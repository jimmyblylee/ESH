/**
 * Project Name : atomikos-jta <br>
 * File Name : SimpleJtaPlatform.java <br>
 * Package Name : com.lee.jta <br>
 * Create Time : 2016-09-17 <br>
 * Create by : jimmyblylee@126.com <br>
 * Copyright © 2006, 2016, Jimmybly Lee. All rights reserved.
 */
package com.lee.jta;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

/**
 * ClassName : SimpleJtaPlatform <br>
 * Description : Simple implementation of JtaPlatform <br>
 * Create Time : 2016-09-17 <br>
 * Create by : jimmyblylee@126.com
 */
public class SimpleJtaPlatform extends AbstractJtaPlatform {

    private static final long serialVersionUID = 8831601546931866840L;

    private static TransactionManager transactionManager;
    private static UserTransaction transaction;

    /*
     * (non-Javadoc)
     * @see org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform#locateTransactionManager()
     */
    @Override
    protected TransactionManager locateTransactionManager() {
        return SimpleJtaPlatform.transactionManager;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform#locateUserTransaction()
     */
    @Override
    protected UserTransaction locateUserTransaction() {
        return SimpleJtaPlatform.transaction;
    }

    /**
     * @param transactionManager the transactionManager to set
     */
    public static void setTransactionManager(TransactionManager transactionManager) {
        SimpleJtaPlatform.transactionManager = transactionManager;
    }

    /**
     * @param transaction the transaction to set
     */
    public static void setTransaction(UserTransaction transaction) {
        SimpleJtaPlatform.transaction = transaction;
    }

}
