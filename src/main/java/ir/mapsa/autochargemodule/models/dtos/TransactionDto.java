package ir.mapsa.autochargemodule.models.dtos;

import ir.mapsa.autochargemodule.models.entities.TransactionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto extends AbstractDto{
    private String walletId;

    private  String user;

    private String trackingId;

    private Long amount;

    private TransactionStatus status;

    private Date  date;
}
