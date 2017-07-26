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
 * Description: 活动登记选择专家.<br>
 * Created by Jimmybly Lee on 2017/7/27.
 * @author Jimmybly Lee
 */

angular.module("WebApp").controller("HDDJSelectZJCtrl", ["$rootScope", "$scope", "$ajaxCall", "$listService", function($rootScope, $scope, $ajaxCall, $listService){

    $scope.condition = {xt_qy: true};
    $listService.init($scope, {
        "controller": "ZJInfoController",
        "method": "query",
        callback: function (success) {
            $scope.searchList = success.data.result;
        }
    });

    /**
     * 刷新数据
     */
    $scope.search = function () {
        $scope.pageRequest.getResponse();
    };

    $scope.select = function(zj) {
        $ajaxCall.post({
            data : {
                controller: "HDController",
                method: "assignZJ",
                "zjId": zj.id,
                "hdId": $scope.entity.id
            },
            success: function() {
                $scope.loadSelected();
            }
        });
    };

    $scope.deselect = function(zj) {
        $ajaxCall.post({
            data : {
                controller: "HDController",
                method: "removeZJ",
                "hdzjId": zj.id
            },
            success: function() {
                $scope.loadSelected();
            }
        });
    };

    $scope.loadSelected = function() {
        if ($scope.entity) {
            $ajaxCall.post({
                data : {
                    controller: "HDController",
                    method: "queryHDZJ",
                    hdId: $scope.entity.id
                },
                success: function(res) {
                    $scope.selectedList = res.result;
                    $scope.entity.selectedList = $scope.selectedList;
                }
            });
        }
    };
}]);
