package pers.xiaomo.demo.dubbo.service.impl;

import pers.xiaomo.demo.dubbo.common.Result;
import pers.xiaomo.demo.dubbo.dto.UserDto;
import pers.xiaomo.demo.dubbo.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private Map<Long,UserDto> map = new HashMap<>();
    private List<UserDto> list = new ArrayList<UserDto>();

    public UserServiceImpl() {
        {
            UserDto userDto = new UserDto();
            userDto.setId(1L);
            userDto.setLoginName("weiguangyue");
            userDto.setNickName("魏广跃");

            this.list.add(userDto);
        }

        {
            UserDto userDto = new UserDto();
            userDto.setId(1L);
            userDto.setLoginName("xiaomo");
            userDto.setNickName("萧默");

            this.list.add(userDto);
        }

        for(UserDto userDto : this.list){
            this.map.put(userDto.getId(),userDto);
        }
    }

    @Override
    public Result<List<UserDto>> queryAll() {

        Result<List<UserDto>> result = new Result<List<UserDto>>();
        result.setSuccess(true);
        result.setData(this.list);
        return result;
    }

    @Override
    public Result<UserDto> queryByUid(Long uid) {

        Result<UserDto> result = new Result<UserDto>();
        result.setSuccess(true);
        result.setData(this.map.get(uid));
        return result;

    }

}
