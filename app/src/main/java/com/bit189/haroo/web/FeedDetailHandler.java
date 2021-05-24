package com.bit189.haroo.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bit189.haroo.domain.Comment;
import com.bit189.haroo.domain.Feed;
import com.bit189.haroo.service.CommentService;
import com.bit189.haroo.service.FeedService;
import com.bit189.haroo.service.ReCommentService;

@SuppressWarnings("serial")
@WebServlet("/feed/detail")
public class FeedDetailHandler extends HttpServlet {

  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    FeedService feedService = (FeedService) request.getServletContext().getAttribute("feedService");
    CommentService commentService = (CommentService) request.getServletContext().getAttribute("commentService");
    ReCommentService reCommentService = (ReCommentService) request.getServletContext().getAttribute("reCommentService");

    int no = Integer.parseInt(request.getParameter("no"));

    try {
      Feed feed = feedService.get(no);
      List<Comment> comments = commentService.list(no);
      //      List<ReComment> reComment = reCommentService.list();

      //      HttpSession session = request.getSession();
      request.setAttribute("feed", feed);
      request.setAttribute("comments", comments);

      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/jsp/feed/detail.jsp").include(request, response);

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}





