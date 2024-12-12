package uz.pdp.exam_post_project.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/verifyCode")
public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String code1 = req.getParameter("code1");
        String code2 = req.getParameter("code2");
        String code3 = req.getParameter("code3");
        String code4 = req.getParameter("code4");
        String realCode=code1.concat(code2).concat(code3).concat(code4);
        if (code.equals(realCode)) {
            resp.sendRedirect("/post.jsp");
        }else {
            resp.sendRedirect("/verifyCode.jsp?code=" + code);
        }
    }
}
