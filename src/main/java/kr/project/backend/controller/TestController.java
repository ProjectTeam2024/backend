package kr.project.backend.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Cacheable(value = "test")
    @GetMapping("/")
    public String get(){
        return "마지막테스트!";
    }
    @GetMapping("/api/v1/test")
    public String get2(){
        return "스웨거 테스트";
    }
}
