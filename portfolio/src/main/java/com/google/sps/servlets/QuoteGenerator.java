package com.google.sps.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /quote URL endpoint */
@WebServlet("/quote")
public class QuoteGenerator extends HttpServlet {
  /**
   * Build the quotes using the author name and quote text.
   *
   * @param authorName The author of the quote's name
   * @param quoteText  The quote text
   * @return A map with the author's name and the text
   */
  private HashMap<String, String> buildQuote(final String authorName, final String quoteText) {
    final HashMap<String, String> quoteInfo = new HashMap<>();
    quoteInfo.put("authorName", authorName);
    quoteInfo.put("quoteText", quoteText);
    return quoteInfo;
  }

  @Override
  public void doGet(final HttpServletRequest request, final HttpServletResponse response)
      throws IOException {
    // Add all to the quotes ArrayList
    final ArrayList<HashMap<String, String>> quotes = new ArrayList<>();
    quotes.add(buildQuote("Toni Morrision", "Correct what you can; learn from what you can\'t."));
    quotes.add(buildQuote("Joan Didion", "Life changes fast. Life changes in the in the instant."));
    quotes.add(buildQuote(
        "Maya Angelou", "I did then what I knew how to do. Now that I know better, I do better."));

    // Create a HashMap to store all the quotes along with the authors
    final HashMap<String, ArrayList<HashMap<String, String>>> quotesJson = new HashMap<>();
    quotesJson.put("quotes", quotes);

    // Parse the data and return the json output
    final Gson gson = new Gson();
    final String json = gson.toJson(quotesJson);
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }
}
