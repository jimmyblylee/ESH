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
 * Description: 专家登记列表控制器.<br>
 * Created by Jimmybly Lee on 2017/7/2.
 * @author Jimmybly Lee
 */
angular.module('WebApp').controller('ZJSHListCtrl', ['$rootScope', '$scope', "$listService", "$ajaxCall", function ($rootScope, $scope, $listService, $ajaxCall) {
    $.getJSON("packages/esh/views/com/cfg.json", function(data) {
        $scope.cfg = data;
        console.log($scope.cfg);
    });

    $scope.condition = {xt_qy: true, xt_zt: "DSL", xt_sfkw: false};
    $listService.init($scope, {
        pageSizeList: [4, 6, 8, 12, 18, 24],
        pageSize: 4,
        "controller": "ZJInfoController",
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
     * 准备查看实体
     */
    $scope.prepareToView = function (item) {
        var scope = $("#viewZJModalDiv").scope();
        scope.title = "查看专家信息";
        scope.method = "update";
        scope.entity = item;

        scope.$on("submitted", function () {
            $scope.load();
        });
    };

    $scope.take = function (item,task) {
        $ajaxCall.post({
            data : {
                controller: "ZJInfoController",
                method: task,
                id: item.id
            },
            success: function() {
                $scope.load();
            }
        });
    };

}]);
