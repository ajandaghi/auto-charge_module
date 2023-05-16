package ir.mapsa.autochargemodule.externalservices;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepositWalletRequest {
   private String trackingId;
   private String token;
   private Long amount;

}
