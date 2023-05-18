package ir.mapsa.autochargemodule.externalservices;

import feign.Headers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;


@FeignClient(name = "walletBalance",url = "${wallet.host.ip}" )
public interface Balance {


    @PostMapping("${balance.inquiry.path}")
    BalanceResponse getBalance(@RequestHeader(name = "Authorization") String token, BalanceRequest balanceRequest);

//    @Headers("Authorization")
//    String returnHeader();
//    }
}

