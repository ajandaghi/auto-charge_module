package ir.mapsa.autochargemodule.externalservices;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositWalletRequest {
   private String token;
   private Long amount;

}
