package ir.mapsa.autochargemodule.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@EqualsAndHashCode(callSuper = true)
@Entity
@Audited
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileEntity extends AbstractEntity {


    private Long minimumBalance;



}
