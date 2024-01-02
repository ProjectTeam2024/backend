package kr.project.backend.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.project.backend.common.Environment;
import kr.project.backend.auth.ServiceUser;
import kr.project.backend.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final String TOKEN_PREFIX = "Bearer ";
    private final String TOKEN_TYPE = "/api/" + Environment.API_VERSION;

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            String headerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
            String token = null;

            //header에 인증 token 검사
            if (StringUtils.isEmpty(headerToken)) {
                chain.doFilter(request, response);
                return;
            } else {
                token = headerToken.substring(TOKEN_PREFIX.length());
            }

            //token 유효성검사
            if (JwtUtil.isExpired(token, jwtSecretKey)) {
                chain.doFilter(request, response);
                return;
            }

            //jwt decode
            ServiceUser serviceUser = JwtUtil.decode(token, jwtSecretKey);

            //인증
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(serviceUser, null,
                    Collections.singletonList(new SimpleGrantedAuthority("USER")));

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authenticationToken);

        }catch (ExpiredJwtException e){

            Map<String,String> res = new LinkedHashMap<>();
            ObjectMapper objectMapper = new ObjectMapper();

            res.put("code","8000");
            res.put("msg","토큰이 만료되었습니다.");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print(objectMapper.writeValueAsString(res));
            response.flushBuffer();
            return;

        }catch (Exception e){

            Map<String,String> res = new LinkedHashMap<>();
            ObjectMapper objectMapper = new ObjectMapper();

            res.put("code","9999");
            res.put("msg","server error 발생");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print(objectMapper.writeValueAsString(res));
            response.flushBuffer();
            return;

        }

        chain.doFilter(request,response);
    }
}
