/**
 * Project Name : jwaf-token-api <br>
 * File Name : User.java <br>
 * Package Name : com.lee.jwaf.token <br>
 * Create Time : 2016-09-18 <br>
 * Create by : jimmyblylee@126.com <br>
 * Copyright © 2006, 2016, Jimmybly Lee. All rights reserved.
 */
package com.lee.jwaf.token;

import java.io.Serializable;

/**
 * ClassName : User <br>
 * Description : api of Token user <br>
 * Create Time : 2016-09-18 <br>
 * Create by : jimmyblylee@126.com
 */
public interface User extends Serializable {

    /**
     * @return the id
     */
    public Integer getId();

    /**
     * @param id the id to set
     */
    public void setId(Integer id);

    /**
     * @return the org
     */
    public Org getOrg();

    /**
     * @param org the dept to set
     */
    public void setOrg(Org org);

    /**
     * @return the name
     */
    public String getName();

    /**
     * @param name the name to set
     */
    public void setName(String name);

    /**
     * @return the account
     */
    public String getAccount();

    /**
     * @param account the account to set
     */
    public void setAccount(String account);

    /**
     * @return the type
     */
    public Dict getType();

    /**
     * @param type the type to set
     */
    public void setType(Dict type);
}
