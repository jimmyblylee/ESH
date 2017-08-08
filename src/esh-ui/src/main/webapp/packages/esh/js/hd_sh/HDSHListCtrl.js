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
 * Description: 活动审核列表控制器.<br>
 * Created by Jimmybly Lee on 2017/7/25.
 * @author Jimmybly Lee
 */
angular.module("WebApp").controller("HDSHListCtrl", ["$rootScope", "$scope", "$ajaxCall", "$listService", function($rootScope, $scope, $ajaxCall, $listService){

    $.getJSON("packages/esh/views/com/cfg.json", function(data) {
        $scope.cfg = data;
    });

    $scope.condition = {qy: true, xz: "PSL", zt: "DSL"};
    $listService.init($scope, {
        "controller": "HDController",
        "method": "query",
        callback: function (success) {
            $scope.list = success.data.result;
        }
    });

    /**
     * 刷新数据
     */
    $scope.load = function () {
        $scope.pageRequest.getResponse();
    };
    $scope.load();

    /**
     * 查看专家需求.
     * @param item
     */
    $scope.prepareToViewXQ = function (item) {
        var scope = $("#hdxqViewModalDiv").scope();
        scope.load(item.id);
    };

    /**
     * 查看专家筛选过程.
     * @param item
     */
    $scope.prepareToViewSJ = function (item) {
        var scope = $("#HDSXZJViewModalDiv").scope();
        scope.load(item.id);
    };

    /**
     * 筛选专家.
     * @param item
     */
    $scope.prepareToTakeSJ = function (item) {
        var scope = $("#HDSXZJTakeModalDiv").scope();
        scope.load(item.id);
    };

    /**
     * 查看专家筛选结果.
     * @param item
     */
    $scope.prepareToViewSJJG = function (item) {
        var scope = $("#HDSXJGViewModalDiv").scope();
        scope.load(item.id);
    };

    /**
     * 受理
     * @param item 活动 entity
     */
    $scope.shouLi = function(item) {
        $ajaxCall.post({
            data: {
                controller: "HDController",
                method: "shouLi",
                id: item.id
            },
            success: function () {
                $scope.load();
            }
        });
    };
}]);
