package com.hbrg.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class UserFormDto {

    @NotBlank(message="아이디는 필수 입력 값 입니다.")
    private  String id;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String nic;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String pw;

    public UserFormDto(String id, String email, String pw, String nic) {
        this.id = id;
        this.email = email;
        this.pw = pw;
        this.nic = nic;
    }
}
