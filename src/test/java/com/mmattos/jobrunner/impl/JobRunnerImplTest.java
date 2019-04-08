package com.mmattos.jobrunner.impl;

import com.mmattos.jobrunner.Job;
import com.mmattos.jobrunner.JobQueue;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.logging.Logger;

public class JobRunnerImplTest {

  Logger logger = Logger.getLogger(this.getClass().getName());

  @Test
  public void runner() {

    JobRunnerImpl jobRunner = new JobRunnerImpl();

    Deque<Job> jobs = new LinkedList<>();
    JobQueueImpl queue = new JobQueueImpl(jobs);
    queue.add(new JobImpl(200, 200, 60));
    queue.add(new JobImpl(200, 201, 30));
    queue.add(new FalingJobImpl(100, 102, 40));
    queue.add(new FalingJobImpl(100, 103, 10));
    queue.add(new JobImpl(200, 202, 70));
    queue.add(new FalingJobImpl(100, 101, 20));

    jobRunner.runner(queue, 6);

  }
}