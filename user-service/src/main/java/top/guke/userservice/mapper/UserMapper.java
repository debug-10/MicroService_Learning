package top.guke.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.guke.userservice.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> { }