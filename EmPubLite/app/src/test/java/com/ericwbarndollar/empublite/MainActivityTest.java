package com.ericwbarndollar.empublite;

import android.widget.TextView;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/** Unit tests for {@link MainActivity}. */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {
  @Test
  public void renderedActivity_shouldDisplayHelloWorldMessage() {
    MainActivity activity = Robolectric.setupActivity(MainActivity.class);

    TextView helloWorldTextView = (TextView) activity.findViewById(R.id.hello_world_label);
    assertEquals("Hello, world!", helloWorldTextView.getText());
  }
}
