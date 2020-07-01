package com.pillowcase.union.intefaces;

/**
 * Author      : PillowCase
 * Create On   : 2020-07-01 23:52
 * Description : 回调接口
 */
public interface IJsonCallBack {
    /**
     * 成功回调
     *
     * @param json Json 字符串
     */
    void onSuccess(String json);

    /**
     * 失败回调
     *
     * @param code     错误码
     * @param errorMsg 错误信息
     */
    void onError(int code, String errorMsg);
}
