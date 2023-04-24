package com.hbrg;

import com.hbrg.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class HbrgApplicationTests {

    @Autowired
    private BoardService hbrg_boardService;

//    @Test
//    void testJpa() {
//        for (int i = 1; i <= 100; i++) {
//            String title = String.format("테스트 데이터입니다:[%03d]", i);
//            String Txt = "내용무";
//            this.hbrg_boardService.create(title, Txt);
//        }
//    }
}
