package com.ericwbarndollar.empublite;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Simple fragment for displaying static text content.
 *
 * <p>Use the {@link SimpleContentFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public final class SimpleContentFragment extends Fragment {
  private static final String ARG_CONTENT_FILE_PATH = "content_file_path";

  /**
   * Use this factory method to create a new instance of this fragment using the provided
   * parameters.
   */
  public static SimpleContentFragment newInstance(String contentFilePath) {
    SimpleContentFragment fragment = new SimpleContentFragment();

    Bundle args = new Bundle();
    args.putString(ARG_CONTENT_FILE_PATH, contentFilePath);
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(true);  // Retain this fragment instance on configuration changes.
  }

  @SuppressLint("SetJavaScriptEnabled")
  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View fragmentView = inflater.inflate(
        R.layout.fragment_simple_content, container, false /* attach? */);

    WebView webView = (WebView) fragmentView.findViewById(R.id.simple_content_webview);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.getSettings().setSupportZoom(true);
    webView.getSettings().setBuiltInZoomControls(true);
    webView.loadUrl(getContentFilePath());

    return fragmentView;
  }

  private String getContentFilePath() {
    return getArguments().getString(ARG_CONTENT_FILE_PATH);
  }
}
