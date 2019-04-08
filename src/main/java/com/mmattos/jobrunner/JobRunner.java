package com.mmattos.jobrunner;

public interface JobRunner {

  void runner(JobQueue jobQueue, long jobCount);

  String version();
}