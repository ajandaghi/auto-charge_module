package ir.mapsa.autochargemodule.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.mapsa.autochargemodule.exceptions.ServiceException;
import ir.mapsa.autochargemodule.services.Report;
import ir.mapsa.autochargemodule.services.TransactionReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auto-charge/report")
public class ReportController
{
    @Autowired
    private TransactionReport transactionReport;

    @PostMapping()
    public void getReport(@RequestBody Report report) throws ServiceException, JsonProcessingException {
        transactionReport.getReport(report);
    }
}