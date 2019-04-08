package com.workday.techtest.impl;

import com.workday.techtest.Job;
import com.workday.techtest.JobQueue;

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
