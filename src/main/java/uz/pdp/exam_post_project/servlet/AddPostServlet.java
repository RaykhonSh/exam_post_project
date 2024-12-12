package uz.pdp.exam_post_project.servlet;

import jakarta.persistence.EntityManager;
import uz.pdp.exam_post_project.entity.Attachment;
import uz.pdp.exam_post_project.entity.Post;
import uz.pdp.exam_post_project.repo.AttachmentRepo;
import uz.pdp.exam_post_project.repo.PostRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

import static uz.pdp.exam_post_project.config.MyListener.emf;

@MultipartConfig
@WebServlet("/addPost")
public class AddPostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (
                EntityManager entityManager = emf.createEntityManager();
                ){
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            Part photo = req.getPart("photo");
            Attachment photoAttachment=AttachmentRepo.saveFile(photo.getSubmittedFileName(),photo.getInputStream().readAllBytes());
            entityManager.getTransaction().begin();
            Post post = new Post(title,description,photoAttachment);
            entityManager.persist(post);
            entityManager.getTransaction().commit();
            resp.sendRedirect("/post.jsp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
