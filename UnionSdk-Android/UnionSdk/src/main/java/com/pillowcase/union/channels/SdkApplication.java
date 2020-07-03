package com.pillowcase.union.channels;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.pillowcase.union.intefaces.IApplicationListener;

/**
 * Author      :  PillowCase
 * Created On  ： 2020-06-29 11:56
 * Description ： 聚合SDK 自带默认SDK Application
 */
public class SdkApplication implements IApplicationListener {

    public SdkApplication() {

    }

    @Override
    public void onApplicationCreate(Application application) {

    }

    @Override
    public void onApplicationAttachBaseContext(Application application, Context base) {

    }

    @Override
    public void onApplicationConfigurationChanged(Application application, Configuration config) {

    }

    @Override
    public void onApplicationTerminate(Application application) {

    }
}
