package hello.aop.internalcall;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/*
 구조를 변경(분리)
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV3 {

    /*private final ObjectProvider<CallServiceV3> callServiceProvider;

    public CallServiceV3(ObjectProvider<CallServiceV3> callServiceProvider) {
        this.callServiceProvider = callServiceProvider;
    }*/

    private final InternalService internalService;

    public void external(){
        log.info("call external");
        //CallServiceV3 callServiceV2 = callServiceProvider.getObject();
        internalService.internal(); //메서드 분리 후 호출
    }

    //별도의 클래스로 분리 후 주입받기
    /*public void internal(){
        log.info("call internal");
    }*/

}
