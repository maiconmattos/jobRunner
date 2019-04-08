package com.mmattos.jobrunner.impl;

import com.mmattos.jobrunner.Job;
import com.mmattos.jobrunner.JobQueue;
import com.mmattos.jobrunner.JobRunner;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobRunnerImpl implements JobRunner {

  public void runner(JobQueue jobQueue, long jobCount) {

    ExecutorService executor = Executors.newCachedThreadPool();
    CompletionService<JobStatus> completionService = new ExecutorCompletionService<>(executor);
    executor.submit(new StatusCheckRunnable(completionService, jobCount));

    for (long i = jobCount; i > 0; i--) {
      Job job = jobQueue.pop();
      JobStatusHandler.getInstance().update(job.uniqueId(), Status.SUBMITTED);
      completionService.submit(new JobCallable(job));
    }

    shutdown(executor);
  }

  public String version() {
    return "536543A4-4077-4672-B501- 3520A49549E6";
  }

  private void shutdown(ExecutorService executor) {
    executor.shutdown();
    try {
      executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    } catch (InterruptedException e) {
    }
  }
}