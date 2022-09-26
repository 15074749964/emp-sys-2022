package cn.kgc.user.service;

import cn.kgc.common.vo.Result;
import cn.kgc.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kgc
 * @since 2022-07-18
 */
public interface IUserService extends IService<User> {

    Result<Map<String, Object>> login(User param);

    Result<Map<String, Object>> getUserInfo(String token);

    void logout(String token);
}
