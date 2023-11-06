package kr.centero.ghg.client.credit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.centero.ghg.client.credit.domain.dto.CreditDto;
import kr.centero.ghg.client.credit.domain.model.CreditInfo;
import kr.centero.ghg.client.credit.domain.model.CreditList;
import kr.centero.ghg.client.credit.domain.model.PJTInfo;
import kr.centero.ghg.client.credit.domain.model.ReductionHistory;
import kr.centero.ghg.client.credit.domain.model.TradingHistory;


@Mapper
public interface CreditMapper {
    List<CreditList> findCreditList(CreditDto.CreditListRequest params);
    List<ReductionHistory> findReductionHistory(CreditDto.ReductionHistoryRequest params); 
    List<TradingHistory> findTradingHistory(CreditDto.TradingHistoryRequest params); 
    List<PJTInfo> findPJTInfo(CreditDto.PJTInfoRequest params);
    List<CreditInfo> findCreditInfo(CreditDto.CreditInfoRequest params);

}
 