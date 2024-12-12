package uz.pdp.exam_post_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.exam_post_project.entity.abs.BaseEntity;

import java.util.List;

import static uz.pdp.exam_post_project.config.MyListener.emf;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserRole extends BaseEntity {
    @ManyToOne
    private Users user;

    @ManyToOne
    private Roles role;

    public static List<UserRole> findAll() {
        try(
                EntityManager entityManager = emf.createEntityManager();
                ) {
          return entityManager.createQuery("from UserRole ", UserRole.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
