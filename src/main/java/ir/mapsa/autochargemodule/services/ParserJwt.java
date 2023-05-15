package ir.mapsa.autochargemodule.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ParserJwt {
    public static JwtPayloadObj getAllFromToken(String token) throws JsonProcessingException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        JwtPayloadObj jwtObj = new ObjectMapper().readValue(payload, JwtPayloadObj.class);

        return  jwtObj;
    }

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println(getAllFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcmVyem9vZWVvIiwicm9sZSI6IlJPTEVfVVNFUiIsImFjY291bnROdW1iZXIiOiI2MzYyMTQxMDU2NzIzMDY1NTgiLCJleHAiOjE2ODQxNDQyNjYsImlhdCI6MTY4NDE0MDY2NiwiY2FyZE51bWJlciI6IjYzNjIxNDEwNTY3MjMwNjgiLCJqdGkiOiI0ZmJkYmI3NjU5ZmY0NTZmOTc5OGNjNmY5MTJmMGYzOCJ9.QqbvACVepYaaevxlDUrqaOwKVoEJcBu2omVh26aoFy4SJqwqZ7fVrx7dWcy7_naaocsMkFrrhMKxncQmDpOG4g"));
    }
}


