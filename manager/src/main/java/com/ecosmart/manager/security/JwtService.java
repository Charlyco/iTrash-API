package com.ecosmart.manager.security;

import com.ecosmart.manager.data.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtService {
    private static final String SECRET_KEY = "Nvu3Aqf78ohFnNinPN8fMHVz0l8KXq/ID66M6dyuwYomcNCvbnYkTtNiyWik9YzNz6RAH21Yn2Skh5t+pLbxhYzrp/2AdnEfvSWoVMrf+yt3b2Q+Z84K9fNEk7CntuR2DGpnTC3WeULKDXTM54OqNopklRcu4g+hOdqKs4zz/jSrhoWKGL7OOV14UGcxmJmplaVauUw+MjSol/Iq0Tg/IWt8O9R9k3rgthtJGaGHaItwWHNJZswq5AOQFcUwigt6jS2e2/41qsZDk8yk+JlbezIdp9Z24gXALrv1mV2geLcJFQYjfVFyzGszDDXb6KmHS2f6UZZakQwutEh0AVt6qQNr+UwH8dJFay7LdkQNC3f6C5gMFRsFoZDWcbcEulQtRi36uLWpUCOzK8qHJ3cMU/yNbxhizib/nOTBpu/OstCVRelZKHKx7cPPESC4cqQ9R7oIfpSfhUECPWbW1dJnmtKilLoDkNcnG43RbLzpSALle8wEGNDuWt4Q0RU4hhoJF2VxQ/XKusgTikUTKGLLA+8MP+fKyW8yLcqH9ntnxx12etQ1Xiub/sTkY4VIbN/zMgCfMXXhwWjBqIen6JrNoyA7KxRI2Epepaf/qOZJR2i5oR3MdxrzM6AgVmV1cbkukg+KV/fJwT4/1lrZTf5OfEk2qfDnP4/d4cSGpH3Vtl3at7Jqpv8kmBVj9RErzMq6XkWGJxcakC7u3QxBC0SoxcFbSoHg1Jymn69BPUgyktw+Os7xbrJub1DxJMBOOSmR6A2xt7udYsWrudLDgAdft6Ao+O6oBSsNVGjERB7LnHb84CgQ4l/w5YEbXjBd5KrAX7KOWSX9eX444T/kCGeDFs11/VrLUo3YNsjvkCBjPR/m1WEQE9/XFMxvzTCoooYoAo50N5orXrYFeq46zpLs6TbRfgugW/nDetHAWbZfCndDgH9GBMW+LOlRGKXc+pRDMMWvu5H4uUGh0mBfED9JLv41auReK3T4vEOxsDsuQBrha7N1WDab00dyQMGtdHncnnXiWAWlbfYhX07uCU6p7GdEj3kDb+58A6rTkuhMtBEE7/t8DE+9nta/qbp10YycMRfJdnNWbMLuw2imc7pKQ3Po5RqflzU4RwNX4frCYS1A+IbHEarir+qhMcam4u9uMWI63zyL3cecRj9WS0afmuDbWndCaYjwjtc8zomFAyTNML9XUxS6YxJKq/M+vyJyHaZN2e8Qkgialu4TLdbUNgE37nGFtqPrKdsa0Ms0ELdKeGi9ENmhRr/YSkxyDR91RBKttVZryXtFQihEv3Zrg7z2ZlvZIJohE03CSSbFA/2vyG9P70XjmiRZHIDmw4jyIAaWSW+nEbAVjhBcShrbvLG8C928cc5OgPQROHdfAaU=";

    public String extractUserName(String token) {

        return extractSingleClaim(token, Claims::getSubject);
    }

    public <T> T extractSingleClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extraAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extraAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            User user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(User user) {
        return generateToken(new HashMap<>(), user);
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractSingleClaim(token, Claims::getExpiration).before(new Date());
    }

}
