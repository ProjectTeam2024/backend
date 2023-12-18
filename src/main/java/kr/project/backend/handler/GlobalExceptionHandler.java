package kr.project.backend.handler;

import jakarta.servlet.http.HttpServletRequest;
import kr.project.backend.common.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = {"kr.project.backend"})
@RequiredArgsConstructor
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Void>> exceptionHandler(HttpServletRequest httpServletRequest, Exception e){

        Response<Void> r = new Response<>();
        r.setCode("9999");
        r.setMsg("server error 발생");

        return new ResponseEntity<>(r, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
