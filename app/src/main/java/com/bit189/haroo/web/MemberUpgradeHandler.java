package com.bit189.haroo.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bit189.haroo.domain.Member;
import com.bit189.haroo.service.MemberService;

@SuppressWarnings("serial")
@WebServlet("/member/upgrade")
public class MemberUpgradeHandler extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    MemberService memberService = (MemberService) request.getServletContext().getAttribute("memberService");

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Member m = memberService.get(no);
      request.setAttribute("member", m);
      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/jsp/tutor/change2.jsp").include(request, response);

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}






