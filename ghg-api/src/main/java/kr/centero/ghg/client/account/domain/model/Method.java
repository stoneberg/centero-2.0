package kr.centero.ghg.client.account.domain.model;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("Method")
@NoArgsConstructor
@AllArgsConstructor
public class Method {
    private String mtdSeq;
    private String ghgProg;
    private String ghgNm;
    private String pjtTypeNm;
    private String ctryNm;
    private String regionNm;
    private String title;
    private String stsNm;
    private String aprvDt;
    private int pcmmtCnt;
}
