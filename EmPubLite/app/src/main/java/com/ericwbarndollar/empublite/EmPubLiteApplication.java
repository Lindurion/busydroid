package com.ericwbarndollar.empublite;

import android.app.Application;
import android.os.StrictMode;

/** Global application class. */
public final class EmPubLiteApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();

    // Set up StrictMode for detecting thread policy violations.
    StrictMode.ThreadPolicy.Builder threadPolicy = new StrictMode.ThreadPolicy.Builder()
        .detectAll()
        .penaltyLog();
    if (BuildConfig.DEBUG) {
      threadPolicy.penaltyFlashScreen();
    }

    StrictMode.setThreadPolicy(threadPolicy.build());
  }
}
