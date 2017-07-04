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
 * Description: main.<br>
 * Created by Jimmybly Lee on 2017/6/30.
 * @author Jimmybly Lee
 */
/* Metronic App */
angular.module("WebApp", [
    "ui.router",
    "ui.bootstrap",
    "oc.lazyLoad",
    "ngSanitize",
    "ui.select",

    "chieffancypants.loadingBar"
]);

/* Configure ocLazyLoader(refer: https://github.com/ocombe/ocLazyLoad) */
angular.module('WebApp').config(['$ocLazyLoadProvider', function ($ocLazyLoadProvider) {
    $ocLazyLoadProvider.config({
        // global configs go here
    });
}]);

/********************************************
 BEGIN: BREAKING CHANGE in AngularJS v1.3.x:
 *********************************************/
/**
 `$controller` will no longer look for controllers on `window`.
 The old behavior of looking on `window` for controllers was originally intended
 for use in examples, demos, and toy apps. We found that allowing global controller
 functions encouraged poor practices, so we resolved to disable this behavior by
 default.

 To migrate, register your controllers with modules rather than exposing them
 as globals:

 Before:

 ```javascript
 function MyController() {
  // ...
}
 ```

 After:

 ```javascript
 angular.module('myApp', []).controller('MyController', [function() {
  // ...
}]);

 Although it's not recommended, you can re-enable the old behavior like this:

 ```javascript
 angular.module('myModule').config(['$controllerProvider', function($controllerProvider) {
  // this option might be handy for migrating old apps, but please don't use it
  // in new ones!
  $controllerProvider.allowGlobals();
}]);
 **/

//AngularJS v1.3.x workaround for old style controller declarition in HTML
angular.module('WebApp').config(['$controllerProvider', function ($controllerProvider) {
    // this option might be handy for migrating old apps, but please don't use it
    // in new ones!
    $controllerProvider.allowGlobals();
}]);

angular.module('WebApp').factory('httpInterceptor', ['$q', function ($q) {
    return {
        'response': function (response) {
            return response;
        },
        'responseError': function (response) {
            var errorDetail = "";
            if (response.status === 404) {
                errorDetail += "请求发生了错误：";
                errorDetail += "<span>" + response.status + "</span>";
                errorDetail += ", 系统找不到请求：";
                errorDetail += response.config.url;
            } else if (response.status === 401 || response.status === 403 || response.status === 417) {
                if (response.data && response.data['errLevel'] && response.data['errLevel'] === "warning") {
                    errorDetail += response.data['errMsg'];
                } else {
                    errorDetail += "请求发生了错误：";
                    errorDetail += "<span>" + response.status + "</span>";
                    errorDetail += response.status === 417 ? ", 请求过程中发生业务异常：" : ", 您无权访问此请求：";
                    errorDetail += "<span class='margin-right-10'></span>";
                    errorDetail += "<span class='bold margin-right-10 font-grey-cascade'>请求目标:</span>" + response.config.data.controller;
                    errorDetail += "<span class='margin-right-10'></span>";
                    errorDetail += "<span class='margin-right-10'></span>";
                    errorDetail += "<span class='bold margin-right-10 font-grey-cascade'>请求函数:</span>" + response.config.data.method;
                    errorDetail += "<span class='margin-right-10'></span>";
                    errorDetail += "<span class='margin-right-10'></span>";
                    errorDetail += "<span class='bold margin-right-10 font-grey-cascade'>编码:</span>" + response.data['errCode'];
                    errorDetail += "<span class='margin-right-10'></span>";
                    errorDetail += "<span class='margin-right-10'></span>";
                    errorDetail += "<span class='bold margin-right-10 font-grey-cascade'>描述:</span> " + response.data['errMsg'];
                }
            }
            App.alert({
                container: $(".page-container .page-content .container .main-error-div"),
                place: 'append', // append or prepent in container
                type: 'warning', // alert's type
                message: errorDetail, // alert's message
                close: true, // make alert closable
                icon: 'fa fa-warning' // put icon class before the message
            });
            return $q.reject(response);
        },
        'request': function (config) {
            //处理AJAX请求（否则后台IsAjaxRequest()始终false）
            config.headers['X-Requested-With'] = 'XMLHttpRequest';
            return config;
        },
        'requestError': function (config) {
            return config;
        }
    };
}]);
angular.module('WebApp').config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
    $httpProvider.defaults.headers.post = {
        'Content-Type': 'application/x-www-form-urlencoded'
    };
}]);

/********************************************
 END: BREAKING CHANGE in AngularJS v1.3.x:
 *********************************************/

/* Setup global settings */
angular.module('WebApp').factory('settings', ['$rootScope', function ($rootScope) {
    // supported languages
    var settings = {
        layout: {
            pageContentWhite: true, // set page content layout
            pageBodySolid: false, // solid body color state
            pageAutoScrollOnLoad: 1000 // auto scroll to top on page load
        },
        assetsPath: 'resources',
        globalPath: 'resources/global',
        layoutPath: 'resources/layouts/layout3'
    };

    $rootScope.settings = settings;

    return settings;
}]);

/***
 Layout Partials.
 By default the partials are loaded through AngularJS ng-include directive. In case they loaded in server side(e.g: PHP include function) then below partial
 initialization can be disabled and Layout.init() should be called on page load complete as explained above.
 ***/

/* Setup Routing For All Pages */
angular.module('WebApp').config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $.ajaxSettings.async = false;
    var cacheVersion = "?" + $("html").attr("cacheVersion");

    // 配置路由跳转相关配置信息
    $stateProvider.state("home", {
        url: "/home.html",
        templateUrl: "packages/esh/views/home/home.html" + cacheVersion,
        data: {"pageTitle": "首页"},
        controller: "HomeCtrl",
        resolve: {
            deps: ["$ocLazyLoad", function ($ocLazyLoad) {
                return $ocLazyLoad.load({
                    name: "WebApp",
                    files: ["packages/esh/js/HomeCtrl.js" + cacheVersion]
                });
            }]
        }
    });
    $.getJSON("packages/config.json" + cacheVersion, function (configs) {
        $.each(configs, function (name, cfg) {
            $.getJSON(cfg["url"] + cacheVersion, function (states) {
                $.each(states, function (key, data) {
                    var files = [];
                    $.each(data["files"], function(idx, url) {
                        files.push(url + cacheVersion);
                    });
                    $stateProvider.state(key, {
                        url: "/" + key + ".html",
                        templateUrl: data["templateUrl"] + cacheVersion,
                        data: data["data"],
                        controller: data["controller"],
                        resolve: {
                            deps: ["$ocLazyLoad", function ($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    name: "WebApp",
                                    insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                                    files: files
                                });
                            }]
                        }
                    });
                });
            });
        });
    });

    // Redirect any unmatched url
    $urlRouterProvider.otherwise("/404.html");

    $.ajaxSettings.async = true;
}]);

/* Init global settings and run the app */
angular.module('WebApp').run(["$rootScope", "settings", "$state", "$location", function ($rootScope, settings, $state, $location) {
    $rootScope.cacheVersion = "?" + $("html").attr("cacheVersion");
    $rootScope.$state = $state; // state to be accessed from view
    $rootScope.$settings = settings; // state to be accessed from view

    /*
     路由和权限配置过程，需要同步处理（不能异步）
     1、获得当前权限
     2、注册落于变化监听
     */

    // 1、获得当前用户令牌，并根据令牌，尝试配置跳转权限
    $rootScope.reloadToken = function () {
        $.ajax({
            url: "mvc/dispatch", async: false,
            data: {controller: "LoginController", method: "getCurrentToken"},
            success: function (data) {
                $rootScope.token = {"user": data.user, "funcs": data.funcs, "funcTree": data.funcTree};
                $rootScope.token.user.photo = data.photo.data;
                $rootScope.token.user.type = $rootScope.token.user.id === -1 ?
                    "ANONYMOUS" : $rootScope.token.user.id === -2 ? "ADMIN" : "NORMAL";
            }
        });
    };
    $rootScope.reloadToken();

    // 2、注册路由变化监听器
    $rootScope.$on("$stateChangeStart", function (event, toState) {
        var isValid = false;
        var commonFuncList = ["403", "404", "500", "about", "coming", "contact", "help", "lock", "login", "home"];
        $.each(commonFuncList, function (idx, func) {
            if (func === toState.name) {
                isValid = true;
            }
        });
        if (!isValid) {
            $.each($rootScope.token.funcs, function (idx, data) {
                if (data.code === toState.name) {
                    isValid = true;
                }
            });
        }
        if (!isValid) {
            event.preventDefault();
            $state.go("403");
        }
    });
}]);
