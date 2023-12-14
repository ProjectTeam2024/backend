package kr.project.backend.user.coin.controller;

import io.swagger.v3.oas.annotations.Operation;
import kr.project.backend.common.Environment;
import kr.project.backend.user.coin.model.StakingInfoResponseDto;
import kr.project.backend.user.coin.service.StakingInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/" + Environment.API_VERSION + "/staking")
public class StakingInfoController {

    private final StakingInfoService stakingInfoService;

    @Operation(summary = "코인 스테이킹 목록 조회", description = "코인 스테이킹 목록 조회를 합니다.")
    @GetMapping("/infos")
    public ResponseEntity<List<StakingInfoResponseDto>> stakingInfos(){
        return ResponseEntity.ok(stakingInfoService.getStakingInfos());
    }
}
