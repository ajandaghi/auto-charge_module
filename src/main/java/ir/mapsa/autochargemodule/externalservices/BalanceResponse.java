package ir.mapsa.autochargemodule.externalservices;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceResponse {
    private String walletId;
    private Long balance;
}
