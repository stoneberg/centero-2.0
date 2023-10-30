package kr.centero.common.client.member.web;

import kr.centero.common.client.member.domain.entity.Member;
import kr.centero.common.client.member.service.MemberService;
import kr.centero.core.common.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/v1/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * redis test
     */


    // save
    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody Member member) {
        Member createdMember = memberService.save(member);
        log.info("[ZET]save member: {}", createdMember);
        return ApiResponse.of(createdMember);
    }

    // update
    @PutMapping("/{memberId}")
    public ResponseEntity<ApiResponse> update(@PathVariable String memberId, @RequestBody Member member) {
        Member updatedMember = memberService.update(memberId, member);
        log.info("[ZET]update member: {}", updatedMember);
        return ApiResponse.of(updatedMember);
    }

    // find by memberId
    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse> findMemberByMemberId(@PathVariable String memberId) {
        Member member = memberService.findMemberByMemberId(memberId);
        log.info("[ZET]find member: {}", member);
        return ApiResponse.of(member);
    }

    // find by id
//    @GetMapping("/{id}")
//    public ResponseEntity<ApiResponse> findById(@PathVariable Long id) {
//        Member member = memberService.findById(id);
//        return ApiResponse.ok(member);
//    }

    // delete
    @DeleteMapping("/{memberId}")
    public ResponseEntity<ApiResponse> remove(@PathVariable String memberId) {
        memberService.remove(memberId);
        return ApiResponse.ok();
    }




}
