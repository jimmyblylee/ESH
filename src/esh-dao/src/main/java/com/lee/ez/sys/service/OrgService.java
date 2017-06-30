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

package com.lee.ez.sys.service;

import java.util.List;

import com.lee.ez.sys.entity.SysOrg;

/**
 * Description: 处室服务.<br>
 * Created by Jimmybly Lee on 2017/6/27.
 *
 * @author Jimmybly Lee
 */
public interface OrgService {

    /**
     * 根据ID获得实体.
     * @param id 实体ID
     * @return 实体
     */
    SysOrg get(Integer id);

    /**
     * 根据条件获得实体列表.
     * @param condition 条件，名称，电话，是否启用
     * @param start 分页开始
     * @param limit 分页长度
     * @return 实体列表
     */
    List<SysOrg> query(SysOrg condition, Integer start, Integer limit);

    /**
     * 根据条件获得实体数量.
     * @param condition 条件，名称，电话，是否启用
     * @return 实体数量
     */
    Integer count(SysOrg condition);

    /**
     * 创建实体.
     * @param entity 游离状态实体
     * @return 持久化实体
     */
    SysOrg create(SysOrg entity);

    /**
     * 更新实体.只更新名称、电话
     * @param entity 游离状态实体.
     */
    void update(SysOrg entity);

    /**
     * 修改实体是否启用状态.
     * @param id 实体ID
     * @param isEnabled 是否启用
     */
    void changeStatus(Integer id, Boolean isEnabled);
}
