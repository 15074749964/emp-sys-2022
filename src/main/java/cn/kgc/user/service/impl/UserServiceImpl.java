package cn.kgc.user.service.impl;

import cn.kgc.common.vo.Result;
import cn.kgc.user.entity.User;
import cn.kgc.user.mapper.UserMapper;
import cn.kgc.user.service.IUserService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kgc
 * @since 2022-07-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Result<Map<String, Object>> login(User param) {
        // 根据用户名查询用户数据
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",param.getUsername());
        User loginUser = this.baseMapper.selectOne(wrapper);
        // 为空则用户不存在
        if(loginUser == null){
            return Result.fail(20002,"用户名或密码错误");
        }
        // 不为空，则判断密码，密码正确则返回正常数据
        boolean matches = passwordEncoder.matches(param.getPassword(), loginUser.getPassword());
        if(!matches){
            return Result.fail(20003,"用户名或密码错误");
        }
        // 登录成功，需要产生token
        String token = "user:" + UUID.randomUUID()+""; //
        Map<String,Object> data = new HashMap<>();
        data.put("token",token);

        // 将登录信息存入redis: key:token,value:user
        redisTemplate.opsForValue().set(token,loginUser,30, TimeUnit.MINUTES);

        return Result.success(data);
    }

    @Override
    public Result<Map<String, Object>> getUserInfo(String token) {
        if(!StringUtils.hasLength(token)){
            return Result.fail(20004,"token无效");
        }

        // 根据token, 从redis中获取数据
        Object loginObj = redisTemplate.opsForValue().get(token);
        if(loginObj == null){
            return Result.fail(20006, "登录凭证无效");
        }

        User loginUser = JSON.parseObject(JSON.toJSONString(loginObj),User.class);

        Map<String, Object> data = new HashMap<>();
        data.put("name",loginUser.getUsername());
        data.put("roles", Arrays.asList(loginUser.getRole().split(",")));
        data.put("avatar",loginUser.getAvatar());

        return Result.success(data);
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }
}
