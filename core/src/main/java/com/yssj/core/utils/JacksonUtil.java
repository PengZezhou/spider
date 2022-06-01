package com.yssj.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * Description: <br/>
 * date: 2022/2/24 9:33<br/>
 *
 * @author Lisland<br />
 */
@Slf4j
public class JacksonUtil {
    public static ObjectMapper objectMapper = new ObjectMapper();


    /**
     * Java对象转JSON字符串
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("The JacksonUtil toJsonString is error : \n", e);
            throw new RuntimeException();
        }
    }

}
