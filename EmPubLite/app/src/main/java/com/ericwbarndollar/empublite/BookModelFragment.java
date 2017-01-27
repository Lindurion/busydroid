package com.ericwbarndollar.empublite;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicReference;

/** A data model only (no UI) fragment that persists the book's data model across rotation. */
public final class BookModelFragment extends Fragment {
  private static final String TAG = BookModelFragment.class.getSimpleName();

  private final AtomicReference<BookContents> contents = new AtomicReference<>();

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);  // Keep instance of this Fragment around for config changes.
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

    // Fire off background thread to load book contents, if not already loaded.
    if (contents.get() == null) {
      new LoadBookContentsThread(context.getAssets()).run();
    }
  }

  BookContents getBookContents() {
    return contents.get();
  }

  private class LoadBookContentsThread extends Thread {
    private final AssetManager assetManager;

    LoadBookContentsThread(AssetManager assetManager) {
      this.assetManager = assetManager;
    }

    @Override
    public void run() {
      Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

      Gson gson = new Gson();
      try {
        InputStream inputStream = assetManager.open("book/contents.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        contents.set(gson.fromJson(reader, BookContents.class));

        EventBus.getDefault().post(new ContentsLoadedEvent(getBookContents()));
      } catch (IOException e) {
        Log.e(TAG, "Exception parsing contents.json", e);
      }
    }
  }
}
