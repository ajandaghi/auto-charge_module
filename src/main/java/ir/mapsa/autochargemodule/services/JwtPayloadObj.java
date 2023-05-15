package ir.mapsa.autochargemodule.services;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class JwtPayloadObj {
    private String sub;
    private String role;
    private String accountNumber;
    private String cardNumber;
    private String jti;
    private Date exp;
    private Date iat;
}
