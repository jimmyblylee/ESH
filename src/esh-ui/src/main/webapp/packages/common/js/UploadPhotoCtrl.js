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
 * Description: 上传图片控制器.<br>
 * Created by Jimmybly Lee on 2017/7/5.
 * @author Jimmybly Lee
 */
angular.module("WebApp").controller("UploadPhotoCtrl", ["$rootScope", "$scope", "$http", "$ajaxCall", function ($rootScope, $scope, $http, $ajaxCall) {

    $scope.sourceImage = $rootScope.cfg["defaultPhoto"];

    $scope.upload = function() {
        var files = $scope["outSelector"] ? $("input[type='file'][name='photo']", $($scope["outSelector"])) : $("input[type='file'][name='photo']");
        if (files.length !== 1 || files[0].files.length !== 1) {
            alert("无法找到唯一的文件！");
            return;
        }
        var fd = new FormData();
        fd.append("photo", files[0].files[0]);
        $http({
            method: "POST",
            url: "mvc/dispatch?controller=Base64Controller&method=convertImg2Base64",
            data: fd,
            headers: {
                'Content-Type' : undefined
            },
            transformRequest : angular.identity
        }).success(function(success) {
            $scope.sourceImage = success.result;

            if (!$scope.latestJcrop) {
                var onJcropChange = function(c) {
                    $scope.c = c;
                };
                $("#sourceImageDom").Jcrop({
                    aspectRatio: 400/400,
                    onChange: onJcropChange,
                    onSelect: onJcropChange,
                    boxHeight: 300,
                    onRelease: function(){$scope.c = undefined}
                }, function(){
                    $scope.latestJcrop = this;
                });
            }
            $scope.latestJcrop.setImage("data:image/png;base64,"+$scope.sourceImage);
        });
    };

    $scope.preview = function() {
        $ajaxCall.post({
            data: {
                controller: "Base64Controller",
                method: "formatBase64Img",
                x: $scope.c.x,
                y: $scope.c.y,
                width: $scope.c.w,
                height: $scope.c.h,
                data: $scope.sourceImage
            },
            success: function(res) {
                $scope.previewImage = res.result;
            }
        })
    };

    $scope.submit = function() {
        $scope.$emit("submit", $scope.previewImage);
    }
}]);