package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// control+r 하면 실행됨
class ReateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 서비스 구현체 단위로 테스트를 하는건지?
    // asserThat이 사라짐.. 어떤걸로 테스트를?
    @Test
    @DisplayName("10% discount for VIP")
    void vip_o() {
        //given
        Member member = new Member(1L,"memberVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then

    }

    @Test
    @DisplayName("No discount for BASIC")
    void vip_ㅌ() {
        //given
        Member member = new Member(2L,"memberBASIC", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then

    }
}