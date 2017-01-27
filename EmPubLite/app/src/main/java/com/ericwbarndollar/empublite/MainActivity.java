package com.ericwbarndollar.empublite;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.karim.MaterialTabs;

/** Main activity for the EmPubLite Android app. */
public final class MainActivity extends AppCompatActivity {
  private static String TAG_MODEL_FRAGMENT = "model";

  private MaterialTabs tabs;
  private ViewPager viewPager;

  private ContentsAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    tabs = (MaterialTabs) findViewById(R.id.tabs);
    viewPager = (ViewPager) findViewById(R.id.pager);
  }

  @Override
  protected void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);

    if (adapter != null) {
      return;
    }

    // Set up adapter by loading book contents, if needed.
    BookModelFragment bookModelFragment = (BookModelFragment)
        getSupportFragmentManager().findFragmentByTag(TAG_MODEL_FRAGMENT);
    if (bookModelFragment == null) {
      // Initial load: add BookModelFragment, which will load contents on a background thread.
      getSupportFragmentManager().beginTransaction()
          .add(new BookModelFragment(), TAG_MODEL_FRAGMENT)
          .commit();
    } else {
      // Configuration change. See if book contents were already loaded.
      BookContents contents = bookModelFragment.getBookContents();
      if (contents != null) {
        setUpViewPager(contents);
      }

      // Note: Otherwise, contents are still loading and will be set via EventBus event.
    }
  }

  @Override
  protected void onStop() {
    EventBus.getDefault().unregister(this);
    super.onStop();
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
        startActivity(launchSimpleContentIntent("about.html"));
        return true;  // Handled.

      case R.id.help:
        startActivity(launchSimpleContentIntent("help.html"));
        return true;  // Handled.
    }

    return super.onOptionsItemSelected(item);
  }

  private Intent launchSimpleContentIntent(String fileName) {
    return new Intent(this, SimpleContentActivity.class)
        .putExtra(SimpleContentActivity.EXTRA_FILE_PATH, "file:///android_asset/misc/" + fileName);
  }

  @SuppressWarnings("unused")  // Called via EventBus.
  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onBookContentsLoaded(ContentsLoadedEvent contentsLoadedEvent) {
    setUpViewPager(contentsLoadedEvent.getContents());
  }

  private void setUpViewPager(BookContents contents) {
    adapter = new ContentsAdapter(getSupportFragmentManager(), contents);
    viewPager.setAdapter(adapter);

    tabs.setViewPager(viewPager);
  }
}
