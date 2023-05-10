package ir.mapsa.autochargemodule.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.envers.Audited;

@EqualsAndHashCode(callSuper = true)
@Entity
@Audited
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity extends AbstractEntity{
    private String trackingId;

    private Long amount;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus status;
}