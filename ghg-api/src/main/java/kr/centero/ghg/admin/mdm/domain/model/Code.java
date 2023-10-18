package kr.centero.ghg.admin.mdm.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Code")
@NoArgsConstructor
@AllArgsConstructor
public class Code {
    private String codeCd;
    private String codeNm;
    private String codeLvl;
    private String pCodeCd;
    private String dspOrder;
    private String useYn;
    private String expFrDt;
    private String expToDt;
    private String attr1Json;
    private String attr1Val;
    private String attr2Json;
    private String attr2Val;
    private String attr3Json;
    private String attr3Val;
    private String attr4Json;
    private String attr4Val;
    private String attr5Json;
    private String attr5Val;
    private String attr6Json;
    private String attr6Val;
    private String attr7Json;
    private String attr7Val;
    private String attr8Json;
    private String attr8Val;
    private String attr9Json;
    private String attr9Val;
    private String attr10Json;
    private String attr10Val;
}
