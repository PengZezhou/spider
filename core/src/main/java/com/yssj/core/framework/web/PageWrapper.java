package com.yssj.core.framework.web;

import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 */
@Data
public class PageWrapper<T> {
    private Long total;
    private Long pageNo;
    private Long pageSize;
    private List<T> list;
}
