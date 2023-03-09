package GDSCSCH.cherry.global.security.jwt;

import GDSCSCH.cherry.domain.admin.domain.RefreshToken;
import GDSCSCH.cherry.domain.admin.domain.Role;
import GDSCSCH.cherry.domain.admin.domain.repository.RefreshTokenRepository;
import GDSCSCH.cherry.domain.user.domain.repository.UserRepository;
import GDSCSCH.cherry.global.security.auth.AuthUserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    // 키
    @Value("${jwt.secret}")
    private String secretKey;

    private final long accessTokenValidTime = 1000L * 60 * 60 * 6;
    private final long refreshTokenValidTime = 1000L * 60 * 60 * 24 * 30;

    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthUserService authUserService;
    private final UserRepository userRepository;

    // Access Token 생성
    public String createAccessToken(String email, Role role){
        return this.createToken(email, accessTokenValidTime, role);
    }

    // Refresh Token 생성
    public String createRefreshToken(String email, Role role){
        return this.createToken(email, refreshTokenValidTime, role);
    }

    // Create Token
    public String createToken(String email, long tokenValid, Role role){
        Claims claims = Jwts.claims().setSubject(email); // claims 생성 및 payload 설정
        claims.put("role", role);

        Date date = new Date();
        return Jwts.builder()
                .setClaims(claims) // 발행 유저 정보 저장
                .setIssuedAt(date) // 발행 시간 저장
                .setExpiration(new Date(date.getTime() + tokenValid)) // 토큰 유효 시간 저장
                .signWith(SignatureAlgorithm.HS256, secretKey) // 해싱 알고리즘 및 키 설정
                .compact(); // 생성
    }

    // JWT에서 인증 정보 조회
    public Authentication getAuthentication(String token){
        UserDetails userDetails = authUserService.loadUserByUsername(this.getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // 토큰에서 회원 정보 추출
    public Role getRole(String token) {
        return userRepository.findByUserEmail(this.getEmail(token)).get().getRole();
    }

    // Request의 Header에서 AccessToken 값을 가져옵니다. "Authorization" : "token'
    public String resolveAccessToken(HttpServletRequest request) {
        if(request.getHeader("Authorization") != null ) {
            return request.getHeader("Authorization").substring(7);
        }
        return null;
    }

    // Request의 Header에서 RefreshToken 값을 가져옵니다. "RefreshToken" : "token"
    public String resolveRefreshToken(HttpServletRequest request){
        if (request.getHeader("RefreshToken") != null){
            return request.getHeader("RefreshToken").substring(7);
        }
        return null;
    }

    // Jwt Token의 유효성 및 만료 기간 검사
    public boolean validateToken(String jwtToken) {

        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    // AccsessToken 헤더 설정
    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("Authorization", "bearer "+ accessToken);
    }

    // RefreshToken 헤더 설정
    public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
        response.setHeader("RefreshToken", "bearer "+ refreshToken);
    }

    // RefreshToken 존재유무 확인
    public boolean existsRefreshToken(String refreshToken) {
        return refreshTokenRepository.existsByRefreshToken(refreshToken);
    }
}
