package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototype1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototype2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // 프로토 타입 빈의 특징
        // 스프링 컨테이너에 요청할때 마다 새로 생성된다.
        // 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입 그리고 초기화까지만 관여한다.
        // 종료 메서드가 호출되지 않는다.
        // 그래도 프로토 타입 빈은 포로토타입 빈을 조회한 클라이언트가 관리해야 한다. 종료 메서드에 대한 호출도 클라이언트가 직접해야된다.
        ac.close(); // 프로토타입 빈은 스프링이 객체생성 + 의존성 주입 + 초기화까지 해주고 클라이언트가 그 다음부터 관리해야된다. 따라서 ac.close()도 클라이언트가 해줘야된다.
    }


    @Scope("prototype")
    class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("Prototype.init");
        }

        @PreDestroy
        public void destory(){
            System.out.println("Prototype.destory");
        }
    }
}
