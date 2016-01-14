/**
 * Created by maks-pain on 12/22/15.
 */

define(['./user.module', '../common/ws.service'], function () {
    "use strict";

    console.log('[USER.SERVICE] Load', new Date().toISOString());

    userService.$inject = ['$log', '$q', 'wsService']

    return angular.module('twidder.user')
        .factory('userService', userService);


    function userService($log, $q, wsService) {
        $log = $log.getInstance('userService');
        $log.info('[INIT]')

        // Prefixes
        var topic = '/topic/user/';
        var userDst = '/app/user/'

        var self = this;
        self.currentUser = $q.defer();
        self.defNew = {};
        self.defGet = {};
        self.defAll = {};
        self.stompClient = null;
        self.wsSubscriptions = [
            {dst: topic + 'create', fn: onCreateUser},
            {dst: topic + 'get', fn: onGetUser},
            {dst: topic + 'all', fn: onAllUsers}
        ];
        /// INIT ///
        self.connectionPromise = wsService.getConnection();
        self.connectionPromise.then(function (stompClient) {
            self.wsSubscriptions.map(function (sub) {
                stompClient.subscribe(sub.dst, sub.fn);
            });
            self.stompClient = stompClient;
            var username = wsService.getUsername();
            $log.info('username', username);

            self.currentUser.resolve(getUser(username));
            $log.debug('stompClient', stompClient);
        });

        return {
            name: 'userService',
            getCurrentUser: getCurrentUser,
            getUser: getUser,
            createUser: createUser,
            getAllUsers: getAllUsers,
            subscribeToUpdates: subscribeToUpdates
        };

        function getCurrentUser() {
            return self.currentUser.promise;
        }

        function createUser(user) {
            self.defNew = $q.defer();
            self.connectionPromise.then(function (client) {
                client.send(userDst + 'create', {/*headers*/}, JSON.stringify(user))
            });
            return self.defNew.promise;
        }

        function onCreateUser(msg) {
            $log.debug('onCreateUser msg=', msg);
            self.defNew.resolve(JSON.parse(msg.body));
        }

        function getUser(name) {
            self.defGet = $q.defer();
            self.connectionPromise.then(function (client) {
                client.send(userDst + 'get', {/*headers*/}, JSON.stringify({'username': name}));
                $log.debug('getUser', name);
            });
            return self.defGet.promise;
        }

        function onGetUser(msg) {
            $log.debug('onGetUser msg=', msg);
            self.defGet.resolve(JSON.parse(msg.body))
        }

        function getAllUsers() {
            self.defAll = $q.defer();
            self.connectionPromise.then(function (client) {
                client.send(userDst + 'all', {/*headers*/}, JSON.stringify({}))
            });
            return self.defAll.promise;
        }

        function onAllUsers(msg) {
            $log.debug('onAllUsers msg=', msg);
            self.defAll.resolve(JSON.parse(msg.body))
        }

        function subscribeToUpdates(dst,callback){
            self.connectionPromise.then(function(client){
                client.subscribe(dst,callback)
            })
        }
    }


});