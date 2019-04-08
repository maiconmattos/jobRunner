package com.mmattos.jobrunner.impl;

import com.mmattos.jobrunner.Job;
import com.mmattos.jobrunner.JobQueue;

import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class JobQueueImpl implements JobQueue {

  private Deque<Job> jobs;

  public JobQueueImpl(Deque<Job> jobs) {
    this.jobs = jobs;
  }

  public boolean add(Job job) {
    return jobs.add(job);
  }

  public boolean addAll(Deque<Job> jobs) {
    return jobs.addAll(jobs);
  }

  public Job pop() {
    return jobs.pop();
  }
}
