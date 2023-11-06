package kr.centero.core.common.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Base Request Class For Payload
 */
@Getter
@Setter
public abstract class BaseRequest {
    private Integer pageNo = 1; // offset for pagination
    private Integer pageSize = 10; // limit for pagination
    private String orderBy = null; // "user_id DESC, age ASC"
    private String locale= "ko"; // "ko"
    private String zoneId = "Asia/Seoul"; // "Asia/Seoul"
    private String centeroUserId; // 추가
    private List<String> centeroUserRoles; // 추가
}
