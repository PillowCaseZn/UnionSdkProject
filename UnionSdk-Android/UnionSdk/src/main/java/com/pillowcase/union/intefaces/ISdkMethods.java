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

    /**
     * 登录
     */
    void login();

    /**
     * 切换账号
     */
    void switchLogin();

    /**
     * 提交游戏角色信息
     */
    void submitRoleInfo();

    /**
     * 账号注销
     */
    void logout();

    /**
     * 支付
     */
    void pay();

    /**
     * 退出游戏
     */
    void exit();
}
