package com.ericwbarndollar.empublite;

import java.util.List;

/** Data class that matches contents.json format. */
public class BookContents {
  String title;
  List<Chapter> chapters;

  int getChapterCount() {
    return chapters.size();
  }

  String getChapterFileName(int chapterIndex) {
    return chapters.get(chapterIndex).file;
  }

  String getChapterTitle(int chapterIndex) {
    return chapters.get(chapterIndex).title;
  }

  /** Data class that matches JSON format of an individual chapter. */
  static class Chapter {
    String file;
    String title;
  }
}
