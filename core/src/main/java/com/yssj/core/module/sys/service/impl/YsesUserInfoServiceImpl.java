package com.yssj.core.module.sys.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.yssj.core.module.sys.service.IYsesUserInfoService;
import com.yssj.core.framework.exception.AuthException;
import com.yssj.common.dto.response.YsesUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: <br/>
 * date: 2021/11/22 18:33<br/>
 *
 * @author Lisland<br />
 */
@Service
public class YsesUserInfoServiceImpl implements IYsesUserInfoService {

    private static final Logger logger = LoggerFactory.getLogger(YsesUserInfoServiceImpl.class);

    private String uumsHost;
    private String userTokenValid;

    /**
     * 缓存token 首先从缓存中获取token，存在返回，若不存在，查询uums校验
     * @param token
     * @return
     */
    @Override
    @Cacheable(value = "yses_user_token", key = "#token")
    public YsesUserInfo verifyToken(String token) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("token", token);
        String result = HttpUtil.get(uumsHost + userTokenValid, params);
        //result = "{\"success\":true,\"data\":{\"expire\":true,\"user\":{\"id\":\"9666e2067b29484faa31223c84014740\",\"phone\":\"18671842450\",\"name\":\"清风\"}}}";
        try {
            JSONObject json = JSONObject.parseObject(result);
            if (!json.getBoolean("success")) {
                logger.error("invalid token ,result {}", result);
                throw new AuthException("internal http error");
            }
            JSONObject data = json.getJSONObject("data");
            if (!data.getBoolean("expire")) {
                logger.error("invalid token {},result {}",token, result);
                throw new AuthException("This token is invalid");
            }
            String userId = data.getJSONObject("user").getString("id");
            String phone = data.getJSONObject("user").getString("phone");
            String userName = data.getJSONObject("user").getString("name");
            return new YsesUserInfo(userId, userName, phone);
        } catch (Exception e) {
            logger.error("校验用户中心token失败", e);
            throw new AuthException("internal http error");
        }
    }
}
