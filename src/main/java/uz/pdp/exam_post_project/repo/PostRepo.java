package uz.pdp.exam_post_project.repo;

import jakarta.persistence.EntityManager;
import uz.pdp.exam_post_project.entity.Post;

import java.util.List;

import static uz.pdp.exam_post_project.config.MyListener.emf;

public class PostRepo {
    public static List<Post> getAllPosts() {
        try(
                EntityManager entityManager = emf.createEntityManager();
                ) {
            return entityManager.createQuery("from Post ",Post.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Post findPostById(int postId) {
        try(
                EntityManager entityManager = emf.createEntityManager();
        ) {
           return entityManager.find(Post.class,postId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
