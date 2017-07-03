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
 * Description: 登录控制器.<br>
 * Created by Jimmybly Lee on 2017/7/2.
 * @author Jimmybly Lee
 */
angular.module('WebApp').controller('LoginController', ['$rootScope', '$scope', '$ajaxCall', function ($rootScope, $scope, $ajaxCall) {

    $scope.$on("$viewContentLoaded", function() {
        // 处理“记住我”
        if (Cookies.get("userAccount")) {
            $scope.user = {account: Cookies.get("userAccount")};
            $("input[ng-model='user.pwd']").focus();
            $("#rememberme").attr("checked", true);
        } else {
            $("input[ng-model='user.account']").focus();
        }
    });

    $scope.keyLogin = function(event, user) {
        if (event.originalEvent.charCode === 13) {
            $scope.login(user);
        }
    };

    $scope.login = function (user) {
        // 处理“记住我”
        if ($("#rememberme").attr("checked")) {
            Cookies.set("userAccount", user.account);
        } else {
            Cookies.remove("userAccount");
        }
        // 登录
        if (!user || !user.account || !user.pwd) {
            App.alert({
                container: $('.login .content .login-form .msg'), // alerts parent container
                place: 'append', // append or prepent in container
                type: 'warning', // alert's type
                message: '账号、密码不能为空！', // alert's message
                close: true, // make alert closable
                icon: 'fa fa-warning' // put icon class before the message
            });
            return;
        }
        $ajaxCall.post({
            data: {
                "controller": "LoginController",
                "method": "login",
                "account": user.account,
                "pwd": user.pwd
            },
            success: function (req) {
                if (req.result) {
                    $rootScope.reloadToken();
                    $rootScope.$state.go("home");
                } else {
                    App.alert({
                        container: $('.login .content .login-form .msg'), // alerts parent container
                        place: 'append', // append or prepent in container
                        type: 'warning', // alert's type
                        message: '用户名或密码错误，请检查用户名和密码！', // alert's message
                        close: true, // make alert closable
                        icon: 'fa fa-warning' // put icon class before the message
                    });
                }
            }
        });
    };
}]);
