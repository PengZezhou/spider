package com.yssj.core.framework.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yssj.common.dto.BasePageRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description: <br/>
 * date: 2022/2/19 10:42<br/>
 *
 * @author Lisland<br />
 */
@Data
@AllArgsConstructor
public class Dto2PageConverter<T extends BasePageRequest> {

    private T  data;

    public Page convert(){
        return  new Page(data.getPageNo(),data.getPageSize());
    }

}

