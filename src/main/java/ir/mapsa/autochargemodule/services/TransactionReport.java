package ir.mapsa.autochargemodule.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.mapsa.autochargemodule.exceptions.ServiceException;
import ir.mapsa.autochargemodule.externalservices.UserAuthorizer;
import ir.mapsa.autochargemodule.models.entities.TransactionEntity;
import ir.mapsa.autochargemodule.repositories.TransactionRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Service
public class TransactionReport {


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private Report report;

    @Autowired
    private UserAuthorizer userAuthorizer;


    public List<TransactionEntity> getReport(Report report) throws JsonProcessingException, ServiceException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=null;
        String token="";
        if (requestAttributes instanceof ServletRequestAttributes) {
            request = ((ServletRequestAttributes) requestAttributes).getRequest();
            token=request.getHeader("Authorization");
        }

            if (ParserJwt.getAllFromToken(token).getRole().equals("ROLE_USER")) {
                System.out.println("true");
                return transactionRepository.findByTransDateAfterAndTransDateBeforeAndUser(report.getFromDate(), report.getToDate(), ParserJwt.getAllFromToken(token).getSub());// ParserJwt.getAllFromToken(report.getToken()).getSub();
            } else if (ParserJwt.getAllFromToken(token).getRole().equals("ROLE_ADMIN")) {
                return transactionRepository.findByTransDateAfterAndTransDateBeforeAndUser(report.getFromDate(), report.getToDate(), report.getUser());//, report.getUser());
            }

            return null;

    }
}