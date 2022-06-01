package com.yssj.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  云上恩施用户信息
 *  @author Lisland<br />
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YsesUserInfo {
    private String userId;
    private String userName;
    private String phone;

}
