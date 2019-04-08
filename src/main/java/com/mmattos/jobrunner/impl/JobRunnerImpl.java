package com.mmattos.jobrunner.impl;

import com.mmattos.jobrunner.Job;
import com.mmattos.jobrunner.JobQueue;
import com.mmattos.jobrunner.JobRunner;

public class JobRunnerImpl implements JobRunner {

  public void runner(JobQueue jobQueue, long jobCount) {

    JobDispatcher jobDispatcher = new JobDispatcher(jobCount);

    for (long i = jobCount; i > 0; i--) {
      Job job = jobQueue.pop();
      JobStatusHandler.getInstance().update(job.uniqueId(), Status.SUBMITTED);
      jobDispatcher.dispatchJob(job);
    }

    jobDispatcher.shutdown();
  }

  public String version() {
    return "536543A4-4077-4672-B501- 3520A49549E6";
  }

}