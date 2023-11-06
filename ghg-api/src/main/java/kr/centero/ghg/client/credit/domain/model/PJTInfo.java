package kr.centero.ghg.client.credit.domain.model;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("PJTInfo")
@NoArgsConstructor
@AllArgsConstructor
public class PJTInfo {
	private String ghgProgramNm;
	private String vvbNm;
	private String proponentNm;
	private String pjtTitle;
	private String pjtPeriod;
	private String crtNm;
	private String crtEmail;
}