package ir.mapsa.autochargemodule.externalservices;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class BalanceRequest {
    private String walletId;
    private String token;
}
