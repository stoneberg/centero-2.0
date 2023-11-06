package kr.centero.ghg.client.credit.domain.model;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("ReductionHistory")
@NoArgsConstructor
@AllArgsConstructor
public class ReductionHistory {
	private String creditProponentId;
	private String proponentNm;
	private String creditSizeNm;
	private String trdTpNm;
	private String crtDT;
}