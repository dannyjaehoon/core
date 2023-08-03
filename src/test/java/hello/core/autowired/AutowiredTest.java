package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        // Member는 스프링 빈이 아니다. 따라서 required= true 일 경우 널값때문에 에러가 난다.

        @Autowired(required = false) // false 일 경우 호출 자체가 안됨
        public void setNoBean1(Member noBean1) {
            System.out.println("No bean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("No bean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("No bean3 = " + noBean3);
        }
    }

}
