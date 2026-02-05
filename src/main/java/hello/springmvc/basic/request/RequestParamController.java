package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void reqeustParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String reqeustParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ){
        log.info("memberName={}, memberAge={}", memberName, memberAge);
        return "ok";
    }

    //3.x  버전대에서는 @RequestParam String memberName 이런식으로 생략하면 안됨.
    //엄격 하게 검사 하기때문에 무조건@RequestParam("username") String memberName 이런식으로 해줘야함
    //설정 - 빌드도구 - gradle 에 intellyJ 세팅 두개 gradle로 바꾸면 잘됨
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String reqeustParamV3(
            @RequestParam String username,
            @RequestParam int age
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String reqeustParamV4(
            String username,
            int age
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String reqeustParamRequired(
            @RequestParam(required = true) String username, // required = true  이게 꼭들어와야함. 없으면 400
            @RequestParam(required = false) Integer age // required = false 없어도됨.
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String reqeustParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username, // defaultValue = 빈문자까지 처리해줌
            @RequestParam(required = false, defaultValue = "-1") Integer age // required = false 없어도됨.
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String reqeustParamMap(@RequestParam Map<String, Objects> paramMap){
        log.info("username={}, age={}",paramMap.get("username") ,paramMap.get("age") );
        return "ok";
    }

}
