package kr.centero.common.client.member.service;

import kr.centero.common.client.member.domain.entity.Member;
import kr.centero.common.client.member.repository.MemberRepository;
import kr.centero.core.common.exception.BusinessErrorCode;
import kr.centero.core.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    /**
     * redis test
     */

    private final MemberRepository memberRepository;


    public Member save(Member member) {

        Optional<Member> registeredUser = memberRepository.findMemberByMemberId(member.getMemberId());
        if (registeredUser.isPresent()) {
            throw new BusinessException(BusinessErrorCode.USER_ALREADY_EXISTS);
        }

        return memberRepository.save(member);
    }

    public Member update(String memberId, Member member) {
//        Optional<Member> registeredUser = Optional.ofNullable(memberRepository.findMemberByMemberId(memberId)
//                .orElseThrow(() -> new BusinessException(BusinessErrorCode.USER_NOT_FOUND)));
//
//        if (registeredUser.isPresent()) {
//            member = memberRepository.save(member);
//        }
//        return member;

        // delete first
        Optional<Member> existMember = Optional.ofNullable(memberRepository.findMemberByMemberId(memberId)
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.USER_NOT_FOUND)));

        log.info("[ZET]delete member: {}", existMember.get());
        memberRepository.delete(existMember.get());

        // save new
        Optional<Member> registeredUser = memberRepository.findMemberByMemberId(member.getMemberId());
        if (registeredUser.isPresent()) {
            throw new BusinessException(BusinessErrorCode.USER_ALREADY_EXISTS);
        }

        return memberRepository.save(member);
    }

    public Member findMemberByMemberId(String memberId) {
        Optional<Member> member = Optional.ofNullable(memberRepository.findMemberByMemberId(memberId)
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.USER_NOT_FOUND)));

        log.info("[ZET]find member: {}", member.get());

        return member.get();
    }

    public void remove(String memberId) {
//        Optional<Member> member = Optional.ofNullable(memberRepository.findMemberByMemberId(memberId)
//                .orElseThrow(() -> new BusinessException(BusinessErrorCode.USER_NOT_FOUND)));
//
//        log.info("[ZET]delete member: {}", member.get());
//        memberRepository.delete(member.get());

        // delete if exist
        memberRepository.findMemberByMemberId(memberId).ifPresent(memberRepository::delete);

    }
}
