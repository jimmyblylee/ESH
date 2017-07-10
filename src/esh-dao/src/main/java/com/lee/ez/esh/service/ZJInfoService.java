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

import com.lee.ez.esh.entity.EshZJ;
import com.lee.jwaf.token.Token;

/**
 * Description: 登记专家信息维护服务.<br>
 * Created by Jimmybly Lee on 2017/6/28.
 *
 * @author Jimmybly Lee
 */
public interface ZJInfoService {

    /**
     * 根据id获得实例.
     * @param id 实例id
     * @return 实例
     */
    EshZJ get(Long id);

    // CSOFF: NPathComplexity
    // CSOFF: CyclomaticComplexity
    /**
     * 根据条件搜索实例.
     * @param condition 条件，姓名，推荐单位，审核状态，是否已启用，登记人所在单位
     * @param start 分页开始
     * @param limit 分页长度
     * @return 实例列表
     */
     List<EshZJ> query(EshZJ condition, Integer start, Integer limit);
    // CSON: CyclomaticComplexity
    // CSON: NPathComplexity

    // CSOFF: CyclomaticComplexity
    // CSOFF: NPathComplexity
    /**
     * 根据条件搜索实例数量.
     * @param condition 条件，姓名，推荐单位，审核状态，是否已启用，登记人所在单位
     * @return 实例数量
     */
    public Integer count(EshZJ condition);
    // CSON: CyclomaticComplexity
    // CSON: NPathComplexity

    /**
     * 持久化实体.
     * @param userToken 用户令牌
     * @param entity 游离实体
     * @return 持久实体
     */
    EshZJ create(Token userToken, EshZJ entity);

    /**
     * 更新实体.
     * @param userToken 用户令牌
     * @param entity 游离实体
     */
    void update(Token userToken, EshZJ entity);

    /**
     * 更新是否启用状态.
     * @param userToken 用户令牌
     * @param id 实体ID
     * @param isEnabled true for 启用
     */
    void setStatus(Token userToken, Integer id, Boolean isEnabled);
}
