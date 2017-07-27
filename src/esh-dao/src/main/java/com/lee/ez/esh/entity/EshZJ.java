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

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.lee.ez.sys.entity.SysDict;
import com.lee.ez.sys.entity.SysUser;

// CSOFF: MethodCount
// CSOFF: ParameterName
// CSOFF: MethodName
/**
 * Description: 专家表实体类.<br>
 * Created by Jimmybly Lee on 2017/6/24.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "ESH_ZJ")
@SuppressWarnings("unused")
public class EshZJ implements Serializable {

    private static final long serialVersionUID = -7049126603171553458L;
    /** 专家ID.**/
    @Id
    @Column(name = "ZJ_ID")
    @SequenceGenerator(name = "eshSEQ", sequenceName = "SEQ_ESH")
    @GeneratedValue(generator = "eshSEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    /** 基本信息：姓名.**/
    @Column(name = "ZJ_JB_XM")
    private String jb_xm;
    /** 基本信息：性别.**/
    @Column(name = "ZJ_JB_XB")
    private String jb_xb;
    /** 基本信息：民族.**/
    @Column(name = "ZJ_JB_MZ")
    private String jb_mz;
    /** 基本信息：籍贯.**/
    @Column(name = "ZJ_JB_JG")
    private String jb_jg;
    /** 基本信息：出生日期.**/
    @Column(name = "ZJ_JB_CSRQ")
    private String jb_csrq;
    /** 基本信息：健康状况.**/
    @Column(name = "ZJ_JB_JKZK")
    private String jb_jkzk;
    // CSOFF: LineLength
    /** 基本信息：政治面貌，关联字典：ZZMM.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "ZJ_JB_ZZMM", referencedColumnName = "DICT_CODE", nullable = false)),
        @JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "DICT_NATURE", value = "'ZZMM'")) })
    private SysDict jb_zzmm;
    // CSON: LineLength
    /** 基本信息：身份证号.**/
    @Column(name = "ZJ_JB_SFZH")
    private String jb_sfzh;

    /** 基本信息：电话.**/
    @Column(name = "ZJ_JB_DH")
    private String jb_dh;
    /** 基本信息：手机.**/
    @Column(name = "ZJ_JB_SJ")
    private String jb_sj;

    /** 基本信息：简历.**/
    @Column(name = "ZJ_JB_JL")
    private String jb_jl;

    /** 基本信息：照片，base64编码.**/
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ZJ_JB_ZP", columnDefinition = "CLOB")
    private String jb_zp;

    /** 工作信息：工作单位.**/
    @Column(name = "ZJ_GZ_GZDW")
    private String gz_gzdw;
    /** 基本信息：工作单位的行政职务.**/
    @Column(name = "ZJ_GZ_GZDW_ZW")
    private String gz_gzdw_zw;
    /** 工作信息：公安系统内?1:是，0:否.**/
    @Column(name = "ZJ_GZ_GAXT")
    private Boolean gz_gaxt;
    /** 工作信息：公安系统内，开始时间.**/
    @Column(name = "ZJ_GZ_GAXT_KSSJ")
    private String gz_gaxt_kssj;

    /** 工作信息：单位所地址所在省，关联字典SHENG.**/
    // CSOFF: LineLength
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "ZJ_GZ_GZDW_DZ_SHENG", referencedColumnName = "DICT_CODE", nullable = false)),
        @JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "DICT_NATURE", value = "'SHENG'")) })
    private SysDict gz_gzdw_dz_sheng;
    // CSON: LineLength
    /** 工作信息：单位所地址所在市.**/
    @Column(name = "ZJ_GZ_GZDW_DZ_SHI")
    private String gz_gzdw_dz_shi;
    /** 工作信息：单位所地址街道.**/
    @Column(name = "ZJ_GZ_GZDW_DZ_JD")
    private String gz_gzdw_dz_jd;
    /** 工作信息：单位所地址邮编.**/
    @Column(name = "ZJ_GZ_GZDW_DZ_YB")
    private String gz_gzdw_dz_yb;

    /** 教育信息：文化程度，关联字典项“WHCD”.**/
    // CSOFF: LineLength
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "ZJ_JY_WHCD", referencedColumnName = "DICT_CODE", nullable = false)),
        @JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "DICT_NATURE", value = "'WHCD'")) })
    private SysDict jy_whcd;
    // CSON: LineLength
    /** 教育信息：文化程度所学专业.**/
    @Column(name = "ZJ_JY_WHCD_SSZY")
    private String jy_whcd_sszy;

    /** 专业信息：业务专长.**/
    @Column(name = "ZJ_ZY_YWZC")
    private String zy_ywzc;
    /** 专业信息：技术职称.**/
    @Column(name = "ZJ_ZY_JSZC")
    private String zy_jszc;
    /** 专业信息：资格认证.**/
    @Column(name = "ZJ_ZY_ZGRZ")
    private String zy_zgrz;

    /** 意见信息：所在单位意见.**/
    @Column(name = "ZJ_YJ_SZDW")
    private String yj_szdw;
    /** 意见信息：推荐单位意见.**/
    @Column(name = "ZJ_YJ_TJDW")
    private String yj_tjdw;
    /** 意见信息：装财局意见.**/
    @Column(name = "ZJ_YJ_ZCJ")
    private String yj_zcj;
    /** 意见信息：推荐单位名称.**/
    @Column(name = "ZJ_YJ_TJDW_MC")
    private String yj_tjdw_mc;

    /** 系统信息：审核状态(0:待上报，1:待受理，2:待审核，3:被驳回，4:审核通过）.**/
    @Enumerated
    @Column(name = "ZJ_XT_ZT")
    private ZJZT xt_zt;
    /** 系统信息：是否启用。.**/
    @Column(name = "ZJ_XT_QY")
    private Boolean xt_qy;
    /** 系统信息：审核状态备注.**/
    @Column(name = "ZJ_XT_ZT_BZ")
    private String xt_zt_bz;
    /** 系统信息：登记人.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZJ_XT_DJR")
    private SysUser xt_djr;
    /** 系统信息：审核人.**/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZJ_XT_SHR")
    private SysUser xt_shr;
    /** 系统信息：登记时间（创建时间）.**/
    @Column(name = "ZJ_XT_DJSJ")
    private String xt_djsj;
    /** 系统信息：更新时间（最近一次操作，包括保存和状态）.**/
    @Column(name = "ZJ_XT_GXSJ")
    private String xt_gxsj;
    /** 是否库外. */
    @Column(name = "ZJ_XT_SFKW")
    private Boolean xt_sfkw;

    /** 工作经历. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "zj", cascade = {CascadeType.ALL})
    private Set<EshZJGZJL> gzjlList;
    /** 奖励情况.*/
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "zj", cascade = {CascadeType.ALL})
    private Set<EshZJJLQK> jlqkList;
    /** 教育背景. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "zj", cascade = {CascadeType.ALL})
    private Set<EshZJJYBJ> jybjList;
    /** 兼职. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "zj", cascade = {CascadeType.ALL})
    private Set<EshZJJZ> jzList;
    /** 研究成果. */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "zj", cascade = {CascadeType.ALL})
    private Set<EshZJYJCG> yjcgList;
    /** 研究成果. */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "zj", cascade = {CascadeType.ALL})
    private Set<EshZJZYLB> zylbList;

    /**
     * Get the id.
     *
     * @return return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the jb_xm.
     *
     * @return return the jb_xm
     */
    public String getJb_xm() {
        return jb_xm;
    }

    /**
     * Set jb_xm.
     *
     * @param jb_xm the jb_xm to set
     */
    public void setJb_xm(String jb_xm) {
        this.jb_xm = jb_xm;
    }

    /**
     * Get the jb_xb.
     *
     * @return return the jb_xb
     */
    public String getJb_xb() {
        return jb_xb;
    }

    /**
     * Set jb_xb.
     *
     * @param jb_xb the jb_xb to set
     */
    public void setJb_xb(String jb_xb) {
        this.jb_xb = jb_xb;
    }

    /**
     * Get the jb_mz.
     *
     * @return return the jb_mz
     */
    public String getJb_mz() {
        return jb_mz;
    }

    /**
     * Set jb_mz.
     *
     * @param jb_mz the jb_mz to set
     */
    public void setJb_mz(String jb_mz) {
        this.jb_mz = jb_mz;
    }

    /**
     * Get the jb_jg.
     *
     * @return return the jb_jg
     */
    public String getJb_jg() {
        return jb_jg;
    }

    /**
     * Set jb_jg.
     *
     * @param jb_jg the jb_jg to set
     */
    public void setJb_jg(String jb_jg) {
        this.jb_jg = jb_jg;
    }

    /**
     * Get the jb_csrq.
     *
     * @return return the jb_csrq
     */
    public String getJb_csrq() {
        return jb_csrq;
    }

    /**
     * Set jb_csrq.
     *
     * @param jb_csrq the jb_csrq to set
     */
    public void setJb_csrq(String jb_csrq) {
        this.jb_csrq = jb_csrq;
    }

    /**
     * Get the jb_jkzk.
     *
     * @return return the jb_jkzk
     */
    public String getJb_jkzk() {
        return jb_jkzk;
    }

    /**
     * Set jb_jkzk.
     *
     * @param jb_jkzk the jb_jkzk to set
     */
    public void setJb_jkzk(String jb_jkzk) {
        this.jb_jkzk = jb_jkzk;
    }

    /**
     * Get the jb_zzmm.
     *
     * @return return the jb_zzmm
     */
    public SysDict getJb_zzmm() {
        return jb_zzmm;
    }

    /**
     * Set jb_zzmm.
     *
     * @param jb_zzmm the jb_zzmm to set
     */
    public void setJb_zzmm(SysDict jb_zzmm) {
        this.jb_zzmm = jb_zzmm;
    }

    /**
     * Get the jb_sfzh.
     *
     * @return return the jb_sfzh
     */
    public String getJb_sfzh() {
        return jb_sfzh;
    }

    /**
     * Set jb_sfzh.
     *
     * @param jb_sfzh the jb_sfzh to set
     */
    public void setJb_sfzh(String jb_sfzh) {
        this.jb_sfzh = jb_sfzh;
    }

    /**
     * Get the jb_dh.
     *
     * @return return the jb_dh
     */
    public String getJb_dh() {
        return jb_dh;
    }

    /**
     * Set jb_dh.
     *
     * @param jb_dh the jb_dh to set
     */
    public void setJb_dh(String jb_dh) {
        this.jb_dh = jb_dh;
    }

    /**
     * Get the jb_sj.
     *
     * @return return the jb_sj
     */
    public String getJb_sj() {
        return jb_sj;
    }

    /**
     * Set jb_sj.
     *
     * @param jb_sj the jb_sj to set
     */
    public void setJb_sj(String jb_sj) {
        this.jb_sj = jb_sj;
    }

    /**
     * Get the jb_jl.
     *
     * @return return the jb_jl
     */
    public String getJb_jl() {
        return jb_jl;
    }

    /**
     * Set jb_jl.
     *
     * @param jb_jl the jb_jl to set
     */
    public void setJb_jl(String jb_jl) {
        this.jb_jl = jb_jl;
    }

    /**
     * Get the jb_zp.
     *
     * @return return the jb_zp
     */
    public String getJb_zp() {
        return jb_zp;
    }

    /**
     * Set jb_zp.
     *
     * @param jb_zp the jb_zp to set
     */
    public void setJb_zp(String jb_zp) {
        this.jb_zp = jb_zp;
    }

    /**
     * Get the gz_gzdw.
     *
     * @return return the gz_gzdw
     */
    public String getGz_gzdw() {
        return gz_gzdw;
    }

    /**
     * Set gz_gzdw.
     *
     * @param gz_gzdw the gz_gzdw to set
     */
    public void setGz_gzdw(String gz_gzdw) {
        this.gz_gzdw = gz_gzdw;
    }

    /**
     * Get the gz_gzdw_zw.
     *
     * @return return the gz_gzdw_zw
     */
    public String getGz_gzdw_zw() {
        return gz_gzdw_zw;
    }

    /**
     * Set gz_gzdw_zw.
     *
     * @param gz_gzdw_zw the gz_gzdw_zw to set
     */
    public void setGz_gzdw_zw(String gz_gzdw_zw) {
        this.gz_gzdw_zw = gz_gzdw_zw;
    }

    /**
     * Get the gz_gaxt.
     *
     * @return return the gz_gaxt
     */
    public Boolean getGz_gaxt() {
        return gz_gaxt;
    }

    /**
     * Set gz_gaxt.
     *
     * @param gz_gaxt the gz_gaxt to set
     */
    public void setGz_gaxt(Boolean gz_gaxt) {
        this.gz_gaxt = gz_gaxt;
    }

    /**
     * Get the gz_gaxt_kssj.
     *
     * @return return the gz_gaxt_kssj
     */
    public String getGz_gaxt_kssj() {
        return gz_gaxt_kssj;
    }

    /**
     * Set gz_gaxt_kssj.
     *
     * @param gz_gaxt_kssj the gz_gaxt_kssj to set
     */
    public void setGz_gaxt_kssj(String gz_gaxt_kssj) {
        this.gz_gaxt_kssj = gz_gaxt_kssj;
    }

    /**
     * Get the gz_gzdw_dz_sheng.
     *
     * @return return the gz_gzdw_dz_sheng
     */
    public SysDict getGz_gzdw_dz_sheng() {
        return gz_gzdw_dz_sheng;
    }

    /**
     * Set gz_gzdw_dz_sheng.
     *
     * @param gz_gzdw_dz_sheng the gz_gzdw_dz_sheng to set
     */
    public void setGz_gzdw_dz_sheng(SysDict gz_gzdw_dz_sheng) {
        this.gz_gzdw_dz_sheng = gz_gzdw_dz_sheng;
    }

    /**
     * Get the gz_gzdw_dz_shi.
     *
     * @return return the gz_gzdw_dz_shi
     */
    public String getGz_gzdw_dz_shi() {
        return gz_gzdw_dz_shi;
    }

    /**
     * Set gz_gzdw_dz_shi.
     *
     * @param gz_gzdw_dz_shi the gz_gzdw_dz_shi to set
     */
    public void setGz_gzdw_dz_shi(String gz_gzdw_dz_shi) {
        this.gz_gzdw_dz_shi = gz_gzdw_dz_shi;
    }

    /**
     * Get the gz_gzdw_dz_jd.
     *
     * @return return the gz_gzdw_dz_jd
     */
    public String getGz_gzdw_dz_jd() {
        return gz_gzdw_dz_jd;
    }

    /**
     * Set gz_gzdw_dz_jd.
     *
     * @param gz_gzdw_dz_jd the gz_gzdw_dz_jd to set
     */
    public void setGz_gzdw_dz_jd(String gz_gzdw_dz_jd) {
        this.gz_gzdw_dz_jd = gz_gzdw_dz_jd;
    }

    /**
     * Get the gz_gzdw_dz_yb.
     *
     * @return return the gz_gzdw_dz_yb
     */
    public String getGz_gzdw_dz_yb() {
        return gz_gzdw_dz_yb;
    }

    /**
     * Set gz_gzdw_dz_yb.
     *
     * @param gz_gzdw_dz_yb the gz_gzdw_dz_yb to set
     */
    public void setGz_gzdw_dz_yb(String gz_gzdw_dz_yb) {
        this.gz_gzdw_dz_yb = gz_gzdw_dz_yb;
    }

    /**
     * Get the jy_whcd.
     *
     * @return return the jy_whcd
     */
    public SysDict getJy_whcd() {
        return jy_whcd;
    }

    /**
     * Set jy_whcd.
     *
     * @param jy_whcd the jy_whcd to set
     */
    public void setJy_whcd(SysDict jy_whcd) {
        this.jy_whcd = jy_whcd;
    }

    /**
     * Get the jy_whcd_sszy.
     *
     * @return return the jy_whcd_sszy
     */
    public String getJy_whcd_sszy() {
        return jy_whcd_sszy;
    }

    /**
     * Set jy_whcd_sszy.
     *
     * @param jy_whcd_sszy the jy_whcd_sszy to set
     */
    public void setJy_whcd_sszy(String jy_whcd_sszy) {
        this.jy_whcd_sszy = jy_whcd_sszy;
    }

    /**
     * Get the zy_ywzc.
     *
     * @return return the zy_ywzc
     */
    public String getZy_ywzc() {
        return zy_ywzc;
    }

    /**
     * Set zy_ywzc.
     *
     * @param zy_ywzc the zy_ywzc to set
     */
    public void setZy_ywzc(String zy_ywzc) {
        this.zy_ywzc = zy_ywzc;
    }

    /**
     * Get the zy_jszc.
     *
     * @return return the zy_jszc
     */
    public String getZy_jszc() {
        return zy_jszc;
    }

    /**
     * Set zy_jszc.
     *
     * @param zy_jszc the zy_jszc to set
     */
    public void setZy_jszc(String zy_jszc) {
        this.zy_jszc = zy_jszc;
    }

    /**
     * Get the zy_zgrz.
     *
     * @return return the zy_zgrz
     */
    public String getZy_zgrz() {
        return zy_zgrz;
    }

    /**
     * Set zy_zgrz.
     *
     * @param zy_zgrz the zy_zgrz to set
     */
    public void setZy_zgrz(String zy_zgrz) {
        this.zy_zgrz = zy_zgrz;
    }

    /**
     * Get the yj_szdw.
     *
     * @return return the yj_szdw
     */
    public String getYj_szdw() {
        return yj_szdw;
    }

    /**
     * Set yj_szdw.
     *
     * @param yj_szdw the yj_szdw to set
     */
    public void setYj_szdw(String yj_szdw) {
        this.yj_szdw = yj_szdw;
    }

    /**
     * Get the yj_tjdw.
     *
     * @return return the yj_tjdw
     */
    public String getYj_tjdw() {
        return yj_tjdw;
    }

    /**
     * Set yj_tjdw.
     *
     * @param yj_tjdw the yj_tjdw to set
     */
    public void setYj_tjdw(String yj_tjdw) {
        this.yj_tjdw = yj_tjdw;
    }

    /**
     * Get the yj_zcj.
     *
     * @return return the yj_zcj
     */
    public String getYj_zcj() {
        return yj_zcj;
    }

    /**
     * Set yj_zcj.
     *
     * @param yj_zcj the yj_zcj to set
     */
    public void setYj_zcj(String yj_zcj) {
        this.yj_zcj = yj_zcj;
    }

    /**
     * Get the yj_tjdw_mc.
     *
     * @return return the yj_tjdw_mc
     */
    public String getYj_tjdw_mc() {
        return yj_tjdw_mc;
    }

    /**
     * Set yj_tjdw_mc.
     *
     * @param yj_tjdw_mc the yj_tjdw_mc to set
     */
    public void setYj_tjdw_mc(String yj_tjdw_mc) {
        this.yj_tjdw_mc = yj_tjdw_mc;
    }

    /**
     * Get the xt_zt.
     *
     * @return return the xt_zt
     */
    public ZJZT getXt_zt() {
        return xt_zt;
    }

    /**
     * Set xt_zt.
     *
     * @param xt_zt the xt_zt to set
     */
    public void setXt_zt(ZJZT xt_zt) {
        this.xt_zt = xt_zt;
    }

    /**
     * Get the xt_qy.
     *
     * @return return the xt_qy
     */
    public Boolean getXt_qy() {
        return xt_qy;
    }

    /**
     * Set xt_qy.
     *
     * @param xt_qy the xt_qy to set
     */
    public void setXt_qy(Boolean xt_qy) {
        this.xt_qy = xt_qy;
    }

    /**
     * Get the xt_zt_bz.
     *
     * @return return the xt_zt_bz
     */
    public String getXt_zt_bz() {
        return xt_zt_bz;
    }

    /**
     * Set xt_zt_bz.
     *
     * @param xt_zt_bz the xt_zt_bz to set
     */
    public void setXt_zt_bz(String xt_zt_bz) {
        this.xt_zt_bz = xt_zt_bz;
    }

    /**
     * Get the xt_djr.
     *
     * @return return the xt_djr
     */
    public SysUser getXt_djr() {
        return xt_djr;
    }

    /**
     * Set xt_djr.
     *
     * @param xt_djr the xt_djr to set
     */
    public void setXt_djr(SysUser xt_djr) {
        this.xt_djr = xt_djr;
    }

    /**
     * Get the xt_shr.
     *
     * @return return the xt_shr
     */
    public SysUser getXt_shr() {
        return xt_shr;
    }

    /**
     * Set xt_shr.
     *
     * @param xt_shr the xt_shr to set
     */
    public void setXt_shr(SysUser xt_shr) {
        this.xt_shr = xt_shr;
    }

    /**
     * Get the xt_djsj.
     *
     * @return return the xt_djsj
     */
    public String getXt_djsj() {
        return xt_djsj;
    }

    /**
     * Set xt_djsj.
     *
     * @param xt_djsj the xt_djsj to set
     */
    public void setXt_djsj(String xt_djsj) {
        this.xt_djsj = xt_djsj;
    }

    /**
     * Get the xt_gxsj.
     *
     * @return return the xt_gxsj
     */
    public String getXt_gxsj() {
        return xt_gxsj;
    }

    /**
     * Set xt_gxsj.
     *
     * @param xt_gxsj the xt_gxsj to set
     */
    public void setXt_gxsj(String xt_gxsj) {
        this.xt_gxsj = xt_gxsj;
    }

    /**
     * Get the gzjlList.
     *
     * @return return the gzjlList
     */
    public Set<EshZJGZJL> getGzjlList() {
        return gzjlList;
    }

    /**
     * Set gzjlList.
     *
     * @param gzjlList the gzjlList to set
     */
    public void setGzjlList(Set<EshZJGZJL> gzjlList) {
        this.gzjlList = gzjlList;
    }

    /**
     * Get the jlqkList.
     *
     * @return return the jlqkList
     */
    public Set<EshZJJLQK> getJlqkList() {
        return jlqkList;
    }

    /**
     * Set jlqkList.
     *
     * @param jlqkList the jlqkList to set
     */
    public void setJlqkList(Set<EshZJJLQK> jlqkList) {
        this.jlqkList = jlqkList;
    }

    /**
     * Get the jybjList.
     *
     * @return return the jybjList
     */
    public Set<EshZJJYBJ> getJybjList() {
        return jybjList;
    }

    /**
     * Set jybjList.
     *
     * @param jybjList the jybjList to set
     */
    public void setJybjList(Set<EshZJJYBJ> jybjList) {
        this.jybjList = jybjList;
    }

    /**
     * Get the jzList.
     *
     * @return return the jzList
     */
    public Set<EshZJJZ> getJzList() {
        return jzList;
    }

    /**
     * Set jzList.
     *
     * @param jzList the jzList to set
     */
    public void setJzList(Set<EshZJJZ> jzList) {
        this.jzList = jzList;
    }

    /**
     * Get the yjcgList.
     *
     * @return return the yjcgList
     */
    public Set<EshZJYJCG> getYjcgList() {
        return yjcgList;
    }

    /**
     * Set yjcgList.
     *
     * @param yjcgList the yjcgList to set
     */
    public void setYjcgList(Set<EshZJYJCG> yjcgList) {
        this.yjcgList = yjcgList;
    }

    /**
     * Get the zylbList.
     *
     * @return return the zylbList
     */
    public Set<EshZJZYLB> getZylbList() {
        return zylbList;
    }

    /**
     * Set zylbList.
     *
     * @param zylbList the zylbList to set
     */
    public void setZylbList(Set<EshZJZYLB> zylbList) {
        this.zylbList = zylbList;
    }

    /**
     * Get the xt_sfkw.
     *
     * @return return the xt_sfkw
     */
    public Boolean getXt_sfkw() {
        return xt_sfkw;
    }

    /**
     * Set xt_sfkw.
     *
     * @param xt_sfkw the xt_sfkw to set
     */
    public void setXt_sfkw(Boolean xt_sfkw) {
        this.xt_sfkw = xt_sfkw;
    }
}
