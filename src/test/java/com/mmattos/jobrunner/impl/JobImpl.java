package com.mmattos.jobrunner.impl;

import com.mmattos.jobrunner.Job;

import java.util.concurrent.TimeUnit;

public class JobImpl implements Job {

  private long customerId;
  private long uniqueId;
  private int duration;

  public JobImpl(long customerId, long uniqueId, int duration) {
    this.customerId = customerId;
    this.uniqueId = uniqueId;
    this.duration = duration;
  }

  public long customerId() {
    return this.customerId;
  }

  public long uniqueId() {
    return this.uniqueId;
  }

  public int duration() {
    return this.duration;
  }

  public void execute() {
    try {
      TimeUnit.SECONDS.sleep(duration);
    } catch (InterruptedException e) {
    }
  }
}
