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
public class AttachmentContent extends BaseEntity {
    @ManyToOne
    private Attachment attachment;

    private byte[] content;
}
