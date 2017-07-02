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
 * Description: PageHeadController.<br>
 * Created by Jimmybly Lee on 2017/6/30.
 * @author Jimmybly Lee
 */
/* Setup Layout Part - Sidebar */
MetronicApp.controller('PageHeadController', ['$scope', function ($scope) {

    // Handle Theme Settings
    var handleTheme = function () {

        var body = $('body');
        var panel = $('.theme-panel');

        if ($('.page-head > .container-fluid').size() === 1) {
            $('.theme-setting-layout', panel).val("fluid");
        } else {
            $('.theme-setting-layout', panel).val("boxed");
        }

        if ($('.top-menu li.dropdown.dropdown-dark').size() > 0) {
            $('.theme-setting-top-menu-style', panel).val("dark");
        } else {
            $('.theme-setting-top-menu-style', panel).val("light");
        }

        if (body.hasClass("page-header-top-fixed")) {
            $('.theme-setting-top-menu-mode', panel).val("fixed");
        } else {
            $('.theme-setting-top-menu-mode', panel).val("not-fixed");
        }

        if ($('.hor-menu.hor-menu-light').size() > 0) {
            $('.theme-setting-mega-menu-style', panel).val("light");
        } else {
            $('.theme-setting-mega-menu-style', panel).val("dark");
        }

        if (body.hasClass("page-header-menu-fixed")) {
            $('.theme-setting-mega-menu-mode', panel).val("fixed");
        } else {
            $('.theme-setting-mega-menu-mode', panel).val("not-fixed");
        }

        //handle theme layout
        var resetLayout = function () {
            $("body").
            removeClass("page-header-top-fixed").
            removeClass("page-header-menu-fixed");

            $('.page-header-top > .container-fluid').removeClass("container-fluid").addClass('container');
            $('.page-header-menu > .container-fluid').removeClass("container-fluid").addClass('container');
            $('.page-head > .container-fluid').removeClass("container-fluid").addClass('container');
            $('.page-content > .container-fluid').removeClass("container-fluid").addClass('container');
            $('.page-prefooter > .container-fluid').removeClass("container-fluid").addClass('container');
            $('.page-footer > .container-fluid').removeClass("container-fluid").addClass('container');
        };

        var setLayout = function () {

            var layoutMode = $('.theme-setting-layout', panel).val();
            var headerTopMenuStyle = $('.theme-setting-top-menu-style', panel).val();
            var headerTopMenuMode = $('.theme-setting-top-menu-mode', panel).val();
            var headerMegaMenuStyle = $('.theme-setting-mega-menu-style', panel).val();
            var headerMegaMenuMode = $('.theme-setting-mega-menu-mode', panel).val();

            resetLayout(); // reset layout to default state

            if (layoutMode === "fluid") {
                $('.page-header-top > .container').removeClass("container").addClass('container-fluid');
                $('.page-header-menu > .container').removeClass("container").addClass('container-fluid');
                $('.page-head > .container').removeClass("container").addClass('container-fluid');
                $('.page-content > .container').removeClass("container").addClass('container-fluid');
                $('.page-prefooter > .container').removeClass("container").addClass('container-fluid');
                $('.page-footer > .container').removeClass("container").addClass('container-fluid');

                //App.runResizeHandlers();
            }

            if (headerTopMenuStyle === 'dark') {
                $(".top-menu > .navbar-nav > li.dropdown").addClass("dropdown-dark");
            } else {
                $(".top-menu > .navbar-nav > li.dropdown").removeClass("dropdown-dark");
            }

            if (headerTopMenuMode === 'fixed') {
                $("body").addClass("page-header-top-fixed");
            } else {
                $("body").removeClass("page-header-top-fixed");
            }

            if (headerMegaMenuStyle === 'light') {
                $(".hor-menu").addClass("hor-menu-light");
            } else {
                $(".hor-menu").removeClass("hor-menu-light");
            }

            if (headerMegaMenuMode === 'fixed') {
                $("body").addClass("page-header-menu-fixed");
            } else {
                $("body").removeClass("page-header-menu-fixed");
            }
        };

        // handle theme colors
        var setColor = function (color) {
            var color_ = (App.isRTL() ? color + '-rtl' : color);
            $('#style_color').attr("href", Layout.getLayoutCssPath() + 'themes/' + color_ + ".min.css");
            var logoLink = $('.page-logo a');
            logoLink.removeClass();
            if (color === 'default') {
                logoLink.addClass('font-green');
            } else if (color === 'yellow-orange') {
                logoLink.addClass('font-yellow-gold');
            } else {
                logoLink.addClass('font-' + color);
            }
        };

        $('.theme-colors > li', panel).click(function () {
            var color = $(this).attr("data-theme");
            setColor(color);
            $('.theme-colors > li', panel).removeClass("active");
            $(this).addClass("active");
        });

        $('.theme-setting-top-menu-mode', panel).change(function(){
            var headerTopMenuMode = $('.theme-setting-top-menu-mode', panel).val();
            var headerMegaMenuMode = $('.theme-setting-mega-menu-mode', panel).val();

            if (headerMegaMenuMode === "fixed") {
                alert("顶层菜单和导航菜单不能同时悬挂！");
                $('.theme-setting-mega-menu-mode', panel).val("not-fixed");
                headerTopMenuMode = 'not-fixed';
            }
        });

        $('.theme-setting-mega-menu-mode', panel).change(function(){
            var headerTopMenuMode = $('.theme-setting-top-menu-mode', panel).val();
            var headerMegaMenuMode = $('.theme-setting-mega-menu-mode', panel).val();

            if (headerTopMenuMode === "fixed") {
                alert("顶层菜单和导航菜单不能同时悬挂！");
                $('.theme-setting-top-menu-mode', panel).val("not-fixed");
                headerTopMenuMode = 'not-fixed';
            }
        });

        $('.theme-setting', panel).change(setLayout);
    };
    $scope.$on('$includeContentLoaded', function () {
        // handles style customer tool
        handleTheme();
    });
}]);
