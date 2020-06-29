package com.pillowcase.union.intefaces;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/**
 * Author      :  PillowCase
 * Created On  ： 2020-06-29 11:52
 * Description ： Application 监听接口
 */
public interface IApplicationListener {
    void onApplicationCreate(Application application);

    void onApplicationAttachBaseContext(Application application, Context base);

    void onApplicationConfigurationChanged(Application application,Configuration config);

    void onApplicationTerminate(Application application);
}
