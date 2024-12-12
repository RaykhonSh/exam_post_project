package uz.pdp.exam_post_project.repo;

import jakarta.persistence.EntityManager;
import uz.pdp.exam_post_project.entity.AttachmentContent;

import static uz.pdp.exam_post_project.config.MyListener.emf;

public class AttachmentContentRepo {
    public static AttachmentContent findAttachment(int attachmentId) {
        try (
                EntityManager entityManager = emf.createEntityManager();
                ){
            return entityManager.find(AttachmentContent.class, attachmentId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
