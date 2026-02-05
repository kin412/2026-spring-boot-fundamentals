package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
@controller - 뷰이름 반환
@restcontroller - 문자 그자체를 반환 - http 메시지 바디에 그 문자를 넣어서 전송
 */

@Slf4j
@RestController
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name="spring";
        System.out.println("name : " + name);

        //로그 레벨 설정가능
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        // 이렇게 쓰면 안됨. 이렇게 되면 결과는 위와 같지만,
        // 둘을 합치는(+) 과정에 서버리소스가 쓰인다. 로그는 수없이 쌓이기때문에 리소스 낭비가 생긴다.
        //log.info("info log={}"+name);

        return "ok";
    }

}
