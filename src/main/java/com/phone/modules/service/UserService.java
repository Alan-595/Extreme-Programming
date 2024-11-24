package com.phone.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phone.commons.r.Result;
import com.phone.modules.domain.User;

/**
 * <p>
 * 电话表 服务类
 * </p>
 */
public interface UserService extends IService<User> {

    Result<?> saveUser(User user);

    Result<?> updateUser(User user);

}
