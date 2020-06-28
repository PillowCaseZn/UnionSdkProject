package com.pillowcase.union.modules;

import android.app.Activity;

/**
 * Author      : PillowCase
 * Create On   : 2020-06-29 00:17
 * Description : 初始化参数
 */
public class InitParams {
    /**
     * 游戏主Activity
     */
    private Activity gameActivity;
    /**
     * 游戏ID
     */
    private String appId;

    public Activity getGameActivity() {
        return gameActivity;
    }

    public void setGameActivity(Activity gameActivity) {
        this.gameActivity = gameActivity;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
