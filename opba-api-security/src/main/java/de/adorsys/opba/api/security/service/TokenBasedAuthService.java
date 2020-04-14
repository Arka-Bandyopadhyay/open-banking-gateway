package de.adorsys.opba.api.security.service;

import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import de.adorsys.opba.api.security.config.TppTokenProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenBasedAuthService {

    private final JWSHeader jwsHeader;
    private final JWSSigner jwsSigner;
    private final JWSVerifier verifier;
    private final TppTokenProperties tppTokenProperties;

    @SneakyThrows
    public String generateToken(String subject) {
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneOffset.UTC);
        Duration duration = tppTokenProperties.getKeyValidityDuration();
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .expirationTime(Date.from(currentTime.plus(duration).toInstant()))
                .issueTime(Date.from(currentTime.toInstant()))
                .subject(String.valueOf(subject))
                .build();
        SignedJWT signedJWT = new SignedJWT(jwsHeader, claims);
        signedJWT.sign(jwsSigner);
        return signedJWT.serialize();
    }

    @SneakyThrows
    public String validateTokenAndGetSubject(String token) {
        if (token == null) {
            throw new IllegalArgumentException("Missing token");
        }

        SignedJWT jwt = SignedJWT.parse(token);

        if (!jwt.verify(verifier)) {
            throw new IllegalArgumentException("Wrong token");
        }

        if (Instant.now().isAfter(jwt.getJWTClaimsSet().getExpirationTime().toInstant())) {
            throw new IllegalArgumentException("Expired token");
        }

        return jwt.getJWTClaimsSet().getSubject();
    }
}
