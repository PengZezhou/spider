package com.yssj.common.dto.request;

import com.yssj.common.dto.BasePageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 答题记录表
 * </p>
 *
 * @author liukai
 * @since 2021-11-22
 */
@Data
public class AnswerRecordRequest  extends BasePageRequest implements Serializable  {

    private static final long serialVersionUID = 1L;

    private String userId;
    private String phone;
    private String userName;
    private Integer orgId;

}
