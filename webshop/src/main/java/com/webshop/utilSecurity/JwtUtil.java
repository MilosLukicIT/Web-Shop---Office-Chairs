package com.webshop.utilSecurity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.naming.AuthenticationException;

import org.springframework.stereotype.Component;

import com.webshop.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {

	private final String secret_key = "CtmFk2Jz9TfuQzacFkIjdTM19t9nfxEe";
	private long accessTokenValidity = 60*60*1000;
	
	
	private final JwtParser jwtParser;
	
	private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";
    
    public JwtUtil() {
    	this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }
    
    
    public String createToken(User user) {
    	Claims claims = Jwts.claims().setSubject(user.getEmail());
    	claims.put("id", user.getUserId());
    	claims.put("role", user.getRole());
    	
    	Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        
    	return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }
    
    private Claims parseJwtClaims(String token) {
    	return jwtParser.parseClaimsJws(token).getBody();
    }
    
    
    public Claims resolveClaims(HttpServletRequest request) {
    	try {
    		String token = resolveToken(request);
    		
    		if(token != null) {
    			return parseJwtClaims(token);
    		}
    		return null;
    	} catch (ExpiredJwtException ex) {
            request.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception e) {
        	request.setAttribute("invalid", e.getMessage());
            throw e;
		}
    }


	public String resolveToken(HttpServletRequest request) {
		
		String bearerToken = request.getHeader(TOKEN_HEADER);
		if(bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
			return bearerToken.substring(TOKEN_PREFIX.length());
		}
		return null;
	}
	
	public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getEmail(Claims claims) {
        return claims.getSubject();
    }
}
