package ir.mapsa.autochargemodule.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto extends AbstractDto{

    private  String walletId;
    private Long minimumBalance;


}
