package com.mmattos.jobrunner.impl;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class StatusCheckRunnable implements Runnable {

  private Logger logger = Logger.getLogger(this.getClass().getName());

  private CompletionService<JobStatus> completionService;
  private long jobCount;

  public StatusCheckRunnable(CompletionService<JobStatus> completionService, long jobCount) {
    this.completionService = completionService;
    this.jobCount = jobCount;
  }

  @Override
  public void run() {
    checkCompletion(completionService, jobCount);
  }

  private void checkCompletion(CompletionService<JobStatus> completionService, long jobCount) {
    for (long i = jobCount; i > 0; i--) {
      try {
        JobStatus jobStatus = completionService.take().get();
        JobStatusHandler.getInstance().update(jobStatus.getUniqueId(), jobStatus.getStatus());
      } catch (InterruptedException | ExecutionException e) {
        logger.warning("Error while trying to retrieve job status.");
      }
    }
  }
}
