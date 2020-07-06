package com.pillowcase.union.manager;

import com.pillowcase.logger.LoggerUtils;
import com.pillowcase.logger.impl.ILoggerOperation;
import com.pillowcase.union.intefaces.IJsonCallBack;
import com.pillowcase.union.intefaces.IUnionChannelMethods;

/**
 * Author      :  PillowCase
 * Created On  ： 2020-07-06 17:16
 * Description ：
 */
public class UnionChannel implements IUnionChannelMethods, ILoggerOperation {
    private static final UnionChannel ourInstance = new UnionChannel();
    private LoggerUtils mLoggerUtils;

    public static UnionChannel getInstance() {
        return ourInstance;
    }

    private UnionChannel() {
        if (mLoggerUtils == null) {
            mLoggerUtils = new LoggerUtils(UnionManager.getInstance().isDebug(), getClass().getSimpleName());
        }
    }

    @Override
    public void getChannelConfig(IJsonCallBack callBack) {
        UnionManager.getInstance().getChannelConfig(callBack);
    }

    @Override
    public void initSuccess() {
        UnionManager.getInstance().getSdkCallbacks().initSuccess();
    }

    @Override
    public void onErrorCallback(int errorCode, String errorMsg) {
        UnionManager.getInstance().getSdkCallbacks().onErrorCallback(errorCode, errorMsg);
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
