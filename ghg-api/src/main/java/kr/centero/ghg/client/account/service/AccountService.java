package kr.centero.ghg.client.account.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.centero.ghg.client.account.domain.dto.AccountDto.CreditRequest;
import kr.centero.ghg.client.account.domain.model.Credit;
import kr.centero.ghg.client.account.domain.model.Method;
import kr.centero.ghg.client.account.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountMapper mapper;

    public List<Method> findMtdList(String id) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", id);
        param.put("localeCd", "ko_KR");
        return mapper.findMtdList(param);
    }

    public List<Credit> findCreditList(CreditRequest req) {
        req.setLocaleCd("ko_KR");
        return mapper.findCreditList(req);
    }

}
