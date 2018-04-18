package com.xfs.ss;

import com.xfs.ts.service.TaskProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-17 10:48
 */
@Service
public class TaskProgressTask {
    @Autowired
    TaskProgressService taskProgressService;

    //@Scheduled(cron = "0/10 * * * * *")
    public void excute() {
        // 查询待办以及执行中的服务商
        List<HashMap<String, Integer>> spserviceList = taskProgressService.findAllSpService();
        if (spserviceList != null && spserviceList.size() > 0) {
            for (HashMap<String, Integer> row : spserviceList) {
                Integer spid = row.get("spid");
                taskProgressService.eachOne(spid);
            }
        }
    }
}
