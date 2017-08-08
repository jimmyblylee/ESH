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

/**
 * Description: TODO.<br>
 * Created by Jimmybly Lee on 2017/7/28.
 * @author Jimmybly Lee
 */
angular.module("WebApp").controller("HDXQCtrl", ["$rootScope", "$scope", "$ajaxCall", function($rootScope, $scope, $ajaxCall){

    // 专业类别典列表
    $ajaxCall.getDictList($scope, "ZYLB", 'zylbList');
    // 需求条件类型
    $ajaxCall.getDictList($scope, "TJLX", 'tjlxList');

    /**
     * 为活动添加需求
     */
    $scope.addXQ = function() {
        $ajaxCall.post({
            data: {
                controller: "HDController",
                method: "createXQ",
                entity: JSON.stringify({
                    tdcq: false,
                    hd: {id: $scope.entity.id},
                    zylb: {id: -1050101},
                    rs: 1
                })
            },
            success: function (res) {
                $scope.entity.xqList.push(res.result);
            }
        });
    };

    /**
     * 为需求添加条件.
     * @param xq 需求
     */
    $scope.addTJ = function(xq) {
        $ajaxCall.post({
            data: {
                controller: "HDController",
                method: "createXQTJ",
                entity: JSON.stringify({
                    xq: {id: xq.id},
                    lx: {id: -10101},
                    z: ''
                })
            },
            success: function (res) {
                xq.tjList.push(res.result);
            }
        });
    };

    /**
     * 为需求添加库外专家.
     * @param xq
     */
    $scope.addKW = function(xq) {
        $ajaxCall.post({
            data: {
                controller: "HDController",
                method: "queryXQKW",
                entity: JSON.stringify({
                    xq: {id: xq.id},
                    zj: {id : -20}
                })
            },
            success: function (res) {
                xq.kwList.push(res.result);
            }
        });
    };

    /**
     * 为活动删除指定需求.
     * @param idx 序号
     * @param xq 需求
     */
    $scope.deleteXQ = function(idx, xq) {
        $ajaxCall.post({
            data: {
                controller: "HDController",
                method: "removeXQ",
                id: xq.id
            },
            success: function () {
                $scope.entity.xqList.splice(idx, 1);
            }
        });
    };

    /**
     * 为需求删除条件
     * @param idx 序号
     * @param tj 条件
     * @param xq 需求
     */
    $scope.deleteTJ = function(idx, tj, xq) {
        $ajaxCall.post({
            data: {
                controller: "HDController",
                method: "removeXQTJ",
                id: tj.id
            },
            success: function () {
                xq.tjList.splice(idx, 1);
            }
        });
    };

    /**
     * 删除库外专家.
     * @param idx 序号
     * @param kw 库外
     * @param xq 需求
     */
    $scope.deleteKW = function(idx, kw, xq) {
        $ajaxCall.post({
            data: {
                controller: "HDController",
                method: "removeXQKW",
                id: kw.id
            },
            success: function () {
                xq.kwList.splice(idx, 1);
            }
        });
    };

    /**
     * 更新需求
     * @param xq 需求
     */
    $scope.updateXQ = function(xq) {
        $ajaxCall.post({
            data: {
                controller: "HDController",
                method: "updateXQ",
                entity: JSON.stringify(xq)
            }
        });
    }
}]);
