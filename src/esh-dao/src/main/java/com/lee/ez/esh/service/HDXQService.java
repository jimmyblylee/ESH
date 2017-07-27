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

// CSOFF: NPathComplexity
// CSOFF: CyclomaticComplexity
/**
 * Description: 活动需求服务.<br>
 * Created by Jimmybly Lee on 2017/7/27.
 *
 * @author Jimmybly Lee
 */
public interface HDXQService {

    /**
     * 获取活动需求.
     * @param hdId 活动id
     * @return 需求列表
     */
    List queryHDXQ(Long hdId);

    /**
     * 给活动指定需求.
     * @param entity 活动需求
     */
    void assignHDXQ(EshHDXQ entity);

    /**
     * 删除活动需求.
     * @param hdxqId 活动需求
     */
    void removeHDXQ(Long hdxqId);
}
