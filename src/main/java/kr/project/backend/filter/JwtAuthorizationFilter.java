package kr.project.backend.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.project.backend.common.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final String TOKEN_PREFIX = "Bearer ";
    private final String TOKEN_TYPE = "/api/" + Environment.API_VERSION;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("@@@@@@@ NO T111OKEN ::: ");

        try {
            String headerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
            String token = null;
        }catch (Exception e){
            System.out.println("@@@@@@@ NO TOKEN ::: ");
        }

        chain.doFilter(request,response);

    }
}
