package com.pillowcase.union.channels;

import android.app.Activity;

import com.pillowcase.union.intefaces.IPayPlugin;

/**
 * Author      : PillowCase
 * Create On   : 2020-06-30 23:30
 * Description :
 */
public class SdkPay implements IPayPlugin {

    public SdkPay(Activity gameActivity) {
    }

    @Override
    public void pay() {
        Sdk.getInstance().pay();
    }

    @Override
    public boolean isSupportMethod(String methodName) {
        return Sdk.getInstance().isSupportMethod(methodName);
    }
}
