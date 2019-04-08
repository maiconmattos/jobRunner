package com.mmattos.jobrunner.impl;

import com.mmattos.jobrunner.Job;
import com.mmattos.jobrunner.JobQueue;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.logging.Logger;

public class JobRunnerImplTest {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Test
    public void runner() {

        JobRunnerImpl jobRunner = new JobRunnerImpl();

        Deque<Job> jobs = new LinkedList<Job>();
        createJobs(jobs, 5, 1);

        jobs.add(new FalingJobImpl(100, 100, 20));

        createJobs(jobs, 5, 6);

        JobQueue queue = new JobQueueImpl(jobs);
        jobRunner.runner(queue, jobs.size());
    }

    private void createJobs(Deque<Job> jobs, int count, int first) {
        int last = count + (first - 1);
        //logger.info("Requested to create [" + count + "] jobs from [" + first + " to [" +  last + "]");

        for (int i=first; i <= last ; i++) {
            jobs.add(new JobImpl(i, i, 10));
        }

        //logger.info("[" + count + "] jobs from [" + first + " to [" + last + "] were created successfully");
    }
}