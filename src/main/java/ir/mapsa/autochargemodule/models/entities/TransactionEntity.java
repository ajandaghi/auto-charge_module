package ir.mapsa.autochargemodule.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Audited
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity extends AbstractEntity{
    @Column(unique = true)
    private String trackingId;

    private Long amount;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus status;

    private Date transDate;
}
