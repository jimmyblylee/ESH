/* ***************************************************************************
 * EZ.JWAF/EZ.JCWAP: Easy series Production.
 * Including JWAF(Java-based Web Application Framework)
 * and JCWAP(Java-based Customized Web Application Platform).
 * Copyright (C) 2016-2017 the original author or authors.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of MIT License as published by
 * the Free Software Foundation;
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the MIT License for more details.
 *
 * You should have received a copy of the MIT License along
 * with this library; if not, write to the Free Software Foundation.
 * ***************************************************************************/

package com.lee.ez.esh.service;

import java.util.List;

import com.lee.ez.esh.entity.EshHDXQ;
import com.lee.ez.esh.entity.EshHDXQKW;
import com.lee.ez.esh.entity.EshHDXQTJ;
import com.lee.jwaf.token.Token;

// CSOFF: NPathComplexity
// CSOFF: CyclomaticComplexity
/**
 * Description: 活动需求服务.<br>
 * Created by Jimmybly Lee on 2017/7/27.
 *
 * @author Jimmybly Lee
 */
public interface HDXQService {

    /* *****************
     * 单步操作需求 开始
     * *****************/
    /**
     * 获取活动的需求.
     * @param hdId 活动id
     * @return 需求列表
     */
    List<EshHDXQ> queryXQ(Integer hdId);

    /**
     * 创建需求(不级联).
     * @param entity 实体
     * @return 持久需求
     */
    EshHDXQ createXQ(EshHDXQ entity);

    /**
     * 更新需求(不级联).
     * @param entity 实体
     */
    void updateXQ(EshHDXQ entity);

    /**
     * 删除需求(级联)(物理删除).
     * @param id 实体
     */
    void removeXQ(Integer id);

    /**
     * 获取活动的需求条件.
     * @param id 需求id
     * @return 需求条件列表
     */
    List<EshHDXQTJ> queryXQTJ(Integer id);

    /**
     * 创建需求条件.
     * @param entity 条件实体
     * @return 持久条件
     */
    EshHDXQTJ createXQTJ(EshHDXQTJ entity);

    /**
     * 更新条件.
     * @param entity 条件实体
     */
    void updateXQTJ(EshHDXQTJ entity);

    /**
     * 删除条件(物理删除).
     * @param id 条件ID
     */
    void removeXQTJ(Integer id);

    /**
     * 获取活动的需求库外专家.
     * @param id 需求id
     * @return 需求库外专家列表
     */
    List<EshHDXQKW> queryXQKW(Integer id);

    /**
     * 创建需求备选库外专家.
     * @param entity 实体
     * @return 持久实体
     */
    EshHDXQKW createXQKW(EshHDXQKW entity);

    /**
     * 更新需求备选库外专家.
     * @param entity 实体
     */
    void updateXQKW(EshHDXQKW entity);

    /**
     * 删除需求备选库外专家（物理删除).
     * @param id 实体ID
     */
    void removeXQKW(Integer id);
    /* *****************
     * 单步操作需求 结束
     * *****************/

    /**
     * 为活动随机人员.
     * @param userToken 用户令牌
     * @param id 活动ID
     */
    void suiJi(Token userToken, Integer id);

    /**
     * 确认某随机结果的专家会参加活动.
     * @param userToken 用户令牌
     * @param sjjgId 随机结果的id
     */
    void queRenCanJia(Token userToken, Integer sjjgId);

    /**
     * 确认某随机结果的专家不会参加活动
     * @param userToken 用户令牌
     * @param sjjgId 随机结果ID
     * @param liYou 未参加理由
     */
    void queRenBuCanJia(Token userToken, Integer sjjgId, String liYou);

    /**
     * 为需求添加候选库外专家.
     * @param xqId 需求id
     * @param zjId 库外专家
     */
    void buChong(Integer xqId, Integer zjId);
}
