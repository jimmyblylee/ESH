/**
 * Project Name : jwaf-token-api <br>
 * File Name : Func.java <br>
 * Package Name : com.lee.jwaf.token <br>
 * Create Time : 2016-09-18 <br>
 * Create by : jimmyblylee@126.com <br>
 * Copyright © 2006, 2016, Jimmybly Lee. All rights reserved.
 */
package com.lee.jwaf.token;

import java.io.Serializable;

/**
 * ClassName : Func <br>
 * Description : api of Token function <br>
 * Create Time : 2016-09-18 <br>
 * Create by : jimmyblylee@126.com
 */
public interface Func extends Serializable {

    /**
     * @return the id
     */
    public Integer getId();

    /**
     * @param id the id to set
     */
    public void setId(Integer id);

    /**
     * @return the code
     */
    public String getCode();

    /**
     * @param code the code to set
     */
    public void setCode(String code);

    /**
     * @return the name
     */
    public String getName();

    /**
     * @param name the name to set
     */
    public void setName(String name);

    /**
     * @return the seq
     */
    public Integer getSeq();

    /**
     * @param seq the seq to set
     */
    public void setSeq(Integer seq);

    /**
     * @return the type
     */
    public Dict getType();

    /**
     * @param type the type to set
     */
    public void setType(Dict type);

    /**
     * @return the url
     */
    public String getUrl();

    /**
     * @param url the url to set
     */
    public void setUrl(String url);

    /**
     * @return the icon
     */
    public String getIcon();

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon);

    /**
     * @return the isVisible
     */
    public Boolean getIsVisible();

    /**
     * @param isVisible the isVisible to set
     */
    public void setIsVisible(Boolean isVisible);
}
