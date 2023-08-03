package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);

        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A 사용자 10000원 주문
        int price = statefulService1.order("userA", 10000);
        //ThreadB : B 사용자가 20000원 주문
        statefulService1.order("userB", 20000);

        //ThreadA : A 사용자 주문금액조회
        System.out.println(price); // 20000만원이 나옴


    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}