package com.yssj.core.constants;

/**
 * Description: <br/>
 * date: 2021/11/23 11:26<br/>
 *
 * @author Lisland<br />
 */
public class Constants {

    public final static Integer BOOLEAN_NO_INT= 0;
    public final static Integer BOOLEAN_YES_INT= 0;

    /**
     * false 禁用
     */
    public final static Integer USER_STATUS_NO = 0;
    /**
     * 启用
     */
    public final static Integer USER_STATUS_YES = 1;

    /**
     * 提案审核状态 -1 不通过
     */
    public final static Integer PROPOSAL_STATUS_NO = -1;
    /**
     * 提案审核状态 0 待审核
     */
    public final static Integer PROPOSAL_STATUS_INIT = 0;
    /**
     * 提案审核状态 1 初审通过
     */
    public final static Integer PROPOSAL_STATUS_FIRST = 1;
    /**
     * 提案审核状态 2 终审通过
     */
    public final static Integer PROPOSAL_STATUS_LAST = 2;


}
