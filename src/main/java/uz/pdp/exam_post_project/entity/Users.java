package uz.pdp.exam_post_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.exam_post_project.entity.abs.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Users extends BaseEntity {
    @Column(nullable = false)
    @Size(min = 8,max = 16,message = "password is short or long")
    private String password;

    @Column(nullable = false,unique = true)
    @Email(message = "email error")
    private String email;
}
