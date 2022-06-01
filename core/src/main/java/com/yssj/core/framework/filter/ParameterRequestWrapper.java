package com.yssj.core.framework.filter;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ParameterRequestWrapper extends HttpServletRequestWrapper {
    private Map<String, String[]> params = new HashMap<>();

    public ParameterRequestWrapper(HttpServletRequest request) {
        super(request);
        Map<String, String[]> requestMap = request.getParameterMap();
        this.params.putAll(requestMap);
        this.modifyParameterValues();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (!super.getHeader(HttpHeaders.CONTENT_TYPE).equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
            return super.getInputStream();
        }
        String json = IoUtil.read(super.getInputStream(), "utf-8");
        if (StrUtil.isEmpty(json)) {
            return super.getInputStream();
        }
        Object o = jsonStringToMap(json);
        // 防止循环引用
        ByteArrayInputStream bis = new ByteArrayInputStream(
                JSON.toJSONString(o, SerializerFeature.DisableCircularReferenceDetect)
                        .getBytes(StandardCharsets.UTF_8));
        return new MyServletInputStream(bis);
    }

    public void modifyParameterValues() {
        Set<String> set = params.keySet();
        for (String key : set) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                values[i] = values[i].trim();
            }
            params.put(key, values);
        }
    }

    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

    public static Object jsonStringToMap(String jsonString) {
        Map<String, Object> map = new HashMap<>();
        Object obj1 = JSONObject.parse(jsonString);
        if (obj1 instanceof JSONArray) {
            List<Map<String, Object>> list = new ArrayList<>();
            JSONArray obj11 = (JSONArray) obj1;
            for (Object o11 : obj11) {
                Map<String, Object> map1 = new HashMap<>();
                recursionTrim(map1, (JSONObject) o11);
                list.add(map1);
            }
            return list;
        }
        if (obj1 instanceof JSONObject) {
            recursionTrim(map, (JSONObject) obj1);
        }

        return map;
    }

    private static void recursionTrim(Map<String, Object> map, JSONObject o11) {
        for (String k : o11.keySet()) {
            Object o = o11.get(k);
            if (o instanceof JSONArray) {
                List<Object> list = new ArrayList<>();
                for (Object obj : (JSONArray) o) {
                    if (obj instanceof JSONObject) {
                        list.add(jsonStringToMap(obj.toString()));
                    } else if (obj instanceof String) {
                        list.add(obj.toString().trim());
                    } else {
                        list.add(obj);
                    }
                }
                map.put(k, list);
            } else if (o instanceof JSONObject) {
                map.put(k, jsonStringToMap(o.toString()));
            } else {
                if (o instanceof String) {
                    map.put(k, o.toString().trim());
                } else {
                    map.put(k, o);
                }
            }
        }
    }

    static class MyServletInputStream extends ServletInputStream {
        private ByteArrayInputStream bis;

        public MyServletInputStream(ByteArrayInputStream bis) {
            this.bis = bis;
        }

        @Override
        public boolean isFinished() {
            return true;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {

        }

        @Override
        public int read() {
            return bis.read();
        }
    }
}
