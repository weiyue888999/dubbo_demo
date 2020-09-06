package pers.xiaomo.demo.dubbo.service;

import pers.xiaomo.demo.dubbo.common.Result;
import pers.xiaomo.demo.dubbo.dto.UserDto;

import java.util.List;

/**
 * 用户服务
 */
public interface UserService {

    Result<List<UserDto>> queryAll();

    Result<UserDto> queryByUid(Long uid);
}
