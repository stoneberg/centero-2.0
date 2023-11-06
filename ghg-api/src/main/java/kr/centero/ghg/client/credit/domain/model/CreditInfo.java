package kr.centero.ghg.client.credit.domain.model;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("CreditInfo")
@NoArgsConstructor
@AllArgsConstructor
public class CreditInfo {
	private String creditNo;
	private String originCrediNo;
	private String creditPeriod;
	private String ghgProgram;
	private String pjtTitle;
	private String apprDt;
	private String bufferCredit;
	private String bufferDepositTP;
}