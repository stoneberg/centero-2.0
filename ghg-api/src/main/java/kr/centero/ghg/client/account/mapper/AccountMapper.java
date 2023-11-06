package kr.centero.ghg.client.account.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.centero.ghg.admin.mdm.domain.dto.CodeDto.CodeRequest;
import kr.centero.ghg.client.account.domain.dto.AccountDto.CreditRequest;
import kr.centero.ghg.client.account.domain.model.Credit;
import kr.centero.ghg.client.account.domain.model.Method;

@Mapper
public interface AccountMapper {
    List<Method> findMtdList(Map<String, Object> param);

    List<Credit> findCreditList(CreditRequest req);
}
