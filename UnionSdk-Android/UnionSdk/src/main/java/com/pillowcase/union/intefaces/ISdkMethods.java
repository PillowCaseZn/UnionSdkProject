package com.pillowcase.union.intefaces;

import com.pillowcase.union.modules.InitParams;

/**
 * Author      : PillowCase
 * Create On   : 2020-06-28 23:52
 * Description : SDK 主要的功能方法
 */
public interface ISdkMethods extends IActivityLifeListener {
    /**
     * 初始化
     *
     * @param params    初始化参数
     * @param callbacks SDK 回调
     */
    void init(InitParams params, ISdkCallbacks callbacks);

    void login();

    void switchLogin();

    void submitRoleInfo();

    void logout();

    void pay();

    void exit();
}
