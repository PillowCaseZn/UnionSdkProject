package com.pillowcase.union.manager;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.text.TextUtils;

import com.pillowcase.logger.LoggerUtils;
import com.pillowcase.logger.impl.ILoggerOperation;
import com.pillowcase.union.intefaces.IApplicationListener;
import com.pillowcase.union.intefaces.ISdkCallbacks;
import com.pillowcase.union.intefaces.ISdkMethods;
import com.pillowcase.union.modules.InitParams;
import com.pillowcase.union.modules.MetaDataConfig;
import com.pillowcase.union.utils.MetaDataUtils;

/**
 * Author      :  PillowCase
 * Created On  ： 2020-06-29 11:59
 * Description ： 主要逻辑
 */
public class UnionManager implements ISdkMethods, IApplicationListener, ILoggerOperation {
    private static final UnionManager ourInstance = new UnionManager();

    private LoggerUtils mLoggerUtils;
    /**
     * 是否Debug模式
     */
    private final boolean isDebug = true;

    private IApplicationListener mApplicationListener;

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
        } catch (Exception e) {
            error(e, "init");
        }
    }

    @Override
    public void login() {
        try {
            log("login", "");
        } catch (Exception e) {
            error(e, "login");
        }
    }

    @Override
    public void switchLogin() {
        try {
            log("switchLogin", "");
        } catch (Exception e) {
            error(e, "switchLogin");
        }
    }

    @Override
    public void submitRoleInfo() {
        try {
            log("submitRoleInfo", "");
        } catch (Exception e) {
            error(e, "submitRoleInfo");
        }
    }

    @Override
    public void logout() {
        try {
            log("logout", "");
        } catch (Exception e) {
            error(e, "logout");
        }
    }

    @Override
    public void pay() {
        try {
            log("pay", "");
        } catch (Exception e) {
            error(e, "pay");
        }
    }

    @Override
    public void exit() {
        try {
            log("exit", "");
        } catch (Exception e) {
            error(e, "exit");
        }
    }

    @Override
    public void onNewIntent(Activity gameActivity, Intent intent) {
        try {
            log("onNewIntent", "");
        } catch (Exception e) {
            error(e, "onNewIntent");
        }
    }

    @Override
    public void onActivityResult(Activity gameActivity, int requestCode, int resultCode, Intent data) {
        try {
            log("onActivityResult", "");
        } catch (Exception e) {
            error(e, "onActivityResult");
        }
    }

    @Override
    public void onRestart(Activity gameActivity) {
        try {
            log("onRestart", "");
        } catch (Exception e) {
            error(e, "onRestart");
        }
    }

    @Override
    public void onRequestPermissionsResult(Activity gameActivity, int requestCode, String[] permissions, int[] grantResults) {
        try {
            log("onRequestPermissionsResult", "");
        } catch (Exception e) {
            error(e, "onRequestPermissionsResult");
        }
    }

    @Override
    public void onConfigurationChanged(Activity gameActivity, Configuration newConfig) {
        try {
            log("onConfigurationChanged", "");
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
                proxyName = "com.pillowcase.union.channels.DefaultSdkApplication";
            }
            log("getProxyApplication", "Proxy Name : " + proxyName);

            Class clazz = Class.forName(proxyName);
            listener = (IApplicationListener) clazz.newInstance();
        } catch (Exception e) {
            error(e, "getProxyApplication");
        }
        return listener;
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
