package com.yssj.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description: <br/>
 * date: 2022/2/23 14:45<br/>
 *
 * @author Lisland<br />
 */
@Data
public class IdsRequest {

    @NotNull
    private List<Integer> ids;
}
