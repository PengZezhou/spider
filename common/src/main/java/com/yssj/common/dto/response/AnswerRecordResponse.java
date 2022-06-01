package com.yssj.common.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Description: <br/>
 * date: 2021/11/23 9:26<br/>
 *
 * @author Lisland<br />
 */
@Data
public class AnswerRecordResponse {

    private Integer recordId;
    private String userId;
    private String phone;
    private String userName;
    private Integer orgId;
    private String orgName;
    private Integer answerCount;
    private Integer status;
    private BigDecimal score;
    private Date createDate;
    private Date answerDate;

    private Double avgScore;
    private Integer rank;

}
