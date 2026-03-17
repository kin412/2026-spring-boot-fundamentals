<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>

<%--
  Created by IntelliJ IDEA.
  User: kin41
  Date: 2026-02-04
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>

<%
  //jsp는 나중에 서블릿으로 자동변환 되기 때문에 request, response 사용가능.
  MemberRepository memberRepository = MemberRepository.getInstance();

  System.out.println("MemberSaveServlet.service");
  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));

  Member member = new Member(username, age);
  memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
  성공
  <ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
  </ul>
  <a href="/index.html">메인</a>
</body>
</html>
