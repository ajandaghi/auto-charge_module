package ir.mapsa.autochargemodule.externalservices;

import ir.mapsa.autochargemodule.models.entities.DealType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Messages {
    private String walletId;
    private String token;
    private Long amount;
    private DealType dealType;

}
