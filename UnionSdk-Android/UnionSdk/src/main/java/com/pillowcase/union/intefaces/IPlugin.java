package com.pillowcase.union.intefaces;

/**
 * Author      :  PillowCase
 * Created On  ： 2020-07-01 16:55
 * Description ： 渠道插件接口
 */
public interface IPlugin {
    /**
     * 是否支持某个接口
     *
     * @param methodName
     * @return
     */
    boolean isSupportMethod(String methodName);
}
