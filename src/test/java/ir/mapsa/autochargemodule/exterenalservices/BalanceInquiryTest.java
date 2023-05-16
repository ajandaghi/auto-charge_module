package ir.mapsa.autochargemodule.exterenalservices;


import ir.mapsa.autochargemodule.externalservices.BalanceRequest;
import ir.mapsa.autochargemodule.externalservices.DirectDebit;
import ir.mapsa.autochargemodule.services.ParserJwt;
import ir.mapsa.autochargemodule.services.ProfileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BalanceInquiryTest {

    @Value("${balance.inquiry.url}")
    private String balanceInquiryUrl;
    @Autowired
    private ProfileService profileService;

    @Mock
    private DirectDebit directDeposit;
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
