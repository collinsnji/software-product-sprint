package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.gson.Gson;
import com.google.sps.comment.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet responsible for listing comments. */
@WebServlet("/comments")
public class ListCommentsServlet extends HttpServlet {
  @Override
  public void doGet(final HttpServletRequest request, final HttpServletResponse response)
      throws IOException {
    final Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    final Query<Entity> query = Query.newEntityQueryBuilder()
                                    .setKind("Comment")
                                    .setOrderBy(OrderBy.desc("timestamp"))
                                    .build();
    final QueryResults<Entity> results = datastore.run(query);

    final List<Comment> tasks = new ArrayList<>();
    while (results.hasNext()) {
      final Entity entity = results.next();

      final long id = entity.getKey().getId();
      final String author = entity.getString("author");
      final String comment = entity.getString("comment");
      final long timestamp = entity.getLong("timestamp");

      final Comment task = new Comment(id, author, comment, timestamp);
      tasks.add(task);
    }

    final Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(tasks));
  }
}