package com.yssj.core.framework.web;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author Administrator
 */
public class PageUtil {

    public static <T> PageWrapper<T> IPage2PageWrapper(IPage<T> res) {
        PageWrapper<T> result = new PageWrapper<T>();
        result.setList(res.getRecords());
        result.setPageNo(res.getCurrent());
        result.setPageSize(res.getSize());
        result.setTotal(res.getTotal());
        return result;
    }


}
