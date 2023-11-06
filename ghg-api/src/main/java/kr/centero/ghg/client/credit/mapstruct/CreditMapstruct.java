package kr.centero.ghg.client.credit.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import kr.centero.ghg.client.credit.domain.dto.CreditDto;
import kr.centero.ghg.client.credit.domain.model.CreditInfo;
import kr.centero.ghg.client.credit.domain.model.CreditList;
import kr.centero.ghg.client.credit.domain.model.PJTInfo;
import kr.centero.ghg.client.credit.domain.model.ReductionHistory;
import kr.centero.ghg.client.credit.domain.model.TradingHistory;
import kr.centero.ghg.config.MapStructMapperConfig;

@Mapper(config = MapStructMapperConfig.class)
public interface CreditMapstruct {
	CreditMapstruct INSTANCE = Mappers.getMapper(CreditMapstruct.class);

    CreditDto.CreditListResponse toCreditDto(CreditList Credit);
    
    CreditDto.ReductionHistoryResponse toCreditDto(ReductionHistory reductionHistory);
    
    CreditDto.TradingHistoryResponse toCreditDto(TradingHistory tradingHistory);
    
    CreditDto.PJTInfoResponse toCreditDto(PJTInfo pjtInfo);

    CreditDto.CreditInfoResponse toCreditDto(CreditInfo creditInfo);
}