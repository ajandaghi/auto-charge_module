package ir.mapsa.autochargemodule.models.dtos;

import ir.mapsa.autochargemodule.models.entities.TransactionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class TransactionDto extends AbstractDto{
    private String trackingId;

    private Long amount;

    private TransactionStatus status;
}
