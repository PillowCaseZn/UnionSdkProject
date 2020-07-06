package com.pillowcase.union.intefaces;

/**
 * Author      :  PillowCase
 * Created On  ： 2020-07-06 17:16
 * Description ：
 */
public interface IUnionChannelMethods extends ISdkCallbacks{

    /**
     * @return 聚合后台配置的渠道参数配置
     */
    void getChannelConfig(IJsonCallBack callBack);
}
