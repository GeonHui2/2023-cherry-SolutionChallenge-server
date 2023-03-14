package GDSCSCH.cherry.global.config;

import GDSCSCH.cherry.global.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin().disable().cors().and().csrf().disable();

        http.authorizeRequests()
                .expressionHandler(expressionHandler())
                .antMatchers("/user/signIn", "/user/testSignIn", "/user/signUp", "/user/testSignUp", "/user/getSiteInfo",
                        "/admin/signIn", "/admin/testSignIn", "/admin/signUp", "/admin/testSignUp", "/admin/getSiteInfo",
                        "/defaultSiteCheck/createCheck", "/defaultSiteCheck/edit/{defaultCheckId}", "/defaultSiteCheck/{defaultCheckId}").permitAll()
                .anyRequest()
                .authenticated();


        http.apply(new FilterConfig(jwtTokenProvider, objectMapper));

        return http.build();
    }

    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_STAFF > ROLE_USER > ROLE_GUEST");
        return roleHierarchy;
    }

    @Bean
    public DefaultWebSecurityExpressionHandler expressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }
}
