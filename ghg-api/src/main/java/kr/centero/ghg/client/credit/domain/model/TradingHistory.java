package kr.centero.ghg.client.credit.domain.model;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("TradingHistory")
@NoArgsConstructor
@AllArgsConstructor
public class TradingHistory {
	private String salesSeq;
	private String salesDT;
	private String creditSellerNm;
	private String creditSellerNmEng;
	private String creditBuyerNm;
	private String creditBuyerNmEng;
	private String creditSizeNm;
}