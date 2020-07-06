package com.pillowcase.union.channels;

import android.app.Activity;

import com.pillowcase.logger.LoggerUtils;
import com.pillowcase.logger.impl.ILoggerOperation;
import com.pillowcase.union.intefaces.IJsonCallBack;
import com.pillowcase.union.intefaces.ISdkPlugin;
import com.pillowcase.union.manager.UnionChannel;
import com.pillowcase.union.modules.PluginSupportMethod;
import com.pillowcase.union.plugin.PluginFactory;


/**
 * Author      : PillowCase
 * Create On   : 2020-06-30 23:29
 * Description :
 */
public class Sdk implements ISdkPlugin, ILoggerOperation {
    private static final Sdk ourInstance = new Sdk();
    private String[] supportMethods = new String[]{
            PluginSupportMethod.LOGIN,
            PluginSupportMethod.LOGOUT,
            PluginSupportMethod.SWITCH_LOGIN,
            PluginSupportMethod.SUBMIT_ROLE_INFO,
            PluginSupportMethod.PAY,
    };
    private LoggerUtils mLoggerUtils;
    private UnionChannel mChannel;

    public static Sdk getInstance() {
        return ourInstance;
    }

    private Sdk() {
        if (mLoggerUtils == null) {
            mLoggerUtils = new LoggerUtils(true, getClass().getSimpleName());
        }
        if (mChannel == null) {
            mChannel = UnionChannel.getInstance();
        }
    }

    @Override
    public void init(Activity gameActivity) {
        try {
            log("init", "");
            mChannel.getChannelConfig(new IJsonCallBack() {
                @Override
                public void onSuccess(String json) {
                    //渠道初始化
                    mChannel.initSuccess();
                }

                @Override
                public void onError(int code, String errorMsg) {
                    mChannel.onErrorCallback(code, errorMsg);
                }
            });
        } catch (Exception e) {
            error(e, "init");
        }
    }

    @Override
    public void pay() {

    }

    @Override
    public void login() {

    }

    @Override
    public void switchLogin() {

    }

    @Override
    public void submitRoleInfo() {

    }

    @Override
    public void logout() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void realNameRegister() {

    }

    @Override
    public void queryAntiAddiction() {

    }

    @Override
    public boolean isSupportMethod(String methodName) {
        return PluginFactory.getInstance().contain(this.supportMethods, methodName);
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
