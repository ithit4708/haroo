//package com.bit189.haroo.web;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.util.List;
//import javax.servlet.Servlet;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import com.bit189.haroo.service.Sample_MemberService;
//
////@WebServlet("/member/list") 
//public class Sample_MemberListHandler implements Servlet {
//
//  @Override
//  public void service(ServletRequest request, ServletResponse response)
//      throws ServletException, IOException {
//
//    Sample_MemberService memberService = (Sample_MemberService) request.getServletContext().getAttribute("memberService");
//
//    response.setContentType("text/plain;charset=UTF-8");
//    PrintWriter out = response.getWriter();
//    out.println("[회원 목록]");
//
//    try {
//      List<Member> list = memberService.list();
//
//      for (Member m : list) {
//        out.printf("%d, %s, %s, %s, %s\n", 
//            m.getNo(), 
//            m.getName(), 
//            m.getEmail(),
//            m.getPhoto(),
//            m.getTel());
//      }
//    } catch (Exception e) {
//      StringWriter strWriter = new StringWriter();
//      PrintWriter printWriter = new PrintWriter(strWriter);
//      e.printStackTrace(printWriter);
//      out.println(strWriter.toString());
//    }
//  }
//
//  @Override
//  public void init(ServletConfig config) throws ServletException {
//  }
//
//  @Override
//  public void destroy() {
//  }
//
//  @Override
//  public ServletConfig getServletConfig() {
//    return null;
//  }
//
//  @Override
//  public String getServletInfo() {
//    return null;
//  }
//}
//
//
//
//
//
//
