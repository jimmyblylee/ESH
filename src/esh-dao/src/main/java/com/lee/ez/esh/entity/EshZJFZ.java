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
 * Description: 专家辅助信息应该具备的接口.<br>
 * Created by Jimmybly Lee on 2017/6/29.
 *
 * @author Jimmybly Lee
 */
public interface EshZJFZ {
    /** Get id.
     * @return entity id.
     * */
    Long getId();

    /** Set Id.
     * @param id entity id
     * */
    void setId(Long id);

    /** Get 专家.
     * @return 专家
     * */
    EshZJ getZj();

    /** Set 专家.
     * @param zhuanJia 专家
     * */
    void setZj(EshZJ zhuanJia);

}
