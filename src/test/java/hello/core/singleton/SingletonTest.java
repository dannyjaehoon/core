package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 di 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 트래픽이 초당 100이면 100개의 객체가 생성되었다가 삭제됨 == 메모리 낭비가 심함
        // 해결 방식으로는 싱글톤 == (객체 1개만 생성하여 사용) 을 사용하면됨

        //1. 조회 : 호출할때 마다 객체 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 조회 : 호출할때 마다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);

        System.out.println("memberService2 = " + memberService1);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {

        //1. 조회 : 호출할때 마다 객체 생성
        SingletonService singletonService1 = SingletonService.getInstance();
        //2. 조회 : 호출할때 마다 객체 생성
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);

        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainerTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        Assertions.assertThat(memberService1).isSameAs(memberService2);

    }
}
