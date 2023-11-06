package kr.centero.ghg.client.credit.domain.model;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("CreditList")
@NoArgsConstructor
@AllArgsConstructor
public class CreditList {
	private String trdSeq;
	private String trdPSeq;
	private String pjtSeq;
	private String pjtMntSeq;
	private String pjtCreditSeq;
	private String slsSeq;
	private String ghgProgram;
	private String ghgProgramNm;
	private String ghgProgramEngNm;
	private String trdTp;
	private String trdKorTp;
	private String pjtTitle;
	private String pjtTitleEng;
	private String proponentNm;  //제안자
	private String proponentNmEng;  
	private String creditProponentNm; // 소유자
	private String creditProponentNmEng; 
	private String creditSize;
	private String creditPeriod;
	private String pjtCreditNo;
	private String salesYn;
	private String etc;	
}

