package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/** Servlet responsible for creating new comments. */
@WebServlet("/new-comment")
public class UserCommentServlet extends HttpServlet {
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Sanitize user input to remove HTML tags and JavaScript.
    String author = Jsoup.clean(request.getParameter("author"), Whitelist.none());
    String comment = Jsoup.clean(request.getParameter("comment"), Whitelist.none());
    long timestamp = System.currentTimeMillis();

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Comment");
    FullEntity taskEntity = Entity.newBuilder(keyFactory.newKey())
                                .set("author", author)
                                .set("comment", comment)
                                .set("timestamp", timestamp)
                                .build();
    datastore.put(taskEntity);

    response.sendRedirect("/index.html");
  }
}
