package ir.mapsa.autochargemodule.externalservices;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DirectRequest {
    private String accountNumber;
    private Long amount;
}
