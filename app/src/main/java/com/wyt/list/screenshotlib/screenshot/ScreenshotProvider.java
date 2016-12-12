package com.wyt.list.screenshotlib.screenshot;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.View;

import rx.Observable;


/**
 * Created by tarek on 5/17/16.
 */
public class ScreenshotProvider {

  public Observable<Bitmap> getScreenshotBitmap(@NonNull final Activity activity,
                                                final View[] removedViews) {

    return ViewsBitmapObservable.get(activity, removedViews);
  }
}
