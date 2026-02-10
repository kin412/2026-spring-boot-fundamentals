package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) throws IOException {
        model.addAttribute("data", "hello model");
        return "response/hello";
    }

    //관례적으로 가능하게 해주긴 하나 비추천함.
    @RequestMapping("response/hello")
    public void responseViewV3(Model model) throws IOException {
        model.addAttribute("data", "hello model");
    }

}
