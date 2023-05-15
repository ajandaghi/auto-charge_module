package ir.mapsa.autochargemodule.services;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Data
@Service
public class Report
{
    private Date fromDate;
    private Date toDate;
    private String user;
    private String token;
}