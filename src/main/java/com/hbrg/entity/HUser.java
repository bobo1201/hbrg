package com.hbrg.entity;

import com.hbrg.constant.Role;
import com.hbrg.dto.UserFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Huser")
@Getter
@Setter
@ToString
public class Huser  extends BaseEntity{

    @Id
    @Column(name="id", nullable = false)
    private String id; // 로그인 ID

    @Column(name="pw", nullable = false)
    private String pw; // 로그인 PW

//    @Column(name="pw2", nullable = false)
//    private String pw2; // 로그인 PW

    @Column(name="email", nullable = false)
    private String email; // 회원가입 이메일

    @Column(name="nic")
    private String nic; // 회원가입 닉네임

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Likes> likes = new ArrayList<>();

    public static Huser createUser (UserFormDto userFormDto, PasswordEncoder passwordEncoder){
        Huser user = new Huser();
        user.setId(userFormDto.getId());
        user.setEmail(userFormDto.getEmail());
        user.setNic(userFormDto.getNic());
//        user.setPw2(userFormDto.getPw2());
        user.setRole(Role.USER);

        //SecurityConfig의 BCryptPasswordEncoder Bean을 파라미터로 넘겨서 비밀번호 암호화
        String Password = passwordEncoder.encode(userFormDto.getPw());
        user.setPw(Password);

        return user;
    }
}
