/*global require*/
/**
 * Created by maks-pain on 12/15/15.
 */


'use strict';

console.log('[MAIN] Init',new Date().toISOString());

require.config({
    baseUrl: '/app',
    waitSeconds: 20,

    paths: {
        jq: '../assets/bower_components/jquery/dist/jquery',
        ng: '../assets/bower_components/angular/angular',
        bootstrap: '../assets/bower_components/angular-bootstrap/ui-bootstrap-tpls',
        router: '../assets/bower_components/angular-ui-router/release/angular-ui-router',
        sockjs: '../assets/bower_components/sockjs/sockjs',
        stomp: '../assets/bower_components/stomp-websocket/lib/stomp',
        twidApp: '../app/app.module'
    },
    shim:{
        ng: {
            exports: 'angular'
        },
        router: ['ng'],
        bootstrap: ['jq','ng'],
        twidApp: ['jq','ng','bootstrap','router','sockjs','stomp']
    }

});

console.log('[MAIN] Kick-start application',new Date().toISOString());

define(['twidApp'], function () {

    angular.element(document).ready(function () {
        angular.bootstrap(document, ['twidder'])
    });
    console.log('[MAIN] Kick-start done',new Date().toISOString());

});