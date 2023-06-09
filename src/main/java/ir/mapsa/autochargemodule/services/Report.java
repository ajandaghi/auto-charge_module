package ir.mapsa.autochargemodule.services;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Report
{
    private Date fromDate;
    private Date toDate;
    private String walletId;
    private String user;
   // private String token;
}