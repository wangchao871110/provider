package com.xfs.bs.utils;


import org.apache.log4j.Logger;

import com.xfs.base.service.SysLogsService;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.SpringContextUtil;

public class SysLogsUtil {
    private static Logger log =Logger.getLogger(SysLogsUtil.class);

    public static SysLogsService sysLogsService = null;

    private synchronized static void init() {
        sysLogsService = (SysLogsService) SpringContextUtil.getBean(SysLogsService.class);
    }

    static {
        init();
    }

    public static void saveLogs(String functionName, String desc, ContextInfo user, String ip) {
        try{
            sysLogsService.saveLogs(functionName, desc, user,ip);
        }catch(Exception e){
            log.error(e);
        }

    }
}
