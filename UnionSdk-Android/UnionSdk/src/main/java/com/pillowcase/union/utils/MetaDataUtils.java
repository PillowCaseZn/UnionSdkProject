package com.pillowcase.union.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.pillowcase.logger.LoggerUtils;
import com.pillowcase.logger.impl.ILoggerOperation;
import com.pillowcase.union.manager.UnionManager;

/**
 * Author      :  PillowCase
 * Created On  ： 2020-06-29 15:39
 * Description ： 获取AndroidManifest.xml 中 MetaData属性
 */
public class MetaDataUtils implements ILoggerOperation {
    private static final MetaDataUtils ourInstance = new MetaDataUtils();
    private LoggerUtils mLoggerUtils;

    public static MetaDataUtils getInstance() {
        return ourInstance;
    }

    public MetaDataUtils() {
        if (mLoggerUtils == null) {
            mLoggerUtils = new LoggerUtils(UnionManager.getInstance().isDebug(), getClass().getSimpleName());
        }
    }

    /**
     * @param context 上下文
     * @param key     MetaData 属性 Key
     */
    public String getMetaData(Context context, String key) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData != null && appInfo.metaData.containsKey(key)) {

                return String.valueOf(appInfo.metaData.get(key));
            } else {
                log("getMetaData", "The meta-data key is not exists." + key);
            }
        } catch (Exception e) {
            error(e, "getMetaData");
        }
        return null;
    }

    @Override
    public void log(String s, Object o) {
        if (mLoggerUtils != null) {
            mLoggerUtils.log(s, o);
        }
    }

    @Override
    public void warn(String s, String s1) {
        if (mLoggerUtils != null) {
            mLoggerUtils.warn(s, s1);
        }
    }

    @Override
    public void error(Throwable throwable, String s) {
        if (mLoggerUtils != null) {
            mLoggerUtils.error(throwable, s);
        }
    }
}
