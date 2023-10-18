package kr.centero.common.user.mapper;

import kr.centero.common.user.domain.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();

    void save(User user);

    void deleteAll();
}
