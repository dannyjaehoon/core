package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private  final LogDemoService logDemoService;
    //private final ObjectProvider<MyLogger> myLogger;
    private final MyLogger myLogger;
    // MyLogger 클래스에 @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)을 넣어주면 가짜 프록시 객체를 만들어서 사용


    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestUrl = request.getRequestURI().toString();
        // myLogger는 스코프가 request이기때문에 화면에서 request를 받고 웹서버가 넘겨줄때까지 살아있는데. 스프링이 구동될때 불르기 때문에 에러발생
        // 첫번째 해결방향은 Provider로 해결하는것. 생성자 주입때가 아닌 사용할때 가져오기때문에 에러발생을 막음

        //MyLogger MyLoggerProvider = myLogger.getObject();
        myLogger.setRequestURL(requestUrl);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
