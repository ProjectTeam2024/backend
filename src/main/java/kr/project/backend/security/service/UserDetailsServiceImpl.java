package kr.project.backend.security.service;

import kr.project.backend.security.model.ServiceUser;
import kr.project.backend.user.account.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * security userDetails -> ServiceUser set
 * @author kh
 * @version v1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        //TODO : 회원정보 조회 로직 필요
        User user = new User();

        if(user == null){
            new UsernameNotFoundException("user not find");
        }

        ServiceUser serviceUser = new ServiceUser();

        //TODO : 조회된 회원정보 세팅 필요(임시 데이터 set)
        serviceUser.setUserId("CS00000001");
        serviceUser.setUserName("홍길동");
        serviceUser.setEmail("test-user@eamil.com");
        serviceUser.setPassword("avanclkd213cmwk12nabyiwermzz");

        log.info("@@@@@@@@@@@@@@@@@");

        return serviceUser;
    }
}
