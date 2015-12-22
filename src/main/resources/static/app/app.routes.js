/*
/!**
 * Created by maks on 12/22/15.
 *!/

define(['./app.module'], function () {
    "use strict";
    console.log('[APP.ROUTES] Load');

    angular
        .module('twidder')
        .config(configRoutes)
        .run(configureRootScope);

    configRoutes.$inject = ['$urlRouterProvider', '$locationProvider'];
    function configRoutes($urlRouterProvider, $locationProvider) {

        $urlRouterProvider
            .otherwise('/');

    }

    configureRootScope.$inject = ['$rootScope', '$state', '$stateParams']
    function configureRootScope($rootScope, $state, $stateParams) {

        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;

    }


});*/
