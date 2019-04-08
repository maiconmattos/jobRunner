package com.mmattos.jobrunner.impl;

import com.mmattos.jobrunner.Job;

import java.util.concurrent.*;

public class JobDispatcher {


    ExecutorService executor = Executors.newCachedThreadPool();
    CompletionService<JobStatus> completionService = new ExecutorCompletionService<>(executor);

    public JobDispatcher(long jobCount) {
        executor.submit(new StatusCheckRunnable(completionService, jobCount));
    }

    public void dispatchJob(Job job){
        completionService.submit(new JobCallable(job));
    }

    public void shutdown() {
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        }
    }

}
