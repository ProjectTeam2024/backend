package kr.project.backend.service.coin;

import kr.project.backend.dto.coin.StakingInfoDetailResponseDto;
import kr.project.backend.dto.coin.StakingInfoListResponseDto;
import kr.project.backend.entity.coin.StakingInfo;
import kr.project.backend.exception.CommonErrorCode;
import kr.project.backend.exception.CommonException;
import kr.project.backend.repository.coin.StakingInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class StakingInfoService {
    private final StakingInfoRepository stakingInfoRepository;

    @Cacheable(value = "stakingInfoList")
    @Transactional(readOnly = true)
    public List<StakingInfoListResponseDto> getStakingInfos() {
        return stakingInfoRepository.findAllByCreatedDateBetween(
                LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .stream().map(StakingInfoListResponseDto::new).collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public StakingInfoDetailResponseDto getStakingInfo(UUID stakingId) {
        StakingInfo stakingInfo = stakingInfoRepository.findById(stakingId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_COIN.getCode(), CommonErrorCode.NOT_FOUND_COIN.getMessage()));

        return new StakingInfoDetailResponseDto(stakingInfo);
    }
}

