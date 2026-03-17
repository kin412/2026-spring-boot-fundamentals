package hello.servlet.web.frontController.v5.adapter;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.v3.ControllerV3;
import hello.servlet.web.frontController.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }


    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controllerV3 = (ControllerV3) handler;

        Map<String, String> paramMap = createParamMap(request);

        try {
            ModelView mv = controllerV3.process(paramMap);
            return mv;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        //파라미터니까 헤더정보라던지 이런건 안나옴. 그저 쿼리파라미터나 메시지바디만
        request.getParameterNames().asIterator().forEachRemaining(
                paramName -> System.out.println("paramName : " + paramName
                        + " || request.getParameter(paramName) : " + request.getParameter(paramName)));
        return paramMap;
    }

}
