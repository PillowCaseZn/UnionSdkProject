package com.pillowcase.union.intefaces;

/**
 * Author      : PillowCase
 * Create On   : 2020-06-28 23:53
 * Description : SDK 回调接口
 */
public interface ISdkCallbacks {
    /**
     * 初始化成功回调
     */
    void initSuccess();

    /**
     * 错误信息回调接口
     *
     * @param errorCode 错误码
     * @param errorMsg  错误信息
     */
    void onErrorCallback(int errorCode, String errorMsg);
}
