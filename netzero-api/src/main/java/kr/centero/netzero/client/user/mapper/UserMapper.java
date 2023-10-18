package kr.centero.netzero.client.user.mapper;

import kr.centero.netzero.client.user.domain.model.User;
import kr.centero.netzero.common.mybatis.pagination.MybatisPageResponse;
import kr.centero.netzero.common.mybatis.pagination.PageMapper;
import kr.centero.netzero.common.mybatis.pagination.PageResponse;
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
