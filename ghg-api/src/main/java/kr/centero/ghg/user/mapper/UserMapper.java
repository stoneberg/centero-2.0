package kr.centero.ghg.user.mapper;

import kr.centero.ghg.common.mybatis.pagination.MybatisPageResponse;
import kr.centero.ghg.common.mybatis.pagination.PageMapper;
import kr.centero.ghg.common.mybatis.pagination.PageResponse;
import kr.centero.ghg.user.domain.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends PageMapper {

    List<User> findUserByCond(User user);

    default PageResponse<User> findUserPageByCond(User user) {
        prepare(user.getPageNo(), user.getPageSize());
        return new MybatisPageResponse<>(findUserByCond(user));
    }

    void save(User user);

    void update(User user);

    void deleteAll();

}
