package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet { //서블릿 클래스는 httpServlet을 상속받아야함.

    // URL이 호출되면 service()가 호출됨. 이걸 호출하면 서블릿컨테이너(톰캣)이 request와 response를 넘겨줌
    // HttpServletRequest, HttpServletResponse 인터페이스임. 표준 스펙
    // 톰캣등 여러 서블릿 컨테이너들이 그 인터페이스에 대한 구현체를 넘겨줌
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username : " + username);

        //setContentType, setCharacterEncoding - 헤더정보
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8"); //euc-kr 은 이제는 쓰면 안됨.
        //http 메시지 바디
        response.getWriter().write("hello " + username);



    }
}
