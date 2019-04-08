package com.workday.techtest;
/**
* Provides Jobs to execute */
public interface JobQueue {
// Remove a job from the queue. If the queue has been drained, // this call will block until a new job becomes available
Job pop();
}