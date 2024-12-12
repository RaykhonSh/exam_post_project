package uz.pdp.exam_post_project.servlet;

import jakarta.persistence.EntityManager;
import uz.pdp.exam_post_project.entity.Users;
import uz.pdp.exam_post_project.service.MailService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import static uz.pdp.exam_post_project.config.MyListener.emf;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(
                EntityManager entityManager = emf.createEntityManager();
                ) {

            String email = req.getParameter("email");
            String password = req.getParameter("password");

            Users foundUser= entityManager.createQuery("from Users where email = :email and password = :password", Users.class).
                    setParameter("email", email).
                    setParameter("password", password).getSingleResult();

            if (foundUser==null) {
                resp.sendRedirect("/login.jsp");
            }else{
                HttpSession session = req.getSession();
                session.setAttribute("currentUser", foundUser);
                Random random = new Random();
                String code = String.valueOf(1000 + random.nextInt(9000));  // 1000 dan 9999 gacha bo'lgan tasodifiy son

                MailService.send(code,email);
                resp.sendRedirect("/verifyCode.jsp?code="+code);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Optional<Users> currentUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("currentUser");  // "user" ni "currentUser" bilan almashtirdim
        return Optional.ofNullable(user); // Optional.empty() qilmadik, shuning uchun Optional.ofNullable ishlatdik
    }
}
