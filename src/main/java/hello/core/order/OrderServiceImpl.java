package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final이 붙은 변수를 받는 생성자를 만들어줌
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;

    // DicountPolicy에 의존하는 FixDiscountPolicy랑 RateDiscountPolicy 두개의 빈이 있다.
    // 자동 의존성 주입할때 2개의 빈때문에 에러가 난다. 문제는 하나를 지목하면 구체화된 객체에 의존하게 된다. 항상 추상화된 객체에 의존해야되는데 그게 문제임
    // 이런 경우 3가지 방법으로 해결함
    // 1. @Autowired에 필드명 매칭 -> private final DiscountPolicy rateDiscountPolicy : 타입매칭으로 가져오지만 타입매칭이 2개이상일경우 필트명, 패라미터 명으로 빈 이름 매칭으로 가져옴
    // 2. @Quilifier끼리 매칭 -> 빈이름 매칭,  각 컴포넌트에 @Qualifier("컴포넌트 이름") 으로 이름지어줄수 있음
    //    사용법 :  public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy)
    // 3. @Primary 사용
    //    사용방법: 우선순위를 가진 컴포넌트에 @Primary를 붙인다.

    private final DiscountPolicy discountPolicy;
    // final은 생성자 주입에서만 사용가능하다. final로 지정을 했을경우 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에서 막아준다
    // 컴파일오류로 나오는게 제일 빠르고 좋은 오류다!
    // 수정자 주입을 포함한 나머지 주입방식은 모두 생성자 이후에 호출되므로 필드에 final 키워드를 사용할수 없다.

    // 생성자 주입방식을 선택하는 이유는
    // 프레임워크에 의존하지 않고, 순수한 자바언어의 특징을 잘 살리는 방법이다.
    // 기본으로 생성자 주입을 선택하고 필수 값이 아닌 경우에는 수정자 주입 방식을 옵션으로 부여하면 된다. 생성자 윕과 수정자 주입을 동시에 사용할수 있다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId,itemName,itemPrice, discountPrice);
    }
}
