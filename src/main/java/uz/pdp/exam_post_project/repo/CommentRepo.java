package uz.pdp.exam_post_project.repo;

import jakarta.persistence.EntityManager;
import uz.pdp.exam_post_project.entity.Comment;
import uz.pdp.exam_post_project.entity.Post;

import java.util.List;

import static uz.pdp.exam_post_project.config.MyListener.emf;

public class CommentRepo {
    public static List<Comment> getAllComments() {
        try(
                EntityManager entityManager = emf.createEntityManager();
        ) {
            return entityManager.createQuery("from Comment ",Comment.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
