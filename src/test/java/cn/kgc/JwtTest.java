package cn.kgc;

import cn.kgc.common.utils.JwtUtil;
import cn.kgc.user.entity.User;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class JwtTest {

    @Test
    public void test(){
        String jti = UUID.randomUUID()+"";
        User user = new User();
        user.setUsername("zhangsan");
        user.setRole("admin");
        String jwt = JwtUtil.createJwt(jti, user);
        System.out.println(jwt);
    }
}
