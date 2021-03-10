package com.google.sps.quote;

/**
 * Class that stores information about a Quote including author and text
 */
public final class Quote {
  private final String authorName;
  private final String quoteText;

  public Quote(String author, String quote) {
    this.authorName = author;
    this.quoteText = quote;
  }

  public String getQuoteAuthor() {
    return this.authorName;
  }

  public String getQuoteText() {
    return this.quoteText;
  }
}
