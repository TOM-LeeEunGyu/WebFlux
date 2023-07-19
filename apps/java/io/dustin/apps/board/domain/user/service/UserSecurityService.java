package io.dustin.apps.board.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSecurityService {// implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        SiteUser siteUser = getEntity(userRepository.findBynickName(username), SiteUser.class, "사용자를 찾을 수 없습니다.");
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        if("admin".equals(username)){
//            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
//        } else {
//            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
//        }
//        return new User(siteUser.getNickName(), siteUser.getPassword(), authorities);
//    }


}
