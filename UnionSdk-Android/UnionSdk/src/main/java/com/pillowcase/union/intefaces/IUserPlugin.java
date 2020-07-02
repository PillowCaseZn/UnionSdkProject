package com.pillowcase.union.intefaces;

/**
 * Author      :  PillowCase
 * Created On  ： 2020-07-01 16:57
 * Description ： 渠道用户登录插件接口
 */
public interface IUserPlugin extends IPlugin {
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
     * 退出游戏
     */
    void exit();

    /***
     * 实名注册方法
     */
    void realNameRegister();

    /***
     * 防沉迷系统查询接口
     */
    void queryAntiAddiction();
}
