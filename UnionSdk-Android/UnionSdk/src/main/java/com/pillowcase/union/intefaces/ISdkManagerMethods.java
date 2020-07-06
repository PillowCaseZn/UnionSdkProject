package com.pillowcase.union.intefaces;

import android.widget.CompoundButton;

import com.pillowcase.union.modules.InitParams;

/**
 * Author      : PillowCase
 * Create On   : 2020-06-28 23:52
 * Description : SDKManager 主要的功能方法
 */
public interface ISdkManagerMethods extends ISdkMethods{
    /**
     * @return 聚合后台配置的渠道参数配置
     */
    void getChannelConfig(IJsonCallBack callBack);
}
