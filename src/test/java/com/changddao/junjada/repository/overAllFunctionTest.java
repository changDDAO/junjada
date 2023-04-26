package com.changddao.junjada.repository;

import com.changddao.junjada.entity.Address;
import com.changddao.junjada.entity.LaptopBoard;
import com.changddao.junjada.entity.LaptopReply;
import com.changddao.junjada.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Rollback(value = false)
@Transactional
class overAllFunctionTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LaptopBoardRepository laptopBoardRepository;

    @Autowired
    LaptopReplyRepository laptopReplyRepository;

    @Test
    public void simpleTest() {
        Member member1 = new Member("younch8342@naver.com","창따오","a12345",
                new Address("261","대구","청수로","캐슬"));
        memberRepository.save(member1);
        Member member2 = new Member("kimchi@naver.com","기무찌","adsp12345",
                new Address("423","서울","용산구","캐슬"));
        memberRepository.save(member2);

        //변경감지 detect modification
        Member findMember = memberRepository.findById(member1.getId()).orElse(null);
        findMember.changeNickname("바뀐 창따오");
    }

    @Test
    public void overallTest(){
    //given
        Member member1 = new Member("younch8342@naver.com","창따오","a12345",
                new Address("261","대구","청수로","캐슬"));
        memberRepository.save(member1);
        Member member2 = new Member("kimchi@naver.com","기무찌","adsp12345",
                new Address("423","서울","용산구","캐슬"));
        memberRepository.save(member2);

        LaptopBoard laptopBoard = new LaptopBoard("삼성갤럭시북",3000000,"삼성갤럭시북 추천"
        ,"정말 좋습니다. 정말 좋습니다. 정말 좋습니다.정말 좋습니다.정말 좋습니다. 정말 좋습니다.정말 좋습니다.정말 좋습니다." +
                "정말 좋습니다. 정말 좋습니다. 정말 좋습니다. 정말 좋습니다.정말 좋습니다.정말 좋습니다.정말 좋습니다.정말 좋습니다.");
        laptopBoard.setMember(member1);
        laptopBoardRepository.save(laptopBoard);
        //when

        LaptopReply laptopReply = new LaptopReply("헐 근데 가격이 너무 비싼거아닌가요?");
        laptopReply.setLaptopBoard(laptopBoard);
        laptopReply.setMember(member2);

        laptopReplyRepository.save(laptopReply);
        //then
        Assertions.assertThat(laptopReply.getMember().getNickName()).isEqualTo("기무찌");

    }



}