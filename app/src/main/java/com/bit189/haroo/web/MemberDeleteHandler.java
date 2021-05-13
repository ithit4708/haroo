package com.bit189.haroo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bit189.haroo.domain.Member;
import com.bit189.haroo.service.MemberService;

@SuppressWarnings("serial")
@WebServlet("/member/delete")
public class MemberDeleteHandler extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    MemberService memberService = (MemberService) request.getServletContext().getAttribute("memberService");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>회원 삭제</title>");

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Member member = memberService.get(no);
      if (member == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }

      // 회원 관리를 관리자가 할 경우 모든 회원의 정보 변경 가능
      //      Member loginUser = (Member) request.getSession().getAttribute("loginUser");
      //      if (oldBoard.getWriter().getNo() != loginUser.getNo()) {
      //        throw new Exception("삭제 권한이 없습니다!");
      //      }

      memberService.delete(no);

      response.sendRedirect("list");

    } catch (Exception e) {
      StringWriter strWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(strWriter);
      e.printStackTrace(printWriter);

      out.printf("<pre>%s</pre>\n", strWriter.toString());
      out.println("<p><a href='list'>목록</a></p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}






