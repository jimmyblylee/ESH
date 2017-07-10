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

import com.lee.ez.esh.entity.EshZJFZ;

/**
 * Description: 专家辅助信息服务，服务于教育背景、工作经历、奖励情况、研究成果、学术组织.<br>
 * Created by Jimmybly Lee on 2017/6/28.
 *
 * @author Jimmybly Lee
 */
public interface ZJFZService {

    /**
     * 根据专家id返回辅助信息表,时间顺序.
     * @param id 专家ID
     * @param type 实体类型
     * @return 辅助信息表,时间顺序.
     */
    List<EshZJFZ> queryJYBJ(Long id, Class type);

    /**
     * 将给定的专家辅助信息存储.
     * @param list 辅助信息列表
     */
    void update(List<EshZJFZ> list);

    /**
     * 删除实体.
     * @param entity 实体
     * @param type 实体类型
     */
    void remove(EshZJFZ entity, Class type);
}
