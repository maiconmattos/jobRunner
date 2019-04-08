package com.mmattos.jobrunner.impl;

public class JobStatus {

  private long uniqueId;
  private Status status;

  public JobStatus(long uniqueId, Status status) {
    this.uniqueId = uniqueId;
    this.status = status;
  }

  @Override
  public String toString() {
    return "JobStatus{" +
        "uniqueId=" + uniqueId +
        ", status='" + status + '\'' +
        '}';
  }

  public long getUniqueId() {
    return uniqueId;
  }

  public Status getStatus() {
    return status;
  }
}
