package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // 객체를 생성하고 연결하는 역할을 함
    // 객체를 생성할때 AppConfig를 통해서 함

    // ApplicationContext 안에 annotationConfigApplicationContext는 annotatedBeanDefinitionReader를 통해서 AppConfig.class를 읽고 BeanDefinition을 생성한다.
    // BeanDefinition 은 빈에 대한 설정정보를 가지고 있음
    // BeanDefinition은 두가지 방법으로 만들수있음 : 직접적으로 스프링빈을 등록하는 방법과 factorybean이라는 것을 이용하여 만듬
    // 일반적으로 @Bean으로 하는건 factorybean으로 BeanDefinition을 만드는 개념

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository(), new RateDiscountPolicy()
    // 논리적으로는 다른 객체여야되지만 똑같은 주소값이 나옴

    // memberService, memberRepository, orderService 순으로 한번씩만 불름
    // @Configuration을 붙이면 AppConfig로 자식 객체를 만들어서 사용하여 만약 메모리에 객체가 올라와있으면 있는 객체를 반복하는 원리로 싱글톤을 지킴
    // @Configuration을 지우면 AppConfig가 자식 객체를 만들지 않고 객체 생성때마다 메모리에 있는지 확인하지않고 새로운 객체 생성 (스프링빈으로 등록은 되지만 싱글톤은 보장되지 않음)


    @Bean
    public MemberService memberService() {

        // memberapp에서 서비스 구현체를 인스턴스화했을때 AppConfig를 이용해서 사용함.
        // 따라서 AppConfig에서 서비스 구현체가 생성될때 어떤 레파짓토리로 할건지 결정
        return new MemberServiceImpl(memberRepository());
        // 생성자 주입
    }

    @Bean
    public static MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // 오더 서비스에서 멤버 레포지토리랑 디스카운트 폴리시를 가져오는데 디스카운트 폴리시의 역할을 바꾸기 위해서는 아래 코드만 수정해주면 끝
        return new RateDiscountPolicy();
    }
}
