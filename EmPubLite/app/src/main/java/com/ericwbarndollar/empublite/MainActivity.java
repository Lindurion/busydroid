package com.ericwbarndollar.empublite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/** Main activity for the EmPubLite Android app. */
public final class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.options, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.about:
        startActivity(new Intent(this, SimpleContentActivity.class));
        return true;  // Handled.

      case R.id.help:
        startActivity(new Intent(this, SimpleContentActivity.class));
        return true;  // Handled.
    }

    return super.onOptionsItemSelected(item);
  }
}
