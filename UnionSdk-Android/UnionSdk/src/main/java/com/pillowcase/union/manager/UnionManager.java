package com.pillowcase.union.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.pillowcase.logger.LoggerUtils;
import com.pillowcase.logger.impl.ILoggerOperation;
import com.pillowcase.union.intefaces.IApplicationListener;
import com.pillowcase.union.intefaces.IJsonCallBack;
import com.pillowcase.union.intefaces.ISdkCallbacks;
import com.pillowcase.union.intefaces.ISdkMethods;
import com.pillowcase.union.modules.ApiResultBean;
import com.pillowcase.union.modules.Code;
import com.pillowcase.union.modules.InitParams;
import com.pillowcase.union.modules.Message;
import com.pillowcase.union.modules.MetaDataConfig;
import com.pillowcase.union.modules.PluginSupportMethod;
import com.pillowcase.union.plugin.PluginPay;
import com.pillowcase.union.plugin.PluginUser;
import com.pillowcase.union.utils.MetaDataUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Author      :  PillowCase
 * Created On  ： 2020-06-29 11:59
 * Description ： 主要逻辑
 */
public class UnionManager implements ISdkMethods, IApplicationListener, ILoggerOperation {
    private static final UnionManager ourInstance = new UnionManager();

    private LoggerUtils mLoggerUtils;
    private Activity gameActivity;
    /**
     * 是否Debug模式
     */
    private final boolean isDebug = true;

    private IApplicationListener mApplicationListener;
    /**
     * 游戏 Activity 生命周期监听
     */
    private GameActivityLifecycleCallback mLifecycleCallback;

    /**
     * 是否已成功初始化
     */
    private boolean isInit = false;

    /**
     * 初始化参数
     */
    private InitParams mInitParams;
    /**
     * SDK 回调接口
     */
    private ISdkCallbacks mSdkCallbacks;

    private String pluginChannelUser, pluginChannelPay;

    public static UnionManager getInstance() {
        return ourInstance;
    }

    private UnionManager() {
        if (mLoggerUtils == null) {
            mLoggerUtils = new LoggerUtils(isDebug, getClass().getSimpleName());
        }
    }

    @Override
    public void init(InitParams params, ISdkCallbacks callbacks) {
        try {
            log("init", "");
            if (this.mSdkCallbacks == null) {
                this.mSdkCallbacks = callbacks;
            }

            if (isInit) {
                mSdkCallbacks.onErrorCallback(Code.VALIDATION_ERROR, Message.IS_INIT);
                return;
            }
            // TODO: 2020/7/1 Android M 版本 的权限判断
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            }

            //检查初始化参数
            if (checkInitParams(params)) {
                this.mInitParams = params;
                this.gameActivity = params.getGameActivity();

                //判断Android 系统版本是否大于P(28)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    closeAndroidPDialog();
                }

                //平台渠道号  0:默认SDK
                String mergeChannel = MetaDataUtils.getInstance().getMetaData(this.gameActivity, MetaDataConfig.MERGE_CHANNEL);
                if (mergeChannel.isEmpty()) {
                    log("init", "No MergeChannel Config , Use Default MergeChannel");
                    mergeChannel = "0";
                }
                log("init", "MergeChannel : " + mergeChannel);

                // TODO: 2020/7/1 分包id处理逻辑要根据后期Python打包脚本来定义
                //获取分包ID
                String subPackageId = "";

                //用户登录插件
                pluginChannelUser = MetaDataUtils.getInstance().getMetaData(this.gameActivity, MetaDataConfig.PLUGIN_CHANNEL_USER);
                if (pluginChannelUser == null || pluginChannelUser.isEmpty()) {
                    log("init", "No Plugin Channel User Config , Use Default Plugin Channel User");
                    pluginChannelUser = "com.pillowcase.union.channels.SdkUser";
                }
                log("init", "Plugin Channel User : " + pluginChannelUser);

                //用户支付插件
                pluginChannelPay = MetaDataUtils.getInstance().getMetaData(this.gameActivity, MetaDataConfig.PLUGIN_CHANNEL_PAY);
                if (pluginChannelPay == null || pluginChannelPay.isEmpty()) {
                    log("init", "No Plugin Channel Pay Config , Use Default Plugin Channel Pay");
                    pluginChannelPay = "com.pillowcase.union.channels.SdkPay";
                }
                log("init", "Plugin Channel Pay : " + pluginChannelPay);

                //请求服务器，获取渠道配置参数信息
                UnionApiManager.getInstance().getChannelConfig(new IJsonCallBack() {
                    @Override
                    public void onSuccess(String json) {
                        ApiResultBean bean = new Gson().fromJson(json, ApiResultBean.class);
                        log("onSuccess", "Api Result Bean : " + bean);
                        if (bean.getCode() == Code.SUCCESS) {
                            //初始化渠道插件
                            PluginUser.getInstance().init(pluginChannelUser);
                            PluginPay.getInstance().init(pluginChannelPay);
                        } else {
                            mSdkCallbacks.onErrorCallback(bean.getCode(), "服务器数据请求错误");
                        }
                    }

                    @Override
                    public void onError(int code, String errorMsg) {
                        mSdkCallbacks.onErrorCallback(code, errorMsg);
                    }
                });
            } else {
                mSdkCallbacks.onErrorCallback(Code.VALIDATION_ERROR, Message.CHECK_INIT_PARAMS);
            }
        } catch (Exception e) {
            error(e, "init");
        }
    }

    @Override
    public void login() {
        try {
            log("login", "");
            if (this.gameActivity == null) {
                mSdkCallbacks.onErrorCallback(Code.VALIDATION_ERROR, Message.CHECK_INIT_PARAMS);
                return;
            }
            this.gameActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    PluginUser.getInstance().login();
                }
            });
        } catch (Exception e) {
            error(e, "login");
        }
    }

    @Override
    public void switchLogin() {
        try {
            log("switchLogin", "");
            if (this.gameActivity == null) {
                mSdkCallbacks.onErrorCallback(Code.VALIDATION_ERROR, Message.CHECK_INIT_PARAMS);
                return;
            }
            this.gameActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    PluginUser.getInstance().switchLogin();
                }
            });
        } catch (Exception e) {
            error(e, "switchLogin");
        }
    }

    @Override
    public void submitRoleInfo() {
        try {
            log("submitRoleInfo", "");
            if (this.gameActivity == null) {
                mSdkCallbacks.onErrorCallback(Code.VALIDATION_ERROR, Message.CHECK_INIT_PARAMS);
                return;
            }
            this.gameActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    PluginUser.getInstance().submitRoleInfo();
                }
            });
        } catch (Exception e) {
            error(e, "submitRoleInfo");
        }
    }

    @Override
    public void logout() {
        try {
            log("logout", "");
            if (this.gameActivity == null) {
                mSdkCallbacks.onErrorCallback(Code.VALIDATION_ERROR, Message.CHECK_INIT_PARAMS);
                return;
            }
            this.gameActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    PluginUser.getInstance().logout();
                }
            });
        } catch (Exception e) {
            error(e, "logout");
        }
    }

    @Override
    public void pay() {
        try {
            log("pay", "");
            if (this.gameActivity == null) {
                mSdkCallbacks.onErrorCallback(Code.VALIDATION_ERROR, Message.CHECK_INIT_PARAMS);
                return;
            }
            this.gameActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    PluginPay.getInstance().pay();
                }
            });
        } catch (Exception e) {
            error(e, "pay");
        }
    }

    @Override
    public void exit() {
        try {
            log("exit", "");
            //判断渠道是否支持用户退出插件
            if (PluginUser.getInstance().isSupportMethod(PluginSupportMethod.EXIT)) {

            } else {

            }
        } catch (Exception e) {
            error(e, "exit");
        }
    }

    @Override
    public void realNameRegister() {
        try {
            log("realNameRegister", "");
            if (this.gameActivity == null) {
                mSdkCallbacks.onErrorCallback(Code.VALIDATION_ERROR, Message.CHECK_INIT_PARAMS);
                return;
            }
        } catch (Exception e) {
            error(e, "realNameRegister");
        }
    }

    @Override
    public void queryAntiAddiction() {
        try {
            log("queryAntiAddiction", "");
            if (this.gameActivity == null) {
                mSdkCallbacks.onErrorCallback(Code.VALIDATION_ERROR, Message.CHECK_INIT_PARAMS);
                return;
            }
        } catch (Exception e) {
            error(e, "queryAntiAddiction");
        }
    }

    @Override
    public void onNewIntent(Activity gameActivity, Intent intent) {
        try {
            log("onNewIntent", "");
            if (mLifecycleCallback != null) {
                gameActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLifecycleCallback.onNewIntent(gameActivity, intent);
                    }
                });
            }
        } catch (Exception e) {
            error(e, "onNewIntent");
        }
    }

    @Override
    public void onActivityResult(Activity gameActivity, int requestCode, int resultCode, Intent data) {
        try {
            log("onActivityResult", "");
            if (mLifecycleCallback != null) {
                gameActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLifecycleCallback.onActivityResult(gameActivity, requestCode, resultCode, data);
                    }
                });
            }
        } catch (Exception e) {
            error(e, "onActivityResult");
        }
    }

    @Override
    public void onRestart(Activity gameActivity) {
        try {
            log("onRestart", "");
            if (mLifecycleCallback != null) {
                gameActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLifecycleCallback.onRestart(gameActivity);
                    }
                });
            }
        } catch (Exception e) {
            error(e, "onRestart");
        }
    }

    @Override
    public void onRequestPermissionsResult(Activity gameActivity, int requestCode, String[] permissions, int[] grantResults) {
        try {
            log("onRequestPermissionsResult", "");
            if (mLifecycleCallback != null) {
                gameActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLifecycleCallback.onRequestPermissionsResult(gameActivity, requestCode, permissions, grantResults);
                    }
                });
            }
        } catch (Exception e) {
            error(e, "onRequestPermissionsResult");
        }
    }

    @Override
    public void onConfigurationChanged(Activity gameActivity, Configuration newConfig) {
        try {
            log("onConfigurationChanged", "");
            if (mLifecycleCallback != null) {
                gameActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLifecycleCallback.onConfigurationChanged(gameActivity, newConfig);
                    }
                });
            }
        } catch (Exception e) {
            error(e, "onConfigurationChanged");
        }
    }

    @Override
    public void onApplicationCreate(Application application) {
        try {
            log("onApplicationCreate", "");
            if (mApplicationListener != null) {
                mApplicationListener.onApplicationCreate(application);
            }

            //注册全局 Activity 生命周期监听
            if (application != null && mLifecycleCallback != null) {
                mLifecycleCallback = new GameActivityLifecycleCallback();
                application.registerActivityLifecycleCallbacks(mLifecycleCallback);
            }
        } catch (Exception e) {
            error(e, "onApplicationCreate");
        }
    }

    @Override
    public void onApplicationAttachBaseContext(Application application, Context base) {
        try {
            log("onApplicationAttachBaseContext", "");
            if (mApplicationListener == null) {
                mApplicationListener = getProxyApplication(base);
            }
            mApplicationListener.onApplicationAttachBaseContext(application, base);
        } catch (Exception e) {
            error(e, "onApplicationAttachBaseContext");
        }
    }

    @Override
    public void onApplicationConfigurationChanged(Application application, Configuration config) {
        try {
            log("onApplicationConfigurationChanged", "");
            if (mApplicationListener != null) {
                mApplicationListener.onApplicationConfigurationChanged(application, config);
            }
        } catch (Exception e) {
            error(e, "onApplicationConfigurationChanged");
        }
    }

    @Override
    public void onApplicationTerminate(Application application) {
        try {
            log("onApplicationTerminate", "");
            if (mApplicationListener != null) {
                mApplicationListener.onApplicationTerminate(application);
            }
        } catch (Exception e) {
            error(e, "onApplicationTerminate");
        }
    }

    /**
     * 去掉在Android P上的提醒弹窗 （Detected problems with API compatibility(visit g.co/dev/appcompat for more info)
     */
    private void closeAndroidPDialog() {
        log("closeAndroidPDialog", "");
        try {
            @SuppressLint("PrivateApi") Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);

            @SuppressLint("PrivateApi") Class cls = Class.forName("android.app.ActivityThread");
            @SuppressLint("DiscouragedPrivateApi") Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            error(e, "closeAndroidPDialog");
        }
    }

    /**
     * @param params 初始化参数
     * @return 初始化参数是否齐全
     */
    private boolean checkInitParams(InitParams params) {
        try {
            return (params.getGameActivity() != null && !params.getAppId().isEmpty() && params.getAppId().length() > 0);
        } catch (Exception e) {
            error(e, "checkInitParams");
        }
        return false;
    }

    /**
     * 获取要使用的Application
     *
     * @param context 上下文
     */
    private IApplicationListener getProxyApplication(Context context) {
        IApplicationListener listener = null;
        try {
            log("getProxyApplication", "");
            String proxyName = MetaDataUtils.getInstance().getMetaData(context, MetaDataConfig.APPLICATION_PROXY_NAME);

            if (proxyName == null || TextUtils.isEmpty(proxyName)) {
                log("getProxyApplication", "No ProxyApplication Config , Use Default Application");
                proxyName = "com.pillowcase.union.channels.SdkApplication";
            }
            log("getProxyApplication", "Proxy Name : " + proxyName);

            Class clazz = Class.forName(proxyName);
            listener = (IApplicationListener) clazz.newInstance();
        } catch (Exception e) {
            error(e, "getProxyApplication");
        }
        return listener;
    }

    /**
     * @return 是否Debug模式
     */
    public boolean isDebug() {
        return isDebug;
    }

    public Activity getGameActivity() {
        return gameActivity;
    }

    @Override
    public boolean isSupportMethod(String methodName) {
        return false;
    }

    @Override
    public void log(String s, Object o) {
        if (mLoggerUtils != null) {
            mLoggerUtils.log(s, o);
        }
    }

    @Override
    public void warn(String s, String s1) {
        if (mLoggerUtils != null) {
            mLoggerUtils.warn(s, s1);
        }
    }

    @Override
    public void error(Throwable throwable, String s) {
        if (mLoggerUtils != null) {
            mLoggerUtils.error(throwable, s);
        }
    }
}
