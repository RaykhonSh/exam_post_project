package uz.pdp.exam_post_project.repo;

import jakarta.persistence.EntityManager;
import uz.pdp.exam_post_project.entity.Attachment;
import uz.pdp.exam_post_project.entity.AttachmentContent;

import static uz.pdp.exam_post_project.config.MyListener.emf;

public class AttachmentRepo {
    public static Attachment saveFile(String submittedFileName, byte[] bytes) {
        try(
                EntityManager entityManager = emf.createEntityManager();
                ) {
            entityManager.getTransaction().begin();
            Attachment attachment = new Attachment(submittedFileName);
            entityManager.persist(attachment);
            AttachmentContent attachmentContent = new AttachmentContent(attachment,bytes);
            entityManager.persist(attachmentContent);
            entityManager.getTransaction().commit();
            return attachment;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
