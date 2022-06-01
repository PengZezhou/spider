package com.yssj.core.module.crawler.service;

import com.yssj.core.module.crawler.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Administrator
 * @since 2022-05-31
 */
public interface IGoodsService extends IService<Goods> {

    /**
     * 爬取拼多多批发折扣商品
     */
    public void pinGoodsSync();
}
