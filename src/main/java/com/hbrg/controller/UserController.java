package com.hbrg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.hbrg.dto.UserFormDto;
import com.hbrg.entity.Huser;
import com.hbrg.model.KakaoProfile;
import com.hbrg.model.OAuthToken;
import com.hbrg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


import java.util.UUID;

import javax.validation.Valid;

@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserController {

    @Value("${cos.key")
    private String cosKey;

    @Autowired
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;


    @GetMapping("/new")
    public String userForm(Model model) {
        model.addAttribute("userFormDto", new UserFormDto());
        return "users/userForm";
    }


    @PostMapping(value="/new")
    public String newUser(@Valid UserFormDto userFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "users/userForm";
        }

        try{
            Huser user = Huser.createHUser(userFormDto, passwordEncoder);
            userService.saveUser(user);
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "users/userForm";
        }
        //회원가입 성공시 로그인 페이지로 이동
        return "redirect:/user/login";
    }

    @GetMapping(value="/login")
    public  String loginHUser() {
        return "/users/userLoginForm";
    }

    @GetMapping(value="/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해 주세요.");
        return "/users/userLoginForm";
    }


    @GetMapping(value = "/auth/kakao/callback")
    public String kakaoCallback(String code){    //Data를 리턴해주는 컨트롤러 함수

        //POST 방식으로 key=value 데이터를 요청( 카카오쪽으로)
        //a태그를 이용할수 없음, POST 이기 때문
        //이때 필요한 RestTemplate 라이브러리를 사용해서 http 요청을 편하게
        RestTemplate rt= new RestTemplate();

        //HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "cf4f271e8fd61d1f1504a1f182eff601");
        params.add("redirect_uri", "http://localhost:8080/user/auth/kakao/callback");
        params.add("code", code);

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params,headers);

        //Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        // Gson, Json Simple, ObjectMapper 라이브러리중 ObjectMapper를 사용해서
        // JSON 데이터를 처리하기
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;

        try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        System.out.println("카카오 엑세스 토큰 : " + oAuthToken.getAccess_token());

        RestTemplate rt2 = new RestTemplate();

        //HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
                new HttpEntity<>(headers2);
        //params를 제거하고 headers만 넘겨줘도 오버로딩이 되어있어서 만들어집니다.

        //Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );


        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;

        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // User오브젝트 : username, password, email 이 필요
        System.out.println("카카오 아이디(번호): " + kakaoProfile.getId());
        System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());

        System.out.println("게시판서버 유저네임: " + kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
        System.out.println("게시판서버 이메일: " + kakaoProfile.getKakao_account().getEmail());

        //쓰레기값이 들어간 임시 비밀번호
        //UUID란 중복되지 않는 어떤 특정값을 만들어내는 알고리즘.
//        UUID garbagePassword = UUID.randomUUID();
        System.out.println("게시판서버 패스워드: " + cosKey);

        //임시 닉네임
        UUID tempNic = UUID.randomUUID();
        System.out.println("게시판 임시 닉네임: " + tempNic);

        String kakaoEmail = kakaoProfile.getKakao_account().getEmail();

        String kakaoId = kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId();

        String kakaoNic = tempNic.toString();



        //값을 받아서 USERFORMDTO에 넣어서 저장
        UserFormDto userFormDto = new UserFormDto(kakaoId, kakaoEmail, cosKey, kakaoNic);
        Huser kakaoUser = Huser.createHUser(userFormDto, passwordEncoder);

        try {
            userService.saveUser(kakaoUser);
            System.out.println("중복이메일 없음, 회원가입 완료");
        } catch (IllegalStateException e) {
            // 이미 등록된 이메일이나 Id가 있는 경우 처리
            System.out.println("이미 등록된 이메일, 가입취소");
        }

        System.out.println("자동 로그인을 진행합니다.");

        return "redirect:/";
    }
}
