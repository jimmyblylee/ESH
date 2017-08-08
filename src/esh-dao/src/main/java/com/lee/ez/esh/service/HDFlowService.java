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

import com.lee.jwaf.token.User;

/**
 * Description: 活动流程服务.<br>
 * Created by Jimmybly Lee on 2017/6/28.
 *
 * @author Jimmybly Lee
 */
public interface HDFlowService {

    /**
     * 上报.
     * @param user 操作人
     * @param id 活动ID
     */
    void shangBao(User user, Integer id);

    /**
     * 受理.
     * @param user 操作人
     * @param id 活动ID
     */
    void shouLi(User user, Integer id);

    /**
     * 需要补充库外.
     * @param user 操作人
     * @param id 活动ID
     */
    void xuYaoBuChong(User user, Integer id);

    /**
     * 完成补充.
     * @param user 操作人
     * @param id 活动ID
     */
    void wanChengBuChong(User user, Integer id);

    /**
     * 完成筛选.
     * @param user 操作人
     * @param id 活动ID
     */
    void wanChengShaiXuan(User user, Integer id);

    /**
     * 启动活动（活动将会等待开始）.
     * @param user 操作人
     * @param id 活动ID
     */
    void qiDong(User user, Integer id);

    /**
     * 开始活动（活动将会在进行中).
     * @param user 操作人
     * @param id 活动ID
     */
    void kaiShi(User user, Integer id);

    /**
     * 为活动总结，评分等行为.
     * @param user 操作人
     * @param id 活动ID
     */
    void zongJie(User user, Integer id);
}
