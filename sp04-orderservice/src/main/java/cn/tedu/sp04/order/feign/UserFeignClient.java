package cn.tedu.sp04.order.feign;

import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", fallback = UserFeignClientFB.class)
public interface UserFeignClient {
    @GetMapping("/{userId}")
    JsonResult<User> getUser(@PathVariable("userId") Integer userId);
    @GetMapping("/{userId}/score")
    JsonResult<?> addScore(@PathVariable("userId") Integer userId,@PathVariable("score") Integer score);
}
