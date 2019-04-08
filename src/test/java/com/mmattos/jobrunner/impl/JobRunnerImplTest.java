package com.mmattos.jobrunner.impl;

import com.mmattos.jobrunner.Job;
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
    queue.add(new JobImpl(200, 200, 6));
    queue.add(new JobImpl(200, 201, 3));
    queue.add(new FalingJobImpl(100, 102, 4));
    queue.add(new FalingJobImpl(100, 103, 1));
    queue.add(new JobImpl(200, 202, 7));
    queue.add(new FalingJobImpl(100, 101, 2));

    jobRunner.runner(queue, 6);

  }
}