package com.ericwbarndollar.empublite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/** Activity for displaying simple static text content. */
public final class SimpleContentActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
      // TODO: Pass valid argument for WebView content file path in.
      getSupportFragmentManager().beginTransaction()
          .add(android.R.id.content, SimpleContentFragment.newInstance("" /* contentFilePath */))
          .commit();
    }
  }

}
