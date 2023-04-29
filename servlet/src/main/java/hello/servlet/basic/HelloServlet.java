package hello.servlet.basic;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//서블릿은 HttpServlet을 상속받아야 함
// /hello로 오면 helloServlet이 실행됨
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // 매핑된 url이 호출되면 서블릿 컨테이너는 service를 실행함
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        //http 메세지 헤더에 정보가 들어감
        response.setContentType("text/plain");//응답 형식이 단순 텍스트라는 것을 알려줌
        response.setCharacterEncoding("utf-8");//utf-8형식이라는 것을 알려줌
        //Http 메세지 바디에 데이터가 들어감
        response.getWriter().write("hello " + username);

    }

}
