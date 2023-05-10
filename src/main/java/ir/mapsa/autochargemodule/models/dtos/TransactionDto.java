package ir.mapsa.autochargemodule.models.dtos;

import ir.mapsa.autochargemodule.models.entities.TransactionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto extends AbstractDto{
    private String trackingId;

    private Long amount;

    private TransactionStatus status;
}