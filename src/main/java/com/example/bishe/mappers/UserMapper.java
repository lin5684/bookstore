package com.example.bishe.mappers;

import com.example.bishe.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.javassist.runtime.Inner;

@Mapper
public interface UserMapper {
//@Insert("insert into bs_user values (null,#{username},#{name},#{sex},#{email},#{phone},#{address},#{role})")
    public int insertUser(User user);
//@Select()
    public int updateUser(User user);

    public User selectUser(String username);

    public User getUserById(Integer id);

    public int deleteUser(Inner id);
}
