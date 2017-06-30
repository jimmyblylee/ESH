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

import javax.persistence.*;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

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
public final class EshZJ {

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
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ZJ_JB_JL")
    private String jb_jl;

    /** 基本信息：照片，base64编码.**/
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ZJ_JB_ZP")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJb_xm() {
        return jb_xm;
    }

    public void setJb_xm(String jb_xm) {
        this.jb_xm = jb_xm;
    }

    public String getJb_xb() {
        return jb_xb;
    }

    public void setJb_xb(String jb_xb) {
        this.jb_xb = jb_xb;
    }

    public String getJb_mz() {
        return jb_mz;
    }

    public void setJb_mz(String jb_mz) {
        this.jb_mz = jb_mz;
    }

    public String getJb_jg() {
        return jb_jg;
    }

    public void setJb_jg(String jb_jg) {
        this.jb_jg = jb_jg;
    }

    public String getJb_csrq() {
        return jb_csrq;
    }

    public void setJb_csrq(String jb_csrq) {
        this.jb_csrq = jb_csrq;
    }

    public String getJb_jkzk() {
        return jb_jkzk;
    }

    public void setJb_jkzk(String jb_jkzk) {
        this.jb_jkzk = jb_jkzk;
    }

    public SysDict getJb_zzmm() {
        return jb_zzmm;
    }

    public void setJb_zzmm(SysDict jb_zzmm) {
        this.jb_zzmm = jb_zzmm;
    }

    public String getJb_sfzh() {
        return jb_sfzh;
    }

    public void setJb_sfzh(String jb_sfzh) {
        this.jb_sfzh = jb_sfzh;
    }

    public String getJb_dh() {
        return jb_dh;
    }

    public void setJb_dh(String jb_dh) {
        this.jb_dh = jb_dh;
    }

    public String getJb_sj() {
        return jb_sj;
    }

    public void setJb_sj(String jb_sj) {
        this.jb_sj = jb_sj;
    }

    public String getJb_jl() {
        return jb_jl;
    }

    public void setJb_jl(String jb_jl) {
        this.jb_jl = jb_jl;
    }

    public String getJb_zp() {
        return jb_zp;
    }

    public void setJb_zp(String jb_zp) {
        this.jb_zp = jb_zp;
    }

    public String getGz_gzdw() {
        return gz_gzdw;
    }

    public void setGz_gzdw(String gz_gzdw) {
        this.gz_gzdw = gz_gzdw;
    }

    public String getGz_gzdw_zw() {
        return gz_gzdw_zw;
    }

    public void setGz_gzdw_zw(String gz_gzdw_zw) {
        this.gz_gzdw_zw = gz_gzdw_zw;
    }

    public Boolean getGz_gaxt() {
        return gz_gaxt;
    }

    public void setGz_gaxt(Boolean gz_gaxt) {
        this.gz_gaxt = gz_gaxt;
    }

    public String getGz_gaxt_kssj() {
        return gz_gaxt_kssj;
    }

    public void setGz_gaxt_kssj(String gz_gaxt_kssj) {
        this.gz_gaxt_kssj = gz_gaxt_kssj;
    }

    public SysDict getGz_gzdw_dz_sheng() {
        return gz_gzdw_dz_sheng;
    }

    public void setGz_gzdw_dz_sheng(SysDict gz_gzdw_dz_sheng) {
        this.gz_gzdw_dz_sheng = gz_gzdw_dz_sheng;
    }

    public String getGz_gzdw_dz_shi() {
        return gz_gzdw_dz_shi;
    }

    public void setGz_gzdw_dz_shi(String gz_gzdw_dz_shi) {
        this.gz_gzdw_dz_shi = gz_gzdw_dz_shi;
    }

    public String getGz_gzdw_dz_jd() {
        return gz_gzdw_dz_jd;
    }

    public void setGz_gzdw_dz_jd(String gz_gzdw_dz_jd) {
        this.gz_gzdw_dz_jd = gz_gzdw_dz_jd;
    }

    public String getGz_gzdw_dz_yb() {
        return gz_gzdw_dz_yb;
    }

    public void setGz_gzdw_dz_yb(String gz_gzdw_dz_yb) {
        this.gz_gzdw_dz_yb = gz_gzdw_dz_yb;
    }

    public SysDict getJy_whcd() {
        return jy_whcd;
    }

    public void setJy_whcd(SysDict jy_whcd) {
        this.jy_whcd = jy_whcd;
    }

    public String getJy_whcd_sszy() {
        return jy_whcd_sszy;
    }

    public void setJy_whcd_sszy(String jy_whcd_sszy) {
        this.jy_whcd_sszy = jy_whcd_sszy;
    }

    public String getZy_ywzc() {
        return zy_ywzc;
    }

    public void setZy_ywzc(String zy_ywzc) {
        this.zy_ywzc = zy_ywzc;
    }

    public String getZy_jszc() {
        return zy_jszc;
    }

    public void setZy_jszc(String zy_jszc) {
        this.zy_jszc = zy_jszc;
    }

    public String getZy_zgrz() {
        return zy_zgrz;
    }

    public void setZy_zgrz(String zy_zgrz) {
        this.zy_zgrz = zy_zgrz;
    }

    public String getYj_szdw() {
        return yj_szdw;
    }

    public void setYj_szdw(String yj_szdw) {
        this.yj_szdw = yj_szdw;
    }

    public String getYj_tjdw() {
        return yj_tjdw;
    }

    public void setYj_tjdw(String yj_tjdw) {
        this.yj_tjdw = yj_tjdw;
    }

    public String getYj_zcj() {
        return yj_zcj;
    }

    public void setYj_zcj(String yj_zcj) {
        this.yj_zcj = yj_zcj;
    }

    public String getYj_tjdw_mc() {
        return yj_tjdw_mc;
    }

    public void setYj_tjdw_mc(String yj_tjdw_mc) {
        this.yj_tjdw_mc = yj_tjdw_mc;
    }

    public ZJZT getXt_zt() {
        return xt_zt;
    }

    public void setXt_zt(ZJZT xt_zt) {
        this.xt_zt = xt_zt;
    }

    public Boolean getXt_qy() {
        return xt_qy;
    }

    public void setXt_qy(Boolean xt_qy) {
        this.xt_qy = xt_qy;
    }

    public String getXt_zt_bz() {
        return xt_zt_bz;
    }

    public void setXt_zt_bz(String xt_zt_bz) {
        this.xt_zt_bz = xt_zt_bz;
    }

    public SysUser getXt_djr() {
        return xt_djr;
    }

    public void setXt_djr(SysUser xt_djr) {
        this.xt_djr = xt_djr;
    }

    public SysUser getXt_shr() {
        return xt_shr;
    }

    public void setXt_shr(SysUser xt_shr) {
        this.xt_shr = xt_shr;
    }

    public String getXt_djsj() {
        return xt_djsj;
    }

    public void setXt_djsj(String xt_djsj) {
        this.xt_djsj = xt_djsj;
    }

    public String getXt_gxsj() {
        return xt_gxsj;
    }

    public void setXt_gxsj(String xt_gxsj) {
        this.xt_gxsj = xt_gxsj;
    }
}
