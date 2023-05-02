package ir.mapsa.autochargemodule.models.entities;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Audited
public class Profile {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long minimumBalance;

    @Version
    private Integer version;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;
}
