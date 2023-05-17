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
    private String token;
    private String sourceAccountNumber;
    private final String DestinationAccountNumber="1000000000";
    private Long amount;
}
