package ir.mapsa.autochargemodule.services;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Service
@AllArgsConstructor
@ToString
public class Report
{
    private Date fromDate;
    private Date toDate;
    private String user;
   // private String token;
}