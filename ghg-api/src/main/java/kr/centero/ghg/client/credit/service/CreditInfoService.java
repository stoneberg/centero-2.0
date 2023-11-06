package kr.centero.ghg.client.credit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.centero.ghg.client.credit.domain.dto.CreditDto;
import kr.centero.ghg.client.credit.domain.model.PJTInfo;
import kr.centero.ghg.client.credit.domain.model.ReductionHistory;
import kr.centero.ghg.client.credit.mapper.CreditMapper;
import kr.centero.ghg.client.credit.mapstruct.CreditMapstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CreditInfoService {

    private final CreditMapper creditMapper;

    public List<CreditDto.CreditListResponse> findCreditList(CreditDto.CreditListRequest creditListRequest) {
        return creditMapper.findCreditList(creditListRequest).stream()
                .map(CreditMapstruct.INSTANCE::toCreditDto).toList();
    }
    
    
    public List<CreditDto.ReductionHistoryResponse> findReductionHistory(CreditDto.ReductionHistoryRequest reductionRequest) {
    	
    	List<ReductionHistory> reductionHistory = creditMapper.findReductionHistory(reductionRequest);
    	
    	for(int i = 0; i < reductionHistory.size()-1; i++)
    		reductionHistory.get(i).setProponentNm(reductionHistory.get(i).getCreditProponentId() + "(" + reductionHistory.get(i).getProponentNm()  + ")");

        return reductionHistory.stream()
                .map(CreditMapstruct.INSTANCE::toCreditDto).toList();
    }
    
    public List<CreditDto.TradingHistoryResponse> findTradingHistory(CreditDto.TradingHistoryRequest tradingRequest) {
        return creditMapper.findTradingHistory(tradingRequest).stream()
                .map(CreditMapstruct.INSTANCE::toCreditDto).toList();
    }
    
    public List<CreditDto.PJTInfoResponse> findPJTInfo(CreditDto.PJTInfoRequest PJTInfoRequest) {
    	
    	List<PJTInfo> pjtInfo = creditMapper.findPJTInfo(PJTInfoRequest);
    	for(int i = 0; i < pjtInfo.size(); i++)
    		pjtInfo.get(i).setCrtNm(pjtInfo.get(i).getCrtNm() + "(" + pjtInfo.get(i).getCrtEmail() + ")");
    	
        return pjtInfo.stream()
                .map(CreditMapstruct.INSTANCE::toCreditDto).toList();
    }
    
    public List<CreditDto.CreditInfoResponse> findCreditInfo(CreditDto.CreditInfoRequest creditInforequest) {
        return creditMapper.findCreditInfo(creditInforequest).stream()
                .map(CreditMapstruct.INSTANCE::toCreditDto).toList();
    }
    
}