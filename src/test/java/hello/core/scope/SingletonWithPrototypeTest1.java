package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    // 싱글톤 빈을 만들때 안에 주입되는 프로토타입빈은 이미 주입된 빈이기때문에 새로 객체를 반환하는 프로토타입빈이 아니라 싱글톤안에서 관리되는 이미 주입받은 프로토타입 빈이다.

    @Test
    void prototypeFind() {
        // 두개를 넣어주는 이유는 저 두개의 객체를 빈으로 자동등록시켜주기 때문임
        // ac 는 저 두 빈의 대한 정보를 가지고있음
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean1.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(2);
        // 프로토타입 빈이라서 1이되어야되지만 안에 있는 빈은 원래 할당된 빈이기 때문에 계속 같은걸 사용함
        // 우리의 의도는 프로토타입 빈의 특징처럼 사용할때마다 새로 생성해서 사용하길 원함

    }

    @Scope("singleton")
    static class ClientBean {
        private final PrototypeBean1 prototypeBean1;

        @Autowired
        private Provider<PrototypeBean1> prototypeBeanProvider;


        // @Autowired
        // private ObjectProvider<PrototypeBean1> prototypeBeanProvider;

        // 어플리캐이션 컨텍스트를 가지고 logic이 불릴때마다 새로운 프로토타입빈을 가져올수 있지만 스프링에 너무 의존적이게 된다.
        // 단위테스트도 어려워지고 스프링컨테이너에 종속적인 코드가 된다.
        // @Autowired
        // private ApplicationContext ac;

        @Autowired
        public ClientBean(PrototypeBean1 prototypeBean1) {
            this.prototypeBean1 = prototypeBean1;
        }

        public int logic() {
            // PrototypeBean1 prototypeBean1 = ac.getBean(PrototypeBean1.class);

            //PrototypeBean1 prototypeBean1 = prototypeBeanProvider.getObject();
            // 필요할때마다 프로토타입 빈을 찾아서 반환해주기때문에 간결하다 하지만 스프링에서 가져오기 때문에 스프링 의존적이다.
            // Dependency Lookup 이라는 기능이다

            PrototypeBean1 prototypeBean1 = prototypeBeanProvider.get();
            // 자바에서 제공하는 inject를 디팬던시에 추가하고
            prototypeBean1.addCount();
            int count = prototypeBean1.getCount();
            return count;
        }
    }
    @Scope("prototype")
    static class PrototypeBean1 {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("prototype.init");
        }

        @PreDestroy
        public void destory(){
            System.out.println("prototype.destory");
        }
    }
}
