package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //basePackages = "hello.core.member", // 이렇게 하면 member안에 있는것만 빈으로 등록함
        //basePackageClasses = AutoAppConfig.class, // 지정한 클래스의 패키지를 탐색 시작위로 지정한다. 만약 지정하지 않으면 @ComponentScan이 붙은 설정정보 클래스의 패키지가 시작 위치가 된다.
        // 보통 프로젝트 시작루트에 AppConfig같은 메인 설정 정보를 두고 basePackages 지정은 생략한다.

        // 컴포넌트 스캔할때 제외하는것
        // AutoConfig파일때문에 Configuration.class를 제외 -> 예제코드 때문에 사용, 실무에서는 안씀
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // RateDiscountPolicy, MemeberServiceImpl등 실질적인 구현체에 @Component를 붙여주면 자동 빈 등록이 된다.

}
