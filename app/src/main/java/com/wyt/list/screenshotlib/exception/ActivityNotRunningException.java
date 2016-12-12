package com.wyt.list.screenshotlib.exception;

/**
 * Created by tarek on 5/31/16.
 */

/**
 * This exception is thrown when the Activity is finished or destroyed.
 */
public class ActivityNotRunningException extends RuntimeException {

  public ActivityNotRunningException() {
  }

  public ActivityNotRunningException(String name) {
    super(name);
  }
}

