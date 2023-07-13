package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamCotroller {


    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = {}, age = {}", name, age);

        response.getWriter().write("OKOK");
    }

    @ResponseBody
    @RequestMapping("request-param-v2")
    public String requestParamV2(@RequestParam("username") String username,
                                 @RequestParam("age") int age) {

        log.info("username = {}, age = {}", username, age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("request-param-v3")
    public String requestParamV3(@RequestParam String username,  // () 생략하고 이름 맞추면 된다.
                                 @RequestParam int age) {

        log.info("username = {}, age = {}", username, age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("request-param-v4")
    public String requestParamV4(String username, int age) {  // String, int, Integer 등의 단순타입이면 @RequestParam도 생략 가능)
                                                                //  하지만 애노테이션마저 없으면 조금 과하다는 생각도 든다.
        log.info("username = {}, age = {}", username, age);
        return "OK";
    }


    @ResponseBody
    @RequestMapping("request-param-required")
    public String requestParamV5(@RequestParam(required = true) String username,  // 기본값이 true
                                 @RequestParam(required = false) Integer age) {   // int -> Integer 변환해야 null 가능

        log.info("username = {}, age = {}", username, age);
        return "OK";
    }

    @ResponseBody
    @RequestMapping("request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){

        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "OK";
    }
}
