package com.yangwenjie.delayqueue.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Yang WenJie
 * @date 2018/1/27 下午9:18
 */
@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/fun1", method = RequestMethod.POST)
    public String fun1() {

        log.info("fun1");

        return "success";
    }
}
