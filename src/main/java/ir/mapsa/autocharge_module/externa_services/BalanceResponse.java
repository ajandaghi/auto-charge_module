package ir.mapsa.autocharge_module.externa_services;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceResponse {
    private String walletId;
    private Long balance;
}
