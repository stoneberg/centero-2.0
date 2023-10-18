package kr.centero.ghg.auth.service;

import kr.centero.ghg.auth.domain.model.BlacklistAccessLog;
import kr.centero.ghg.auth.domain.model.IpBlacklist;
import kr.centero.ghg.auth.mapper.IpBlacklistMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IpBlacklistService {
    private final IpBlacklistMapper ipBlacklistMapper;

    // IP 블랙리스트에서 IP를 조회
    public IpBlacklist findByIpAddress(@Param("ipAddress") String ipAddress) {
        return ipBlacklistMapper.findByIpAddress(ipAddress);
    }

    // IP 블랙리스트 전체를 조회
    public List<IpBlacklist> findIpBlacklist() {
        return ipBlacklistMapper.findIpBlacklist();
    }

    // IP 블랙리스트에 IP를 추가하는 메소드
    @Transactional
    public void insertIpBlacklist(@Param("ipAddress") String ipAddress) {
        ipBlacklistMapper.insertIpBlacklist(ipAddress);
    }

    // IP 블랙리스트에서 IP를 삭제하는 메소드
    @Transactional
    public void deleteByIpBlacklist(@Param("ipAddress") String ipAddress) {
        ipBlacklistMapper.deleteByIpBlacklist(ipAddress);
    }

    // IP 블랙리스트에 IP가 있는지 확인하는 메소드
    public boolean existsIpBlacklist(@Param("ipAddress") String ipAddress) {
        return ipBlacklistMapper.existsIpBlacklist(ipAddress);
    }

    // 블랙리스트 IP 접근 로그 저장
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void insertBlacklistAccessLog(BlacklistAccessLog log) {
        ipBlacklistMapper.insertBlacklistAccessLog(log);
    }

}
