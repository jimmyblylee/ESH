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
var MetronicApp = angular.module("MetronicApp", [
    "ui.router",
    "ui.bootstrap",
    "oc.lazyLoad",
    "ngSanitize"
]);

/* Configure ocLazyLoader(refer: https://github.com/ocombe/ocLazyLoad) */
MetronicApp.config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
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
MetronicApp.config(['$controllerProvider', function($controllerProvider) {
  // this option might be handy for migrating old apps, but please don't use it
  // in new ones!
  $controllerProvider.allowGlobals();
}]);

/********************************************
 END: BREAKING CHANGE in AngularJS v1.3.x:
*********************************************/

/* Setup global settings */
MetronicApp.factory('settings', ['$rootScope', function($rootScope) {
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

/* Setup Rounting For All Pages */
MetronicApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    // Redirect any unmatched url
    $urlRouterProvider.otherwise("/home.html");
    $stateProvider.state("home", {
        url: "/home.html",
        templateUrl: "packages/esh/views/home/home.html",
        data: { "pageTitle": "首页" },
        controller: "HomeCtrl",
        resolve: {
            deps: ["$ocLazyLoad", function($ocLazyLoad) {
                return $ocLazyLoad.load({
                    name: "MetronicApp",
                    files: ["packages/esh/js/HomeCtrl.js"],
                    cache: false
                });
            }]
        }
    });

    $.getJSON("packages/config.json", function(configs) {
        $.each(configs, function(key, cfg) {
            $.getJSON(cfg["url"], function(states) {
                $.each(states, function(key, data) {
                    $stateProvider.state(key, {
                        url: "/" + key + ".html",
                        templateUrl: data["templateUrl"],
                        data: data["data"],
                        controller: data["controller"],
                        resolve: {
                            deps: ["$ocLazyLoad", function($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    name: "MetronicApp",
                                    insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                                    files: data["files"],
                                    cache: false
                                });
                            }]
                        }
                    });
                });
            })
        });
    });
}]);

/* Init global settings and run the app */
MetronicApp.run(["$rootScope", "settings", "$state", "$http", function($rootScope, settings, $state, $http) {
    $rootScope.cacheVersion = "?" + $("html").attr("cacheVersion");
    $rootScope.$state = $state; // state to be accessed from view
    $rootScope.$settings = settings; // state to be accessed from view
    $rootScope.reloadToken = function() {
        $http({
            method : 'post',
            url : 'mvc/dispatch',
            data : {
                "controller": "LoginController",
                "method": "getCurrentToken"
            },
            headers : {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            transformRequest : function(obj) {
                var str = [];
                $.each(obj, function(idx, data){
                    str.push(encodeURIComponent(idx) + "=" + encodeURIComponent(data));
                });
                return str.join("&");
            }
        }).success(function(req) {
            $rootScope.token = {
                "user": req.user,
                "funcs": req.funcs,
                "funcTree": req.funcTree
            };
            if ($rootScope.token.user.id === -1) {
                $rootScope.token.user.type = "ANONYMOUS";
            } else if ($rootScope.token.user.id === -2) {
                $rootScope.token.user.type = "ADMIN";
            } else if ($rootScope.token.user.id > 0) {
                $rootScope.token.user.type = "NORMAL";
            }
        });
    };
    $rootScope.reloadToken();
}]);
