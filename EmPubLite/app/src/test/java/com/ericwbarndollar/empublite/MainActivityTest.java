package com.ericwbarndollar.empublite;

import static com.google.common.truth.Truth.assertThat;
import static org.robolectric.Shadows.shadowOf;

import android.content.ComponentName;
import android.content.Intent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.shadows.ShadowLooper;

/** Unit tests for {@link MainActivity}. */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {
  @Test
  public void optionsMenu_shouldStartHelpActivity() {
    // Note: Pausing looper like this during activity creation to work around Robolectric
    // IllegalStateException ("Iteration already started") bug.
    ShadowLooper.pauseMainLooper();
    MainActivity activity = Robolectric.setupActivity(MainActivity.class);
    Robolectric.getForegroundThreadScheduler().advanceToLastPostedRunnable();

    // Simulate click on Help option menu item.
    RoboMenuItem aboutMenuItem = new RoboMenuItem(R.id.help);
    activity.onOptionsItemSelected(aboutMenuItem);

    // Make sure the right Activity was launched.
    Intent actualLaunchIntent = shadowOf(activity).getNextStartedActivity();
    assertThat(actualLaunchIntent.getComponent())
        .isEqualTo(new ComponentName(activity, SimpleContentActivity.class));
  }

  @Test
  public void optionsMenu_shouldStartAboutActivity() {
    // Note: Pausing looper like this during activity creation to work around Robolectric
    // IllegalStateException ("Iteration already started") bug.
    ShadowLooper.pauseMainLooper();
    MainActivity activity = Robolectric.setupActivity(MainActivity.class);
    Robolectric.getForegroundThreadScheduler().advanceToLastPostedRunnable();

    // Simulate click on About option menu item.
    RoboMenuItem aboutMenuItem = new RoboMenuItem(R.id.about);
    activity.onOptionsItemSelected(aboutMenuItem);

    // Make sure the right Activity was launched.
    Intent actualLaunchIntent = shadowOf(activity).getNextStartedActivity();
    assertThat(actualLaunchIntent.getComponent())
        .isEqualTo(new ComponentName(activity, SimpleContentActivity.class));
  }
}
