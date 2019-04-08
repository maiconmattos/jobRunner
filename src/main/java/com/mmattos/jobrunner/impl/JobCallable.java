package com.mmattos.jobrunner.impl;

import com.mmattos.jobrunner.Job;

import java.util.concurrent.Callable;

public class JobCallable implements Callable<JobStatus> {

    private Job job;

    public JobCallable(Job job) {
        this.job = job;
    }

    @Override
    public JobStatus call() {
        try {
            JobStatusHandler.getInstance().update(job.uniqueId(), Status.RUNNING);
            job.execute();
        } catch (Exception e) {
            return new JobStatus(job.uniqueId(), Status.FAILURE);
        }
        return new JobStatus(job.uniqueId(), Status.SUCCESS);
    }
}
