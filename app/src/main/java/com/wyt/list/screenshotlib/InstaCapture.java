package com.wyt.list.screenshotlib;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.wyt.list.screenshotlib.exception.ActivityNotRunningException;
import com.wyt.list.screenshotlib.listener.ScreenCaptureListener;
import com.wyt.list.screenshotlib.screenshot.ScreenshotProvider;
import com.wyt.list.screenshotlib.utility.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Created by tarek on 5/17/16.
 */
public final class InstaCapture {
  public static long startTime;

  private static final String MESSAGE_IS_ACTIVITY_RUNNING = "Is your activity running?";
  private static final String MESSAGE_BUSY = "InstaCapture is busy, please wait!";
  private static final String ERROR_INIT_WITH_DESTROYED_ACTIVITY = "Your Activity may be destroyed";
  private static final String ERROR_SCREENSHOT_CAPTURE_FAILED = "Screenshot capture failed";

  private static InstaCapture instance;
  private static Listener listener;

  @NonNull
  private ActivityReferenceManager activityReferenceManager;

  @NonNull
  private ScreenshotProvider screenshotProvider;

  private ScreenCaptureListener mScreenCapturingListener;

  private InstaCapture(@NonNull final Activity activity) {

    this.activityReferenceManager = new ActivityReferenceManager();
    this.activityReferenceManager.setActivity(activity);
    this.screenshotProvider = getScreenshotProvider();
  }

  /**
   * Get single tone instance.
   *
   * @param activity .
   * @return InstaCapture single tone instance.
   */

  /**
   * Set configuration.
   *
   * @param configuration InstaCaptureConfiguration.
   */
  public static void setConfiguration(InstaCaptureConfiguration configuration) {
    if (configuration.logging) {
      Logger.enable();
    } else {
      Logger.disable();
    }
  }

  /** Returns singleton class instance */
  public static InstaCapture getInstance(@NonNull final Activity activity) {

    synchronized (InstaCapture.class) {
      if (instance == null) {
        instance = new InstaCapture(activity);
      } else {
        instance.setActivity(activity);
      }
    }

    return instance;
  }

  private void setActivity(@NonNull final Activity activity) {
    this.activityReferenceManager.setActivity(activity);
  }

  /**
   * Capture the current screen.
   */
  public Listener capture() {
    capture((View) null);
    return getListener();
  }

  /**
   * Capture the current screen.
   *
   * @param ignoredViews from screenshot .
   */
  public Listener capture(View... ignoredViews) {

    captureRx(ignoredViews).subscribe(new Subscriber<Bitmap>() {
      @Override public void onCompleted() {
      }

      @Override public void onError(final Throwable e) {
        Logger.e(ERROR_SCREENSHOT_CAPTURE_FAILED);
        Logger.printStackTrace(e);

        if (mScreenCapturingListener != null) {
          mScreenCapturingListener.onCaptureFailed(e);
        }
      }

      @Override public void onNext(final Bitmap file) {
        if (mScreenCapturingListener != null) {
          mScreenCapturingListener.onCaptureComplete(file);
        }
      }
    });
    return getListener();
  }

  /**
   * Capture the current screen.
   *
   * @return a Observable<File>
   */
  public Observable<Bitmap> captureRx() {
    return captureRx((View) null);
  }

  /**
   * Capture the current screen.
   *
   * @param ignoredViews from screenshot.
   * @return a Observable<Bitmap>
   */
  public Observable<Bitmap> captureRx(@Nullable View... ignoredViews) {

    InstaCapture.startTime = System.currentTimeMillis();
    final Activity activity = activityReferenceManager.getValidatedActivity();
    if (activity == null) {
      return Observable.error(new ActivityNotRunningException(MESSAGE_IS_ACTIVITY_RUNNING));
    }

    if (mScreenCapturingListener != null) {
      mScreenCapturingListener.onCaptureStarted();
    }

    return this.screenshotProvider.getScreenshotBitmap(activity, ignoredViews).observeOn(AndroidSchedulers.mainThread());
  }

  /**
   * @return a ScreenshotProvider
   */
  private ScreenshotProvider getScreenshotProvider() {

    final Activity activity = activityReferenceManager.getValidatedActivity();
    if (activity == null) {
      Logger.e(MESSAGE_IS_ACTIVITY_RUNNING);
      throw new IllegalArgumentException(ERROR_INIT_WITH_DESTROYED_ACTIVITY);
    }

    return new ScreenshotProvider();
  }

  private Listener getListener() {

    synchronized (Listener.class) {
      if (listener == null) {
        listener = new Listener();
      }
    }

    return listener;
  }

  public final class Listener {

    private Listener() {
    }

    public void setScreenCapturingListener(@NonNull ScreenCaptureListener listener) {
      mScreenCapturingListener = listener;
    }
  }
}
