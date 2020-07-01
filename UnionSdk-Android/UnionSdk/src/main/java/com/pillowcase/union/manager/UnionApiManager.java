package com.pillowcase.union.manager;

import com.pillowcase.logger.LoggerUtils;
import com.pillowcase.logger.impl.ILoggerOperation;

/**
 * Author      :  PillowCase
 * Created On  ： 2020-07-01 16:59
 * Description ：
 */
public class UnionApiManager implements ILoggerOperation {
    private static final UnionApiManager ourInstance = new UnionApiManager();

    private LoggerUtils mLoggerUtils;

    public static UnionApiManager getInstance() {
        return ourInstance;
    }

    private UnionApiManager() {
        if (mLoggerUtils == null) {
            mLoggerUtils = new LoggerUtils(UnionManager.getInstance().isDebug(), getClass().getSimpleName());
        }
    }

    /**
     * 获取渠道配置信息
     */
    public void getChannelConfig() {
        try {
            log("getChannelConfig", "");
            // TODO: 2020/7/1 判断网络是否连接成功
        } catch (Exception e) {
            error(e, "getChannelConfig");
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
