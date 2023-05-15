package ir.mapsa.autochargemodule.externalservices;

import ir.mapsa.autochargemodule.models.entities.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Messages {
    private String walletId;
    private String token;
    private Long amount;
    private TransactionType dealType;

}
