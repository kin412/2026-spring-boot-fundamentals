package hello.servlet.web.frontController.v3;

import hello.servlet.web.frontController.ModelView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap) throws Exception;

}
