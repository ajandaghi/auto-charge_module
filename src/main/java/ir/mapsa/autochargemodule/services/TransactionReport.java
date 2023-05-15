package ir.mapsa.autochargemodule.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.mapsa.autochargemodule.exceptions.ServiceException;
import ir.mapsa.autochargemodule.externalservices.UserAuthorizer;
import ir.mapsa.autochargemodule.models.entities.TransactionEntity;
import ir.mapsa.autochargemodule.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionReport {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private Report report;

    @Autowired
    private UserAuthorizer userAuthorizer;


    public List<TransactionEntity> getReport(Report report) throws JsonProcessingException, ServiceException {


        if (ParserJwt.getAllFromToken(report.getToken()).getRole().equals("ROLE_USER")) {
            return transactionRepository.findByTransDateBetweenAndUser(report.getFromDate(), report.getToDate(), ParserJwt.getAllFromToken(report.getToken()).getSub());
        } else if (ParserJwt.getAllFromToken(report.getToken()).getRole().equals("ROLE_ADMIN")) {
            return transactionRepository.findByTransDateBetweenAndUser(report.getFromDate(), report.getToDate(), report.getUser());
        }

        return null;
    }
}