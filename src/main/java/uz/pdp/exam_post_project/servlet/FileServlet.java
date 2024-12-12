package uz.pdp.exam_post_project.servlet;

import uz.pdp.exam_post_project.entity.AttachmentContent;
import uz.pdp.exam_post_project.repo.AttachmentContentRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
@WebServlet("/file")
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int attachmentId = Integer.parseInt(req.getParameter("id"));
        AttachmentContent attachmentContent= AttachmentContentRepo.findAttachment(attachmentId);
        resp.getOutputStream().write(attachmentContent.getContent());
    }
}
