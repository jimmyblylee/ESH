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

import com.lee.ez.esh.entity.EshHDZJ;

import java.util.List;

/**
 * Description: 活动相关专家服务.<br>
 * Created by Jimmybly Lee on 2017/7/23.
 *
 * @author Jimmybly Lee
 */
public interface HDZJService {

    /**
     * 为给定活动手动指定专家.
     * @param zjId 专家ID
     * @param hdId 活动ID
     */
    void assignZJ(Long zjId, Long hdId);

    /**
     * 查找已经选定的活动专家.
     * @param hdId 活动ID
     */
    List<EshHDZJ> queryAssignedZJ(Long hdId);

    /**
     * 删除某活动专家.
     * @param hdzjId 活动专家id
     */
    void removeZJ(Long hdzjId);
}
