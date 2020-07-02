package com.pillowcase.union.plugin;

import com.pillowcase.logger.LoggerUtils;
import com.pillowcase.logger.impl.ILoggerOperation;
import com.pillowcase.union.intefaces.IUserPlugin;
import com.pillowcase.union.manager.UnionManager;

/**
 * Author      : PillowCase
 * Create On   : 2020-07-02 17:43
 * Description : 用户登录插件
 */
public class PluginUser implements IUserPlugin, ILoggerOperation {
    private static final PluginUser ourInstance = new PluginUser();

    private LoggerUtils mLoggerUtils;
    private IUserPlugin mPlugin;

    public static PluginUser getInstance() {
        return ourInstance;
    }

    private PluginUser() {
        if (mLoggerUtils == null) {
            mLoggerUtils = new LoggerUtils(UnionManager.getInstance().isDebug(), getClass().getSimpleName());
        }
    }

    public void init(String pluginName) {
        try {
            log("init", "Plugin Name : " + pluginName);
            if (!pluginName.isEmpty()) {
                mPlugin = (IUserPlugin) PluginFactory.getInstance().initPlugin(pluginName);
            }
        } catch (Exception e) {
            error(e, "init");
        }
    }

    @Override
    public void login() {
        try {
            log("login", "");
            if (mPlugin != null) {
                mPlugin.login();
            }
        } catch (Exception e) {
            error(e, "login");
        }
    }

    @Override
    public void switchLogin() {
        try {
            log("switchLogin", "");
            if (mPlugin != null) {
                mPlugin.switchLogin();
            }
        } catch (Exception e) {
            error(e, "switchLogin");
        }
    }

    @Override
    public void submitRoleInfo() {
        try {
            log("submitRoleInfo", "");
            if (mPlugin != null) {
                mPlugin.submitRoleInfo();
            }
        } catch (Exception e) {
            error(e, "submitRoleInfo");
        }
    }

    @Override
    public void logout() {
        try {
            log("logout", "");
            if (mPlugin != null) {
                mPlugin.logout();
            }
        } catch (Exception e) {
            error(e, "logout");
        }
    }

    @Override
    public void exit() {
        try {
            log("exit", "");
            if (mPlugin != null) {
                mPlugin.exit();
            }
        } catch (Exception e) {
            error(e, "exit");
        }
    }

    @Override
    public void realNameRegister() {
        try {
            log("realNameRegister", "");
            if (mPlugin != null) {
                mPlugin.realNameRegister();
            }
        } catch (Exception e) {
            error(e, "realNameRegister");
        }
    }

    @Override
    public void queryAntiAddiction() {
        try {
            log("queryAntiAddiction", "");
            if (mPlugin != null) {
                mPlugin.queryAntiAddiction();
            }
        } catch (Exception e) {
            error(e, "queryAntiAddiction");
        }
    }

    @Override
    public boolean isSupportMethod(String methodName) {
        if (mPlugin != null) {
            return mPlugin.isSupportMethod(methodName);
        }
        return false;
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
