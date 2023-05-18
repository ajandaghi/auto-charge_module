package ir.mapsa.autochargemodule;

import ir.mapsa.autochargemodule.converters.TransactionConverter;
import ir.mapsa.autochargemodule.externalservices.BalanceImpl;
import ir.mapsa.autochargemodule.externalservices.BalanceRequest;
import ir.mapsa.autochargemodule.externalservices.DepositImpl;
import ir.mapsa.autochargemodule.externalservices.DirectImpl;
import ir.mapsa.autochargemodule.services.ParserJwt;
import ir.mapsa.autochargemodule.services.ProfileService;
import ir.mapsa.autochargemodule.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AutoChargeModuleApplicationTests {
     @Mock
     private BalanceImpl balanceImpl;

    @Mock
    private ProfileService profileService;

    @Mock
    private DirectImpl directImpl;

    @Mock
    private ParserJwt parserJwt;

    @Mock
    private TransactionService transactionService;

    @Mock
    private DepositImpl depositImpl;

    @Mock
    private TransactionConverter transactionConverter;


    @Test
    public void checkBalanceResponse(){
        String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcmVyem9vZWVvIiwicm9sZSI6IlJPTEVfVVNFUiIsImFjY291bnROdW1iZXIiOiI2MzYyMTQxMDU2NzIzMDY1NTgiLCJleHAiOjE2ODQwNjEwODEsImlhdCI6MTY4NDA1NzQ4MSwiY2FyZE51bWJlciI6IjYzNjIxNDEwNTY3MjMwNjgiLCJqdGkiOiJlMzc5NzNmODA4N2Q0MTc2OWE3MTllYzM1MDQ1NmVhNCJ9.yBofO_uT_r50bwQyAZhzcMvmrPeAPavJLGrwowLy7DhUDLycBJxyvE5hmWrOzSKT95gTmafX4ERzIcjnNRCaSg";
        //BalanceRequest balanceRequest=new BalanceRequest(token);
        //Mockito.when(profileService.findById(parserJwt.getAllFromToken(token).getSub()).get().getMinimumBalance()).thenReturn(20000L);
        // balanceImpl.getBalance(balanceReque).getBalance();


    }

}
