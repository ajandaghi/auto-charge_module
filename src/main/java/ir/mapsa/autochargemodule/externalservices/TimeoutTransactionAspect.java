package ir.mapsa.autochargemodule.externalservices;

import ir.mapsa.autochargemodule.converters.TransactionConverter;
import ir.mapsa.autochargemodule.services.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Service
@PropertySource("classpath:requestedURL.properties")
@Aspect
public class TimeoutTransactionAspect {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    DepositWalletResponse restResponse;
    @Value("${failed.inquiry.url}")
    private String failedInquiryUrl;

    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionConverter transactionConverter;

    @Autowired
    DepositWalletService depositWalletService;



    @AfterThrowing(value = "execution(* ir.mapsa.autochargemodule.externalservices.DepositWalletService.deposit(..))", throwing = "exception")

    public void checkTimedOutTransactions(JoinPoint jp, Exception exception) throws Exception {

//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        if (requestAttributes instanceof ServletRequestAttributes) {
//            HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
//        }
        Signature signature=jp.getSignature();
        DepositWalletRequest[] objects=(DepositWalletRequest[])jp.getArgs();
        depositWalletService.retryDeposit(objects[0]);

    }



}
