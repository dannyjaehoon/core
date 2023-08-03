package hello.core.member;

import hello.core.AppConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() { // 테스트 마다 한번씩 실행됨
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member member = new Member(1l,"memberA", Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1l);

        //then


    }

}
