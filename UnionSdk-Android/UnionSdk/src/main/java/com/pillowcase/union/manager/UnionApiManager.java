package com.pillowcase.union.manager;

import com.pillowcase.logger.LoggerUtils;
import com.pillowcase.logger.impl.ILoggerOperation;
import com.pillowcase.union.intefaces.IJsonCallBack;
import com.pillowcase.union.modules.ApiResultBean;
import com.pillowcase.union.modules.Code;
import com.pillowcase.utils.NetUtils;

/**
 * Author      :  PillowCase
 * Created On  ： 2020-07-01 16:59
 * Description ：
 */
public class UnionApiManager implements ILoggerOperation {
    private static final UnionApiManager ourInstance = new UnionApiManager();

    private LoggerUtils mLoggerUtils;
    private NetUtils mNetUtils;

    public static UnionApiManager getInstance() {
        return ourInstance;
    }

    private UnionApiManager() {
        if (mLoggerUtils == null) {
            mLoggerUtils = new LoggerUtils(UnionManager.getInstance().isDebug(), getClass().getSimpleName());
        }
        if (mNetUtils == null) {
            mNetUtils = new NetUtils(UnionManager.getInstance().getGameActivity());
        }
    }

    /**
     * 获取渠道配置信息
     */
    public void getChannelConfig(IJsonCallBack callBack) {
        try {
            log("getChannelConfig", "");
            if (!mNetUtils.isNetConnect()) {
                callBack.onError(Code.NETWORK_ERROR, "请检查网络连接");
                return;
            }
            // TODO: 2020/7/2 请求后台，返回相关数据

            ApiResultBean resultJson = new ApiResultBean();
            resultJson.setCode(Code.SUCCESS);
            callBack.onSuccess(resultJson.result());
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
