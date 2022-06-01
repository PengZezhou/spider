package com.yssj.core.module.sys.service;

import com.yssj.common.dto.response.YsesUserInfo;

/**
 * Description: <br/>
 * date: 2021/11/22 18:30<br/>
 *
 * @author Lisland<br />
 */
public interface IYsesUserInfoService {

    /**
     * 校验用户token
     * @param token
     * @return 用户中心用户信息
     */
    YsesUserInfo verifyToken(String token);
}
