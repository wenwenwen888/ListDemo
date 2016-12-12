package com.wyt.list.screenshotlib.listener;

import android.graphics.Bitmap;


/**
 * Created by tarek on 5/17/16.
 */

public class SimpleScreenCapturingListener implements ScreenCaptureListener {

  /**
   * Is called when screen capturing task was started
   */
  @Override public void onCaptureStarted() {
    // Empty implementation
  }

  /**
   * Is called when an error was occurred during screen capturing.
   */
  @Override public void onCaptureFailed(Throwable e) {
    // Empty implementation
  }

  /**
   * Is called when screen  is captured successfully.
   *
   * @param bitmap Captured screen bitmap
   */
  @Override public void onCaptureComplete(Bitmap bitmap) {
    // Empty implementation
  }
}
