package com.phone.modules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phone.commons.r.Result;
import com.phone.commons.r.ResultCodeEnum;
import com.phone.modules.dao.UserDao;
import com.phone.modules.domain.User;
import com.phone.modules.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 电话表 服务实现类
 * </p>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private FilesService filesService;
    @Override
    public Result<?> saveUser(User user) {
        if(ObjectUtils.isNotEmpty(user.getFile())){
            String path = filesService.uploadSingleFile(user.getFile());
            user.setImage(path);
        }
        if (baseMapper.insert(user) > 0) {
            return Result.success();
        }
        return Result.fail(ResultCodeEnum.INSERT_ERROR);
    }


    @Override
    public Result<?> updateUser(User user) {
        if(ObjectUtils.isNotEmpty(user.getFile())){
            String path = filesService.uploadSingleFile(user.getFile());
            user.setImage(path);
        }

        if (baseMapper.updateById(user) > 0) {
            return Result.success();
        }
        return Result.fail(ResultCodeEnum.UPDATE_ERROR);
    }

}
