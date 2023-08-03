package hello.core.order;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        // 수정자 주입으로 할경우에는 memberRepository, discountPolicy 의존성 주입이 필요하다.
        // OrderServiceImpl orderService = new OrderServiceImpl();
    }

}