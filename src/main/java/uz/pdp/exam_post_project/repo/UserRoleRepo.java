package uz.pdp.exam_post_project.repo;

import jakarta.persistence.EntityManager;
import uz.pdp.exam_post_project.entity.UserRole;

import static uz.pdp.exam_post_project.config.MyListener.emf;

public class UserRoleRepo {
    public static UserRole findUserById(Integer userId) {
        try(
                EntityManager entityManager = emf.createEntityManager();
                ) {
            return entityManager.find(UserRole.class, userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
