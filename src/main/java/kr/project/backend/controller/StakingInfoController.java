package kr.project.backend.controller;

import kr.project.backend.dto.StakingInfoResponseDto;
import kr.project.backend.service.StakingInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/staking")
public class StakingInfoController {

    private final StakingInfoService stakingInfoService;

    //@Cacheable(value = "test")
    @GetMapping("/infos")
    public ResponseEntity<List<StakingInfoResponseDto>> stakingInfos(){
        return ResponseEntity.ok(stakingInfoService.getStakingInfos());
    }
}
