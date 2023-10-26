package kr.centero.common.client.member.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@RedisHash("member")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member implements Serializable {

    @Id
    private Long id;

    @Indexed
    private String memberId;

    private String password;

    private String email;

    public Member(String memberId, String password, String email) {
        this.memberId = memberId;
        this.password = password;
        this.email = email;
    }
}
