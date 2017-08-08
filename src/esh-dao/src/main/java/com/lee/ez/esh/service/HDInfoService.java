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

import com.lee.ez.esh.dto.HD;
import com.lee.ez.esh.entity.EshHD;
import com.lee.jwaf.token.Token;

/**
 * Description: 活动服务信息维护服务.<br>
 * Created by Jimmybly Lee on 2017/6/28.
 *
 * @author Jimmybly Lee
 */
public interface HDInfoService {

    /**
     * 查询实体.
     * @param condition 条件
     * @param start 开始
     * @param limit 长度
     * @return 实体列表
     */
    List<EshHD> query(EshHD condition, Integer start, Integer limit);

    /**
     * 根据条件获取总数.
     * @param condition 条件
     * @return 总数
     */
    Integer count(EshHD condition);

    /**
     * 创建实体.
     * @param token 用户令牌
     * @param entity 待创建的实体
     */
    Integer create(Token token, EshHD entity);

    /**
     * 更新实体.
     * @param token 用户令牌
     * @param entity 待更新的实体
     */
    void update(Token token, EshHD entity);

    /**
     * 更新是否启用状态.
     *
     * @param userToken 用户令牌
     * @param id        实体ID
     * @param isEnabled true for 启用
     */
    void setStatus(Token userToken, Integer id, Boolean isEnabled);

    /**
     * 物理删除活动信息.
     * @param id 活动id
     */
    void doRealRemove(Integer id);

    /**
     * 获取活动相对全面的信息，通过dto传送.
     * @param id 活动id
     * @return 活动dto
     */
    HD getHDInfo(Integer id);

}
