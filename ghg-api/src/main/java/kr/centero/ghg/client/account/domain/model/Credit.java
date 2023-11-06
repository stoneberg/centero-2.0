package kr.centero.ghg.client.account.domain.model;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("Credit")
@NoArgsConstructor
@AllArgsConstructor
public class Credit {
    private String trdSeq;
    private String trdParentSeq;
    private String pjtSeq;
    private String pjtMntSeq;
    private String pjtCreditSeq;
    private String trdType;
    private String trdTpNm;
    private String creditNoRep;
    private String creditNo;
    private String orgCreditNoRep;
    private String orgCreditNo;
    private String creditPpntId;
    private String creditFrDt;
    private String creditToDt;
    private String creditPeriod;
    private String bufferType;
    private String bufferTypeNm;
    private String creditSize;
    private String ghgProg;
    private String pjtTitle;
    private String delYn;
    private String certUrl;
    private String refSlsSeq;
    private String bufferRate;
    private String createDt;
}
