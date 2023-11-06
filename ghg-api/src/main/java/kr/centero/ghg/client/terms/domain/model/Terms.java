package kr.centero.ghg.client.terms.domain.model;

import kr.centero.core.common.pagination.PageModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Alias("Terms")
@EqualsAndHashCode(callSuper = true)
public class Terms extends PageModel {

    private int termsId;
    private String serviceType;
    private int version;
    private String termsType;
    private String title;
    private String content;
    private Date openedAt;
    private String isUse;
    private String isRequired;
    private int displayOrder;
    private Date createDt;
    private String createId;
    private Date modifyDt;
    private String modifyId;
    private String versionList;

    public Terms() {
        // SuperBuilder를 사용하면 기본 생성자가 필요하다.
    }
}
