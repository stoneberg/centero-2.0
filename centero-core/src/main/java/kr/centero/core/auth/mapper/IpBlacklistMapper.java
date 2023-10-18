package kr.centero.core.auth.mapper;

import kr.centero.core.auth.domain.model.BlacklistAccessLog;
import kr.centero.core.auth.domain.model.IpBlacklist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IpBlacklistMapper {
    // IP 블랙리스트에서 IP를 조회
    IpBlacklist findByIpAddress(@Param("ipAddress") String ipAddress);

    // IP 블랙리스트 전체를 조회
    List<IpBlacklist> findIpBlacklist();

    // IP 블랙리스트에 IP를 추가하는 메소드
    void insertIpBlacklist(@Param("ipAddress") String ipAddress);

    // IP 블랙리스트에서 IP를 삭제하는 메소드
    void deleteByIpBlacklist(@Param("ipAddress") String ipAddress);

    // IP 블랙리스트에 IP가 있는지 확인하는 메소드
    boolean existsIpBlacklist(@Param("ipAddress") String ipAddress);

    // 블랙리스트 IP 접근 로그 저장
    void insertBlacklistAccessLog(BlacklistAccessLog blacklistAccessLog);
}
