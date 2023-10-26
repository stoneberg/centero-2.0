package kr.centero.common.client.member.repository;

import kr.centero.common.client.member.domain.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {

    Optional<Member> findMemberByMemberId(String memberId);

}
