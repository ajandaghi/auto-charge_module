package ir.mapsa.autochargemodule.exterenalservices;


import com.fasterxml.jackson.core.JsonProcessingException;
import ir.mapsa.autochargemodule.externalservices.BalanceRequest;
import ir.mapsa.autochargemodule.externalservices.DirectDeposit;
import ir.mapsa.autochargemodule.models.entities.ProfileEntity;
import ir.mapsa.autochargemodule.repositories.ProfileRepository;
import ir.mapsa.autochargemodule.services.AbstractService;
import ir.mapsa.autochargemodule.services.ParserJwt;
import ir.mapsa.autochargemodule.services.ProfileService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BalanceInquiryTest {

    @Value("${balance.inquiry.url}")
    private String balanceInquiryUrl;
    @Autowired
    private ProfileService profileService;

    @Mock
    private DirectDeposit directDeposit;
    @Mock
    private BalanceRequest balanceRequest;

    @BeforeAll
    public static void creatProfileService() {

    }


    @Test
    public void findFromToken() throws Exception {
        Assertions.assertEquals(ParserJwt.getAllFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcmVyem9vZWVvIiwicm9sZSI6IlJPTEVfVVNFUiIsImFjY291bnROdW1iZXIiOiI2MzYyMTQxMDU2NzIzMDY1NTgiLCJleHAiOjE2ODQwNjEwODEsImlhdCI6MTY4NDA1NzQ4MSwiY2FyZE51bWJlciI6IjYzNjIxNDEwNTY3MjMwNjgiLCJqdGkiOiJlMzc5NzNmODA4N2Q0MTc2OWE3MTllYzM1MDQ1NmVhNCJ9.yBofO_uT_r50bwQyAZhzcMvmrPeAPavJLGrwowLy7DhUDLycBJxyvE5hmWrOzSKT95gTmafX4ERzIcjnNRCaSg").getSub(), "arerzooeeo");
        Assertions.assertEquals(ParserJwt.getAllFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcmVyem9vZWVvIiwicm9sZSI6IlJPTEVfVVNFUiIsImFjY291bnROdW1iZXIiOiI2MzYyMTQxMDU2NzIzMDY1NTgiLCJleHAiOjE2ODQwNjEwODEsImlhdCI6MTY4NDA1NzQ4MSwiY2FyZE51bWJlciI6IjYzNjIxNDEwNTY3MjMwNjgiLCJqdGkiOiJlMzc5NzNmODA4N2Q0MTc2OWE3MTllYzM1MDQ1NmVhNCJ9.yBofO_uT_r50bwQyAZhzcMvmrPeAPavJLGrwowLy7DhUDLycBJxyvE5hmWrOzSKT95gTmafX4ERzIcjnNRCaSg").getAccountNumber(), "636214105672306558");
    }

    @Test
    public void checkUser() throws Exception {
        Assertions.assertEquals(profileService.findById("arerzooeeo").get().getMinimumBalance(), 2000000L);
    }
    @Test
    public void shouldThrowException(){

    }


}
