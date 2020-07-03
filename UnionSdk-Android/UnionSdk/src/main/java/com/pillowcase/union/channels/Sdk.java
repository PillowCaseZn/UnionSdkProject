package com.pillowcase.union.channels;

import android.app.Activity;

import com.pillowcase.union.intefaces.ISdkPlugin;
import com.pillowcase.union.modules.PluginSupportMethod;
import com.pillowcase.union.plugin.PluginFactory;


/**
 * Author      : PillowCase
 * Create On   : 2020-06-30 23:29
 * Description :
 */
public class Sdk implements ISdkPlugin {
    private static final Sdk ourInstance = new Sdk();
    private String[] supportMethods = new String[]{
            PluginSupportMethod.LOGIN,
            PluginSupportMethod.LOGOUT,
            PluginSupportMethod.SWITCH_LOGIN,
            PluginSupportMethod.SUBMIT_ROLE_INFO,
            PluginSupportMethod.PAY,
    };

    public static Sdk getInstance() {
        return ourInstance;
    }

    @Override
    public void init(Activity gameActivity) {

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
}
