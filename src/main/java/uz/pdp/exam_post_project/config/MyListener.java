package uz.pdp.exam_post_project.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import uz.pdp.exam_post_project.entity.Roles;
import uz.pdp.exam_post_project.entity.Users;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class MyListener implements ServletContextListener {
    public static EntityManagerFactory emf;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
       emf = Persistence.createEntityManagerFactory("default");
        try (
                EntityManager entityManager = emf.createEntityManager();
        ){
            List<Roles> fromRoles = entityManager.createQuery("from Roles ", Roles.class).getResultList();
            if (fromRoles.isEmpty()) {
                entityManager.getTransaction().begin();
                Roles role1 = new Roles("ADMIN");
                Roles role2 = new Roles("USER");
                entityManager.persist(role1);
                entityManager.persist(role2);
                entityManager.getTransaction().commit();
            }

            List<Users> fromUsers = entityManager.createQuery("from Users", Users.class).getResultList();
            if (fromUsers.isEmpty()) {
                entityManager.getTransaction().begin();
                Users users1 = new Users("12345678","melek@gmail.com");
                Users users2 = new Users("12345678","rayhon@gmail.com");
                Users users3 = new Users("12345678","abdusobur@gmail.com");
                Users users4 = new Users("12345678","nozanin@gmail.com");
                Users users5 = new Users("12345678","jonibek@gmail.com");
                entityManager.persist(users1);
                entityManager.persist(users2);
                entityManager.persist(users3);
                entityManager.persist(users4);
                entityManager.persist(users5);
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
