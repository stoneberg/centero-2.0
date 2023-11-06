package kr.centero.ghg.client.menu.domain.model;

import kr.centero.core.common.pagination.PageModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("Menu")
public class Menu  {

    /**
     * 메뉴 트리 레벨
     */
    private int treeLvl;

    /**
     * 메뉴 트리 정렬 순서 : ex)11>10>10
     */
    private String treeOrder;

    /**
     * 메뉴 트리 경로 : ex)D010>DD010>DD01010
     */
    private String treePath;

    /**
     * 메뉴 언어코드 (다국어)
     */
    private String langCd;

    /**
     * 메뉴명 (다국어 지원)
     */
    private String dspText;

    /**
     * 생성 권한 여부 : Y,N
     */
    private String c;
    /**
     * 읽기 권한 여부 : Y,N
     */
    private String r;
    /**
     * 수정 권한 여부 : Y,N
     */
    private String u;
    /**
     * 삭제 권한 여부 : Y,N
     */
    private String d;

    /**
     * 메뉴 표시 여부 : Y,N
     */
    private String dspYn;

}
