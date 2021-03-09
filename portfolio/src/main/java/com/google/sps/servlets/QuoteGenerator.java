package com.google.sps.servlets;

import com.google.gson.Gson;
import com.google.sps.quote.Quote;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /quote URL endpoint */
@WebServlet("/quote")
public class QuoteGenerator extends HttpServlet {
  @Override
  public void doGet(final HttpServletRequest request, final HttpServletResponse response)
      throws IOException {
    // Get a random quote
    Quote randomQuote = this.getRandomQuote();

    // Parse the quote and return the json output
    final Gson gson = new Gson();
    final String json = gson.toJson(randomQuote);
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  /**
   * Build the quotes using the author name and quote text.
   *
   * @param authorName Name of the quote author
   * @param quoteText  The quote text
   * @return A Quote object with the author's name and the text
   */
  private Quote buildQuote(final String authorName, final String quoteText) {
    final Quote quoteInfo = new Quote(authorName, quoteText);
    return quoteInfo;
  }

  /**
   * Return one random quote from an ArrayList of quotes
   *
   * @return a random quote
   */
  private Quote getRandomQuote() {
    // Add all to the quotes ArrayList
    final ArrayList<Quote> quotes = new ArrayList<>();
    quotes.add(buildQuote("Toni Morrision", "Correct what you can; learn from what you can\'t."));
    quotes.add(buildQuote("Joan Didion", "Life changes fast. Life changes in the in the instant."));
    quotes.add(buildQuote(
        "Maya Angelou", "I did then what I knew how to do. Now that I know better, I do better."));

    // Return a quote from the ArrayList
    Random random = new Random();
    int randomIndex = random.nextInt(quotes.size());
    Quote randomQuote = quotes.get(randomIndex);
    return randomQuote;
  }
}
