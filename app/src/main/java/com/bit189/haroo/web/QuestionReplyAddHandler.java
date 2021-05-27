package com.bit189.haroo.web;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.bit189.haroo.domain.AttachedFile;
import com.bit189.haroo.domain.Member;
import com.bit189.haroo.domain.Post;
import com.bit189.haroo.domain.Question;
import com.bit189.haroo.service.ServiceQuestionService;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@SuppressWarnings("serial")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/question/reply/add")
public class QuestionReplyAddHandler extends HttpServlet{

  private String uploadDir;

  @Override
  public void init() throws ServletException {
    this.uploadDir = this.getServletContext().getRealPath("/upload");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    request.getRequestDispatcher("/jsp/serviceQuestion/form2.jsp").include(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServiceQuestionService serviceQuestionService =
        (ServiceQuestionService) request.getServletContext().getAttribute("serviceQuestionService");

    try {
      Question question = new Question();
      Post post = new Post();
      AttachedFile attachedFile = new AttachedFile();

      question.setNo(Integer.parseInt(request.getParameter("no")));
      question.setReplyContent(request.getParameter("content"));

      Collection<Part> files = request.getParts();
      for (Part file : files) {
        if (file.getName().equals("file") && file.getSize() > 0) {
          System.out.println(">" + file.getSubmittedFileName());

          System.out.println("uploadDir1 : " + uploadDir);

          if (file.getSize() > 0) {
            String filename = UUID.randomUUID().toString();

            System.out.println("uploadDir2 : " + uploadDir);

            file.write(this.uploadDir + "/" + filename);
            System.out.println("uploadDir3 : " + uploadDir);
            System.out.println(this.uploadDir + "/");


            attachedFile.setName(filename);

            //attachedFile.add(attachedFile);

            System.out.println("uploadDir4 : " + uploadDir);

            Thumbnails.of(this.uploadDir + "/" + filename)
            .size(500, 500)
            .outputFormat("jpg")
            .crop(Positions.CENTER)
            .toFiles(new Rename() {
              @Override
              public String apply(String name, ThumbnailParameter param) {
                return name + "_300x300";
              }
            });
          }
        }


        System.out.println("uploadDir5 : " + uploadDir);
      }



      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      question.setWriter(loginUser);

      serviceQuestionService.addreply(question, post, attachedFile);

      response.sendRedirect("../detail?no=" + question.getNo());

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }    
}
