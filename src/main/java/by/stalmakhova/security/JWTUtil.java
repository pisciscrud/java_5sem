package by.stalmakhova.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(String login) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());

        return JWT.create().withSubject("User details").withClaim("login", login).withIssuedAt(new Date()).withIssuer("Demo").withExpiresAt(expirationDate).sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveClaim(String token) {

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).withSubject("User details").withIssuer("Demo").build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("login").asString();
    }
}
