package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        log.info("call resolver={}", ex);

        try {
            if (ex instanceof IllegalArgumentException) { // IllegalArgumentException이 터지면 400으로 보내겠다.
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                // 리턴을 정상적으로 modelandview로 해서 was로 보냄.
                // 그럼 500에러지만 was는 400에러라고 인식하고 화면이 출력되서 에러역시 400이 되어버림.
                return new ModelAndView();
            }
        } catch(IOException e){
            log.error("IOException resolver to 400", e);
        }

        return null;

    }
}
