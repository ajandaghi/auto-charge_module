package ir.mapsa.autochargemodule.exterenalservices;

import ir.mapsa.autochargemodule.externalservices.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DirectDepositTest {
    @Mock
    private DepositWalletService depositWalletService;
    @InjectMocks
    private DirectDeposit directDeposit;
    @Mock
    private RestTemplate restTemplate;

    private DirectRequest directRequest;

    private DepositWalletService depositWallet;

    @Value("${bank.directDebit.url}")
    private String bankDirectDebitUrl;

    @Test
    public void testIfOk() throws Exception {

        HttpEntity<DirectRequest> request = new HttpEntity<>(directRequest);
        when(restTemplate.postForEntity(bankDirectDebitUrl, request, DirectResponse.class).getBody().getStatus().equals("Ok")).thenReturn(true);

    }
    @Test
    public void testIfNotOk() throws Exception {

        HttpEntity<DirectRequest> request = new HttpEntity<>(directRequest);
        when(restTemplate.postForEntity(bankDirectDebitUrl, request, DirectResponse.class).getBody().getStatus().equals("false")).thenReturn(false);

    }

}
