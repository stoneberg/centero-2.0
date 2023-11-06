package kr.centero.ghg.client.credit.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreditDto {
	
    /**
     * CreditInfo 조회 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "대시보드 정보", description = "대시보드 정보 DTO")
    public static class CreditListRequest {
    	private String ghgProgram;
    }
    
    /**
     * 프로젝트 정보 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "프로젝트 정보", description = "프로젝트 정보 DTO")
    public static class PJTInfoRequest {
    	private String pjtSeq;
    }
    
    /**
     * 감축이력 조회 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "감축이력", description = "감축이력 DTO")
    public static class ReductionHistoryRequest {
    	private String trdPSeq;
    }
    
    /**
     * 거래이력 조회 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "거래이력", description = "거래이력 DTO")
    public static class TradingHistoryRequest {
    	private String slsSeq;
    }

    /**
     * Credit 정보 요청 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "Credit 정보", description = "Credit 정보 DTO")
    public static class CreditInfoRequest {
    	private String trdSeq;
    	private String slsSeq;
    }
    
    /**
     * CreditInfo 응답 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "대시보드 정보", description = "대시보드 정보 DTO")
    public static class CreditListResponse {
    	private int trdSeq;
    	private int trdPSeq;
    	private String pjtSeq;
    	private int slsSeq;
    	private String ghgProgramNm;
    	private String trdTp;
    	private String pjtTitle;
    	private String proponentNm;  //제안자
    	private String creditProponentNm; // 소유자
    	private int creditSize;
    	private String pjtCreditNo;
    	private String creditPeriod;
    	private String salesYn;
    	private String etc;	
    }
    
    /**
     * 프로젝트 응답 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "프로젝트 정보", description = "프로젝트 정보 DTO")
    public static class PJTInfoResponse {
    	private String ghgProgramNm;
    	private String vvbNm;
    	private String proponentNm;
    	private String pjtTitle;
    	private String pjtPeriod;
    	private String crtNm;
    }
    
    /**
     * 감축이 응답 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "감축이력", description = "감축이력 DTO")
    public static class ReductionHistoryResponse {
    	private String crtDT;
    	private String proponentNm;
    	private String creditSizeNm;
    	private String trdTpNm;
    }
    
    /**
     * 거래이 응답 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "거래이력", description = "거래이력 DTO")
    public static class TradingHistoryResponse {
    	private int salesSeq;
    	private String salesDT;
    	private String creditSellerNm;
    	private String creditSellerNmEng;
    	private String creditBuyerNm;
    	private String creditBuyerNmEng;
    	private String creditSizeNm;
    }
    
    
    /**
     * Credit 정보 응답 DTO
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "Credit 정보", description = "Credit 정보 DTO")
    public static class CreditInfoResponse {
    	private String creditNo;
    	private String originCrediNo;
    	private String creditPeriod;
    	private String ghgProgram;
    	private String pjtTitle;
    	private String apprDt;
    	private String bufferCredit;
    	private String bufferDepositTP;
    }
}
