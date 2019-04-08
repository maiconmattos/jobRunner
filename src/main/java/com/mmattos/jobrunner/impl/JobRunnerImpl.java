package com.mmattos.jobrunner.impl;

import com.mmattos.jobrunner.Job;
import com.mmattos.jobrunner.JobQueue;
import com.mmattos.jobrunner.JobRunner;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobRunnerImpl implements JobRunner {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public void runner(JobQueue jobQueue, long jobCount) {

        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionService<JobStatus> completionService = new ExecutorCompletionService<>(executor);

        for (long i = jobCount; i > 0; i--) {
            Job job = jobQueue.pop();
            completionService.submit(new JobCallable(job));
            JobStatusHandler.getInstance().update(job.uniqueId(), Status.SUBMITTED);

        }

        executor.shutdown();

        checkCompletion(completionService, jobCount);
    }

    public String version() {
        return "536543A4-4077-4672-B501- 3520A49549E6";
    }

    private void checkCompletion(CompletionService<JobStatus> completionService, long jobCount) {
        for (long i = jobCount; i > 0; i--) {
            try {
                JobStatus jobStatus = completionService.take().get();
                JobStatusHandler.getInstance().update(jobStatus.getUniqueId(), jobStatus.getStatus());
            } catch (InterruptedException | ExecutionException e) {
                logger.log(Level.WARNING, "Error while trying to retrieve job status.");
            }
        }
    }
}
