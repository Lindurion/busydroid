package com.ericwbarndollar.empublite;

/** EventBus event class that holds the loaded book contents. */
final class ContentsLoadedEvent {
  private final BookContents contents;

  ContentsLoadedEvent(BookContents contents) {
    this.contents = contents;
  }

  BookContents getContents() {
    return contents;
  }
}
