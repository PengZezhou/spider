package com.yssj.core.module.crawler.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Administrator
 * @since 2022-05-31
 */
@Getter
@Setter
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 业务id
     */
    private String bussinessId;

    /**
     * 折扣值
     */
    private String disCount;

    /**
     * 商品链接
     */
    private String url;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 1:拼多多；2...
     */
    private Integer src;

    /**
     * 0：爬取；1：已使用（无效）；
     */
    private Integer status;


}
