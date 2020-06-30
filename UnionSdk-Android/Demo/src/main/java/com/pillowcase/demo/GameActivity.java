package com.pillowcase.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.pillowcase.union.UnionSdk;
import com.pillowcase.union.intefaces.ISdkCallbacks;
import com.pillowcase.union.modules.Code;
import com.pillowcase.union.modules.InitParams;

/**
 * Author      : PillowCase
 * Create On   : 2020-06-28 23:12
 * Description : 游戏主页
 */
public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitParams params = new InitParams();
        params.setGameActivity(this);
        params.setAppId("");
        UnionSdk.getInstance().init(params, new ISdkCallbacks() {

            @Override
            public void initSuccess() {

            }

            @Override
            public void onErrorCallback(Code errorCode, String errorMsg) {

            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                UnionSdk.getInstance().login();
                break;
            case R.id.switch_account_btn:
                UnionSdk.getInstance().switchLogin();
                break;
            case R.id.submit_role_info_btn:
                UnionSdk.getInstance().submitRoleInfo();
                break;
            case R.id.pay_btn:
                UnionSdk.getInstance().pay();
                break;
            case R.id.exit_btn:
                UnionSdk.getInstance().exit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        UnionSdk.getInstance().exit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        UnionSdk.getInstance().onRestart(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        UnionSdk.getInstance().onNewIntent(this, intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        UnionSdk.getInstance().onConfigurationChanged(this, newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UnionSdk.getInstance().onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        UnionSdk.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

}