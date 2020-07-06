package com.pillowcase.union.manager;

import com.pillowcase.logger.LoggerUtils;
import com.pillowcase.logger.impl.ILoggerOperation;
import com.pillowcase.union.intefaces.ISdkCallbacks;

/**
 * Author      :  PillowCase
 * Created On  ： 2020-07-06 17:06
 * Description ：
 */
public class UnionSdkCallback implements ISdkCallbacks, ILoggerOperation {
    private ISdkCallbacks mSdkCallbacks;
    private LoggerUtils mLoggerUtils;

    public UnionSdkCallback(ISdkCallbacks mSdkCallbacks) {
        this.mSdkCallbacks = mSdkCallbacks;
        if (mLoggerUtils == null) {
            mLoggerUtils = new LoggerUtils(UnionManager.getInstance().isDebug(), getClass().getSimpleName());
        }
    }

    @Override
    public void initSuccess() {
        try {
            log("initSuccess", "");
        } catch (Exception e) {
            error(e, "initSuccess");
        }
    }

    @Override
    public void onErrorCallback(int errorCode, String errorMsg) {
        try {
            log("onErrorCallback", "");
            mSdkCallbacks.onErrorCallback(errorCode, errorMsg);
        } catch (Exception e) {
            error(e, "onErrorCallback");
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
