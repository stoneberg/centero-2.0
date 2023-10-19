package kr.centero.ghg.client.user.mapper;

import kr.centero.ghg.client.user.domain.model.User;
import kr.centero.ghg.common.mybatis.pagination.MybatisPageResponse;
import kr.centero.ghg.common.mybatis.pagination.PageMapper;
import kr.centero.ghg.common.mybatis.pagination.PageResponse;
import org.apache.ibatis.annotations.Mapper;

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
