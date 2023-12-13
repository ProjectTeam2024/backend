package kr.project.backend.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.project.backend.common.Environment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final String TOKEN_PREFIX = "Bearer ";
    private final String TOKEN_TYPE = "/api/" + Environment.API_VERSION;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("--- security jwt access ::: ");

        try {
            String headerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
            String token = null;

            //TODO jwt 인증후 SecurityContext 인증 객체 저장

        }catch (Exception e){

            //TODO jwt 인증 실패시 후처리

        }

        chain.doFilter(request,response);
    }
}
