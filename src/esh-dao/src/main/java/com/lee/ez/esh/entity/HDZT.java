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

package com.lee.ez.esh.entity;

/**
 * Description: 活动审核、进行状态枚举.<br>
 * Created by Jimmybly Lee on 2017/6/25.
 *
 * @author Jimmybly Lee
 */
public enum HDZT {
    /** 0:待上报.**/
    DSB,
    /** 1:待受理..*/
    DSL,
    /** 2:待筛选(专家)，.**/
    DSX,
    /** 3:待补充（专家）.**/
    DBC,
    /** 4:待启动.**/
    DQD,
    /** 5:待开始（等待活动开始）.**/
    DKS,
    /** 6:活动中. **/
    HDZ,
    /** 7:待总结..**/
    DZJ,
    /** 8：结束（活动）. **/
    JS;
}
