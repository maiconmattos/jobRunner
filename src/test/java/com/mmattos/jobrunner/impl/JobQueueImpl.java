package com.mmattos.jobrunner.impl;

import com.mmattos.jobrunner.Job;
import com.mmattos.jobrunner.JobQueue;

import java.util.Deque;

public class JobQueueImpl implements JobQueue {

    private Deque<Job> jobs;

    public JobQueueImpl(Deque<Job> jobs) {
        this.jobs = jobs;
    }

    public Job pop() {
        return jobs.pop();
    }
}
