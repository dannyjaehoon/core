package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

//public class NetworkClient implements InitializingBean, DisposableBean
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }
    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }
    @PostConstruct
    public void init() { // 객체생성+의존성 주입이 끝난 후 자동 호출
        connect();
        call("초기화 연결");
    }
    @PreDestroy
    public void destroy() { // 객체생성+의존성 주입이 끝난 후 자동 호출
        disconnect();
    }
    // PostConstruct, PreDestory를 쓰면된다. 만약 외부 라이브러리에 사용하고 싶은경우에는 @Bean(initMethod = "init", destroyMethod = "destroy") 를 사용하자

//    @Override
//    public void afterPropertiesSet() throws Exception { // 객체생성+의존성 주입이 끝난 후 자동 호출
//        connect();
//        call("초기화 연결");
//    }
//
//    @Override
//    public void destroy() throws Exception { // 빈이 종료될때 호출
//        disconnect();
//    }
}
