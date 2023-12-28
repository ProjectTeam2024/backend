package kr.project.backend.user.coin.service;

import jakarta.transaction.Transactional;
import kr.project.backend.user.coin.model.StakingInfoResponseDto;
import kr.project.backend.user.coin.repository.StakingInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class StakingInfoService {
    private final StakingInfoRepository stakingInfoRepository;

    @Cacheable(value = "stakingInfo")
    @Transactional
    public List<StakingInfoResponseDto> getStakingInfos() {
        // DateTimeFormatter를 사용하여 원하는 형식으로 출력
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 현재 날짜의 시작 (00:00:00)
        LocalDateTime startDate = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIN);
        // 현재 날짜의 끝 (23:59:59)
        LocalDateTime endDate = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MAX);
        return stakingInfoRepository.findAllByCreatedDateBetween(startDate.format(formatter),endDate.format(formatter)).stream().map(StakingInfoResponseDto::new).collect(Collectors.toList());

    }
}
