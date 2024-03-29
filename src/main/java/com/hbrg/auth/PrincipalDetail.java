package com.hbrg.auth;//package com.hbrg.auth;
//
//import com.hbrg.entity.HUser;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import static com.hbrg.entity.QHUser.hUser;
//
//import lombok.Data;
//
////  스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
//// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
//@Data
//public class PrincipalDetail implements UserDetails{
//    private HUser hUser; // 콤포지션
//
//    public PrincipalDetail(HUser hUser) {
//        this.hUser = hUser;
//    }
//
//    @Override
//    public String getPassword() {
//        return hUser.getPw();
//    }
//
//    @Override
//    public String getUsername() {
//        return hUser.getId();
//    }
//
//    // 계정이 만료되지 않았는지 리턴한다. (true: 만료안됨)
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    // 계정이 잠겨있지 않았는지 리턴한다. (true: 잠기지 않음)
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    // 비밀번호가 만료되지 않았는지 리턴한다. (true: 만료안됨)
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    // 계정이 활성화(사용가능)인지 리턴한다. (true: 활성화)
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    // 계정이 갖고있는 권한 목록을 리턴한다. (권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 우리는 한개만)
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//        Collection<GrantedAuthority> collectors = new ArrayList<>();
//        collectors.add(()->{ return "ROLE_"+hUser.getRole();});
//
//        return collectors;
//    }
//
//}