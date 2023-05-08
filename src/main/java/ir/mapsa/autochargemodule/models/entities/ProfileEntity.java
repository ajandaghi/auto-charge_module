package ir.mapsa.autochargemodule.models.entities;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

@Entity
@Audited
public class ProfileEntity extends AbstractEntity {


    private Long minimumBalance;



}
