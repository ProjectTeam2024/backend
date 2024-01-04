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
        // DateTimeFormatter를 사용하여 원하는 형식으로 출력
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 현재 날짜의 시작 (00:00:00)
        LocalDateTime startDate = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIN);
        // 현재 날짜의 끝 (23:59:59)
        LocalDateTime endDate = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MAX);
        return stakingInfoRepository.findAllByCreatedDateBetween(startDate.format(formatter),endDate.format(formatter)).stream().map(StakingInfoListResponseDto::new).collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public StakingInfoDetailResponseDto getStakingInfo(UUID stakingId) {
        StakingInfo stakingInfo = stakingInfoRepository.findById(stakingId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_COIN.getCode(), CommonErrorCode.NOT_FOUND_COIN.getMessage()));

        return new StakingInfoDetailResponseDto(stakingInfo);
    }
}

