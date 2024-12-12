package uz.pdp.exam_post_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.exam_post_project.entity.abs.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment extends BaseEntity {
    private String comment;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Users users;

}
