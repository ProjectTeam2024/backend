package kr.project.backend.service;

import kr.project.backend.dto.StakingInfoResponseDto;
import kr.project.backend.entity.StakingInfo;
import kr.project.backend.repository.StakingInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StakingInfoService {
    private final StakingInfoRepository stakingInfoRepository;
    public List<StakingInfoResponseDto> getStakingInfos() {
        return stakingInfoRepository.findAll().stream().map(StakingInfoResponseDto::new).collect(Collectors.toList());
    }
}
