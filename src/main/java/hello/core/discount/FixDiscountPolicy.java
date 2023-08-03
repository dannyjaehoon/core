package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")
@MainDiscountPolicy // 직접 만들어 씀 annotation package에 있음
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { //enum은 == 쓰는게 맞음
            return discountFixAmount;
        } else {
            return 0;
        }

    }
}
