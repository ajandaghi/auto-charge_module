package ir.mapsa.autochargemodule.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mapsa.autochargemodule.exceptions.ServiceException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Service
public class ParserJwt {


    @Autowired
    private  ObjectMapper objectMapper;

    public  JwtPayloadObj getAllFromToken(String token)  {
       // System.out.println(token);

        //System.err.println("=======================");

        if (token.startsWith("Bearer")){
            token = token.substring(8);
        }
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String[] chunks = token.split("\\.");

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));


        try {
            return objectMapper.readValue(payload , JwtPayloadObj.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }



  //  @PostConstruct
    public void test() throws ParseException, ServiceException, JsonProcessingException {
      //  System.out.println(transactionReport.getReport(new Report(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2023-05-14 11:00:00"), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2023-05-17 12:47:00"), "arerzooeeo", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcmVyem9vZWVvIiwicm9sZSI6IlJPTEVfVVNFUiIsImFjY291bnROdW1iZXIiOiI2MzYyMTQxMDU2NzIzMDY1NTgiLCJleHAiOjE2ODQxNDQyNjYsImlhdCI6MTY4NDE0MDY2NiwiY2FyZE51bWJlciI6IjYzNjIxNDEwNTY3MjMwNjgiLCJqdGkiOiI0ZmJkYmI3NjU5ZmY0NTZmOTc5OGNjNmY5MTJmMGYzOCJ9.QqbvACVepYaaevxlDUrqaOwKVoEJcBu2omVh26aoFy4SJqwqZ7fVrx7dWcy7_naaocsMkFrrhMKxncQmDpOG4g")));

    }
}


