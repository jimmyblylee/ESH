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

package com.lee.ez.esh.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lee.ez.esh.entity.*;
import com.lee.ez.esh.service.HDFlowService;
import com.lee.ez.esh.service.HDInfoService;
import com.lee.ez.esh.service.HDXQService;
import com.lee.ez.esh.service.HDZJService;
import com.lee.jwaf.action.AbstractControllerSupport;

/**
 * Description: 活动控制器.<br>
 * Created by Jimmybly Lee on 2017/7/25.
 *
 * @author Jimmybly Lee
 */
@Controller("HDController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class HDController extends AbstractControllerSupport {

    /** 活动服务. */
    @Resource
    private HDInfoService infoService;
    /** 活动流程服务. */
    @Resource
    private HDFlowService flowService;
    /** 活动专家服务. */
    @Resource
    private HDZJService hdzjService;
    /** 活动需求服务. */
    @Resource
    private HDXQService hdxqService;

    /**
     * 查询.
     */
    @SuppressWarnings("CheckStyle")
    public void query() {
        final EshHD condition = workDTO.convertJsonToBeanByKey("condition", EshHD.class);
        List<EshHD> result = infoService.query(condition, workDTO.getStart(), workDTO.getLimit());
        for (EshHD hd : result) {
            for (EshHDXQ xq : hd.getXqList()) {
                xq.setHd(null);
                for (EshHDXQTJ tj : xq.getTjList()) {
                    tj.setXq(null);
                }
                for (EshHDXQKW kw : xq.getKwList()) {
                    kw.setXq(null);
                }
            }
        }
        workDTO.setResult(result);
        workDTO.setTotle(infoService.count(condition));
    }

    /**
     * 创建.
     */
    public void create() {
        workDTO.setResult(infoService.create(sessionDTO.currentToken(), workDTO.convertJsonToBeanByKey("entity", EshHD.class)));
    }

    /**
     * 更新.
     */
    public void update() {
        infoService.update(sessionDTO.currentToken(), workDTO.convertJsonToBeanByKey("entity", EshHD.class));
    }

    /**
     * 删除.
     */
    public void remove() {
        infoService.setStatus(sessionDTO.currentToken(), workDTO.getInteger("id"), false);
    }

    /**
     * 恢复.
     */
    public void resume() {
        infoService.setStatus(sessionDTO.currentToken(), workDTO.getInteger("id"), true);
    }

    /**
     * 物理删除.
     */
    public void doRealRemove() {
        infoService.doRealRemove(workDTO.getInteger("id"));
    }

    /**
     * 指定专家.
     */
    public void assignZJ() {
        hdzjService.assignZJ(workDTO.getInteger("zjId"), workDTO.getInteger("hdId"));
    }

    /**
     * 在活动中删除指定专家.
     */
    public void removeZJ() {
        hdzjService.removeZJ(workDTO.getInteger("hdzjId"));
    }

    /**
     * 根据活动获得已经选择的专家.
     */
    public void queryHDZJ() {
        final List<EshHDZJ> result = hdzjService.queryAssignedZJ(workDTO.getInteger("hdId"));
        for (EshHDZJ zj : result) {
            zj.setHd(null);
            zj.getZj().setGzjlList(null);
            zj.getZj().setJlqkList(null);
            zj.getZj().setJybjList(null);
            zj.getZj().setJzList(null);
            zj.getZj().setYjcgList(null);
            zj.getZj().setZylbList(null);
        }
        workDTO.setResult(result);
    }

    /* *****************
     * 单步操作需求 开始
     * *****************/

    /**
     * 获取活动需求.
     */
    public void queryXQ() {
        workDTO.setResult(hdxqService.queryXQ(workDTO.getInteger("hdId")));
    }

    /**
     * 创建活动需求.
     */
    public void createXQ() {
        workDTO.setResult(hdxqService.createXQ(workDTO.convertJsonToBeanByKey("entity", EshHDXQ.class)));
    }

    /**
     * 更新活动需求.
     */
    public void updateXQ() {
        hdxqService.updateXQ(workDTO.convertJsonToBeanByKey("entity", EshHDXQ.class));
    }

    /**
     * 删除活动需求.
     */
    public void removeXQ() {
        hdxqService.removeXQ(workDTO.getInteger("id"));
    }

    /**
     * 获取活动需求条件.
     */
    public void queryXQTJ() {
        workDTO.setResult(hdxqService.queryXQTJ(workDTO.getInteger("id")));
    }

    /**
     * 创建活动需求条件.
     */
    public void createXQTJ() {
        workDTO.setResult(hdxqService.createXQTJ(workDTO.convertJsonToBeanByKey("entity", EshHDXQTJ.class)));
    }

    /**
     * 更新活动需求条件.
     */
    public void updateXQTJ() {
        hdxqService.updateXQTJ(workDTO.convertJsonToBeanByKey("entity", EshHDXQTJ.class));
    }

    /**
     * 删除活动需求条件.
     */
    public void removeXQTJ() {
        hdxqService.removeXQTJ(workDTO.getInteger("id"));
    }

    /**
     * 获取活动需求库外专家.
     */
    public void queryXQKW() {
        workDTO.setResult(hdxqService.queryXQKW(workDTO.getInteger("id")));
    }

    /**
     * 创建活动需求条件.
     */
    public void createXQKW() {
        workDTO.setResult(hdxqService.createXQKW(workDTO.convertJsonToBeanByKey("entity", EshHDXQKW.class)));
    }

    /**
     * 更新活动需求条件.
     */
    public void updateXQKW() {
        hdxqService.updateXQKW(workDTO.convertJsonToBeanByKey("entity", EshHDXQKW.class));
    }

    /**
     * 删除活动需求条件.
     */
    public void removeXQKW() {
        hdxqService.removeXQKW(workDTO.getInteger("id"));
    }
    /* *****************
     * 单步操作需求 结束
     * *****************/

    /* *****************
     * 流程相关 开始
     * *****************/

    /**
     * 上报.
     */
    public void shangBao() {
        flowService.shangBao(sessionDTO.currentToken().user(), workDTO.getInteger("id"));
    }

    /**
     * 受理.
     */
    public void shouLi() {
        flowService.shouLi(sessionDTO.currentToken().user(), workDTO.getInteger("id"));
    }

    /**
     * 需要补充.
     */
    public void xuYaoBuChong() {
        flowService.xuYaoBuChong(sessionDTO.currentToken().user(), workDTO.getInteger("id"));
    }

    /**
     * 完成补充.
     */
    public void wanChengBuChong() {
        flowService.wanChengBuChong(sessionDTO.currentToken().user(), workDTO.getInteger("id"));
    }

    /**
     * 完成筛选.
     */
    public void wanChengShaiXuan() {
        flowService.wanChengShaiXuan(sessionDTO.currentToken().user(), workDTO.getInteger("id"));
    }

    /**
     * 启动.
     */
    public void qiDong() {
        flowService.qiDong(sessionDTO.currentToken().user(), workDTO.getInteger("id"));
    }

    /**
     * 开始.
     */
    public void kaiShi() {
        flowService.kaiShi(sessionDTO.currentToken().user(), workDTO.getInteger("id"));
    }

    /**
     * 总结.
     */
    public void zongJie() {
        flowService.zongJie(sessionDTO.currentToken().user(), workDTO.getInteger("id"));
    }

    /**
     * 为活动随机专家.
     */
    public void suiJi() {
        hdxqService.suiJi(sessionDTO.currentToken(), workDTO.getInteger("id"));
    }

    /**
     * 为活动补充专家.
     */
    public void buChong() {
        hdxqService.buChong(workDTO.getInteger("xqId"), workDTO.getInteger("zuId"));
    }

    /**
     * 确认参加.
     */
    public void queRenCanJia() {
        hdxqService.queRenCanJia(sessionDTO.currentToken(), workDTO.getInteger("id"));
    }

    /**
     * 确认不参加.
     */
    public void queRenBuCanJia() {
        hdxqService.queRenBuCanJia(sessionDTO.currentToken(), workDTO.getInteger("id"), workDTO.get("liYou"));
    }
    /* *****************
     * 流程相关 结束
     * *****************/

    /**
     * 获取单个活动较全面的需求信息.
     */
    public void getHDInfo() {
        workDTO.setResult(infoService.getHDInfo(workDTO.getInteger("id")));
    }
}
