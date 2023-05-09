package ir.mapsa.autochargemodule.externalservices;

import ir.mapsa.autochargemodule.models.entities.TransactionStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class WalletResponse {
    private TransactionStatus status;
    private Long balance;
}
