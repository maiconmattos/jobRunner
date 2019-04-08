package com.mmattos.jobrunner.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class JobStatusHandler {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private static JobStatusHandler instance = null;

    private Map<Long, Status> jobStatusMap = new HashMap<>();

    private JobStatusHandler() {
    }

    public static JobStatusHandler getInstance() {
        if (instance == null)
            instance = new JobStatusHandler();
        return instance;
    }

    public Status getStatus(Long uniqueId) {
        return jobStatusMap.getOrDefault(uniqueId, Status.NOT_FOUND);
    }

    public synchronized void update(long uniqueId, Status jobStatus) {
        Status oldStatus = this.getStatus(uniqueId);
        logger.info("Updating Status of job [" + uniqueId + "] " +
                "from [" + oldStatus + "] to [" + jobStatus + "]");
        jobStatusMap.put(uniqueId, jobStatus);
    }
}
