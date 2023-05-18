package ir.mapsa.autochargemodule.externalservices;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DirectRequest {
    private String walletId;
    private String token;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private Long amount;
}
