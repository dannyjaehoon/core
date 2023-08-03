package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        // ApplicationContext > ConfigurableApplicationContext > AnnotationConfigApplicationContext
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        NetworkClient client = ac.getBean(NetworkClient.class);

        ac.close();

    }

    @Configuration
    static class LifeCycleConfig {
        //@Bean(initMethod = "init", destroyMethod = "destroy")
        @Bean
        public NetworkClient networkClient() {
            // 라이프 사이클 : 객체 생성 -> 의존관계 주입, 생성자 주입은 제외
            // 스프링 빈의 이벤트 라이프 싸이클
            // 스프린 컨테이너 생성-> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소명전 콜백 -> 스프링 종료 // 싱글톤에 대한 주기

            // 객체의 생성과 초기화를 분리하는게 좋다. 객체 생성할때는 생성하는거에 포커스를 두고 동작이랑 값을 넣어주는건 따로 하는게 좋다.
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
