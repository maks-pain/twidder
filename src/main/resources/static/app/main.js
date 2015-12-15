/*global require*/
/**
 * Created by maks-pain on 12/15/15.
 */


'use strict';

console.log('[MAIN] Init');

require.config({
    baseUrl: '/app',
    waitSeconds: 20,

    paths: {
        twidApp: '/app/app.module'
    }

});

console.log('[MAIN] Kick-start application');

define(['twidApp'], function () {

    angular.element(document).ready(function () {
        angular.bootstrap(document, ['twidder'])
    });
    console.log('[MAIN] Kick-start done');

});