package kr.project.backend.user.coin.service;

import jakarta.transaction.Transactional;
import kr.project.backend.user.coin.model.StakingInfoResponseDto;
import kr.project.backend.user.coin.repository.StakingInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StakingInfoService {
    private final StakingInfoRepository stakingInfoRepository;

    @Cacheable(value = "stakingInfo")
    @Transactional
    public List<StakingInfoResponseDto> getStakingInfos() {
        return stakingInfoRepository.findAll().stream().map(StakingInfoResponseDto::new).collect(Collectors.toList());
    }
}