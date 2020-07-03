package com.pillowcase.union.channels;

import android.app.Activity;

import com.pillowcase.union.intefaces.IUserPlugin;

/**
 * Author      : PillowCase
 * Create On   : 2020-06-30 23:29
 * Description :
 */
public class SdkUser implements IUserPlugin {

    public SdkUser(Activity gameActivity) {
        Sdk.getInstance().init(gameActivity);
    }

    @Override
    public void login() {
        Sdk.getInstance().login();
    }

    @Override
    public void switchLogin() {
        Sdk.getInstance().switchLogin();
    }

    @Override
    public void submitRoleInfo() {
        Sdk.getInstance().submitRoleInfo();
    }

    @Override
    public void logout() {
        Sdk.getInstance().logout();
    }

    @Override
    public void exit() {
        Sdk.getInstance().exit();
    }

    @Override
    public void realNameRegister() {
        Sdk.getInstance().realNameRegister();
    }

    @Override
    public void queryAntiAddiction() {
        Sdk.getInstance().queryAntiAddiction();
    }

    @Override
    public boolean isSupportMethod(String methodName) {
        return Sdk.getInstance().isSupportMethod(methodName);
    }
}
