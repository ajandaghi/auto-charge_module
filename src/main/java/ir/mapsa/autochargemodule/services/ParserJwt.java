package ir.mapsa.autochargemodule.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Base64;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Service
public class ParserJwt {
    private static String getAllClaimsFromToken(String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        return  header;
    }

    public static void main(String[] args) {
        System.out.println(getAllClaimsFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcmVyem9vZWVvIiwiZXhwIjoxNjgzOTY3NjEyLCJpYXQiOjE2ODM5NjQ2MTJ9.wOf0dm7ui0dGZACX_5p0IsnzPEBQ4R2xjTZrdhxMgUkcHOSpb1By08YNwkCGtuJXgvySgQaUXvtV42zCD_RJEg"));
    }
}


