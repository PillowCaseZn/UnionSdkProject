package com.pillowcase.union;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.pillowcase.union.intefaces.ISdkCallbacks;
import com.pillowcase.union.intefaces.ISdkMethods;
import com.pillowcase.union.modules.InitParams;

/**
 * Author      : PillowCase
 * Create On   : 2020-06-29 00:00
 * Description : 聚合SDK 主入口
 */
public class UnionSdk implements ISdkMethods {
    private static final UnionSdk ourInstance = new UnionSdk();

    public static UnionSdk getInstance() {
        return ourInstance;
    }

    private UnionSdk() {
    }

    @Override
    public void init(InitParams params, ISdkCallbacks callbacks) {

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
    public void pay() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void onNewIntent(Activity gameActivity, Intent intent) {

    }

    @Override
    public void onActivityResult(Activity gameActivity, int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onRestart(Activity gameActivity) {

    }

    @Override
    public void onRequestPermissionsResult(Activity gameActivity, int requestCode, String[] permissions, int[] grantResults) {

    }

    @Override
    public void onConfigurationChanged(Activity gameActivity, Configuration newConfig) {

    }
}
