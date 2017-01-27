package com.ericwbarndollar.empublite;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/** Adapter for book table of contents. */
final class ContentsAdapter extends FragmentStatePagerAdapter {
  private final BookContents contents;

  ContentsAdapter(FragmentManager fragmentManager, BookContents contents) {
    super(fragmentManager);
    this.contents = contents;
  }

  @Override
  public int getCount() {
    return contents.getChapterCount();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return contents.getChapterTitle(position);
  }

  @Override
  public Fragment getItem(int position) {
    return SimpleContentFragment.newInstance(
        "file:///android_asset/book/" + contents.getChapterFileName(position));
  }
}
