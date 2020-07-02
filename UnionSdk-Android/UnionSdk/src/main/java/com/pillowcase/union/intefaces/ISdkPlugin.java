package com.pillowcase.union.intefaces;

import android.app.Activity;

/**
 * Author      : PillowCase
 * Create On   : 2020-07-02 17:44
 * Description :
 */
public interface ISdkPlugin extends IUserPlugin, IPayPlugin {
    /**
     * 初始化
     *
     * @param gameActivity 游戏主Activity
     */
    void init(Activity gameActivity);
}
