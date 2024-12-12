package uz.pdp.exam_post_project.servlet;

import jakarta.persistence.EntityManager;
import uz.pdp.exam_post_project.entity.Comment;
import uz.pdp.exam_post_project.entity.Post;
import uz.pdp.exam_post_project.entity.Users;
import uz.pdp.exam_post_project.repo.PostRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static uz.pdp.exam_post_project.config.MyListener.emf;

@WebServlet("/addComment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(
                EntityManager entityManager = emf.createEntityManager();
                ) {
            int postId = Integer.parseInt(req.getParameter("postId"));
            Post post= PostRepo.findPostById(postId);
            String comment = req.getParameter("comment");
            Optional<Users> optionalUsers=  LoginServlet.currentUser(req);
            if (optionalUsers.isEmpty()){
                resp.sendRedirect("/login.jsp");
                return;
            }

            Users users = optionalUsers.get();
            req.getSession().setAttribute("currentUser",users);

            entityManager.getTransaction().begin();
            Comment comment1 = new Comment(comment,post,users);
            entityManager.persist(comment1);
            entityManager.getTransaction().commit();
            resp.sendRedirect("/comments.jsp?postId="+postId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
