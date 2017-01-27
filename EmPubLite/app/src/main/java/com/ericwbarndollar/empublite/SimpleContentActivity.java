package com.ericwbarndollar.empublite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/** Activity for displaying simple static text content. */
public final class SimpleContentActivity extends AppCompatActivity {
  public static final String EXTRA_FILE_PATH = "file_path";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
      getSupportFragmentManager().beginTransaction()
          .add(android.R.id.content, SimpleContentFragment.newInstance(getFilePath()))
          .commit();
    }
  }

  private String getFilePath() {
    return getIntent().getStringExtra(EXTRA_FILE_PATH);
  }
}
