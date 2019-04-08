package com.mmattos.jobrunner.impl;

import com.mmattos.jobrunner.Job;

import java.util.concurrent.TimeUnit;

public class FalingJobImpl implements Job {

    private long customerId;
    private long uniqueId;
    private int duration;

    public FalingJobImpl(long customerId, long uniqueId, int duration) {
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
            TimeUnit.SECONDS.sleep(duration / 2);
        } catch (InterruptedException e) {
        }
        throw new RuntimeException("FAILED job from customer [ " + customerId + " ], " +
                "uniqueId [" + uniqueId + "] Duration : " + duration + " seconds.");
    }
}
