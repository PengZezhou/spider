package com.yssj.common.dto;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public abstract class BasePageRequest {
    private Integer pageNo=1;
    private Integer pageSize=10;

    public Integer getPageNo() {
        if (pageNo == null) {
            return 1;
        }
        return pageNo;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            return 10;
        }
        return pageSize;
    }
}