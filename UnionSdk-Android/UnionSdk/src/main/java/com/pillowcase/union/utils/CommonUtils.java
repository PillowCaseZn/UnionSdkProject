package com.pillowcase.union.utils;

import com.pillowcase.logger.LoggerUtils;
import com.pillowcase.logger.impl.ILoggerOperation;
import com.pillowcase.union.manager.UnionManager;

/**
 * Author      :  PillowCase
 * Created On  ： 2020-07-01 11:06
 * Description ：
 */
public class CommonUtils implements ILoggerOperation {
    private static final CommonUtils ourInstance = new CommonUtils();
    private LoggerUtils mLoggerUtils;

    public static CommonUtils getInstance() {
        return ourInstance;
    }

    private CommonUtils() {
        if (mLoggerUtils == null) {
            mLoggerUtils = new LoggerUtils(UnionManager.getInstance().isDebug() , getClass().getSimpleName());
        }
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
