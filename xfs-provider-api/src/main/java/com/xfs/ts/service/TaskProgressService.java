package com.xfs.ts.service;

import java.util.HashMap;
import java.util.List;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2016-11-17 10:43
 */
public interface TaskProgressService {

    boolean isStarted(String spkey);

    @SuppressWarnings("rawtypes")
    void eachOne(Integer spid);

    void sendNotice2Client(HashMap<String, Object> row);

    boolean sendTaskClient(HashMap<String, Object> row);

    boolean sendLoginClient(HashMap<String, Object> row);

    List<HashMap<String, Integer>> findAllSpService();
}
