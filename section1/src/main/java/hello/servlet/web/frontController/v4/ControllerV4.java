package hello.servlet.web.frontController.v4;

import java.util.Map;

public interface ControllerV4 {

    /**
     *
     * @param paramMap
     * @param model
     * @return viewname
     * @throws Exception
     */
    String process(Map<String, String> paramMap, Map<String, Object> model) throws Exception;

}
