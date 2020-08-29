package pers.xiaomo.demo.dubbo.service;

import pers.xiaomo.demo.dubbo.dto.Result;
import pers.xiaomo.demo.dubbo.dto.UserDto;

import java.util.List;

public interface UserService {

    Result<List<UserDto>> queryAll();

    Result<UserDto> queryByUid(Long uid);
}
