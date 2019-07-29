package com.yangwenjie.delayqueue.web;

import com.yangwenjie.delayqueue.dto.Result;
import com.yangwenjie.delayqueue.utils.SnowflakeIdUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yang WenJie
 * @date 2018/1/27 下午9:18
 */
@Api("延迟队列相关接口")
@RestController
@RequestMapping("/test")
public class TestController {

    private static SnowflakeIdUtil idUtil = new SnowflakeIdUtil(1, 1);

    @RequestMapping(value = "/fun1", method = RequestMethod.POST)
    public Result findPageInfo() {

        System.out.println("1111");

        return new Result();
    }
}
