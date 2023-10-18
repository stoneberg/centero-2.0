package kr.centero.common.user.mapper;

import kr.centero.common.common.mybatis.pagination.MybatisPageResponse;
import kr.centero.common.common.mybatis.pagination.PageMapper;
import kr.centero.common.common.mybatis.pagination.PageResponse;
import kr.centero.common.user.domain.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper extends PageMapper {

    List<User> findAllByCond(User user);

    default PageResponse<User> findUserPageByCond(User user) {
        prepare(user.getPageNo(), user.getPageSize());
        return new MybatisPageResponse<>(findAllByCond(user));
    }

    Optional<User> findUserByCond(@Param("userId") Long userId, User user);

    void save(User user);

    void update(@Param("userId") Long userId, User user);

    void deleteAll();

}
