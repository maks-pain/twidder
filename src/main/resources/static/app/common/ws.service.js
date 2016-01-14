/**
 * Created by maks-pain on 12/16/15
 */

define(['./common.module'], function () {
    "use strict";

    console.log('[WS.SERVICE] Load',new Date().toISOString());

    angular.module('twidder.common')
        .factory('wsService', wsService);

    wsService.$inject = ['$log', '$q']
    function wsService($log, $q) {
        $log = $log.getInstance('wsService');
        $log.info("[INIT]");

        var self = this;
        self.username = 'unknown';
        self.connected = false;
        self.stompClient;

        var deferredConnection = $q.defer();
        var registeredSubscriptions = [];

        return {
            getUsername: getUsername,
            getConnection: getConnection,
            wsSubscribe: wsSubscribe,
            sendToDestination: sendToDestination,
            disconnect: disconnect
        }

        function connect() {
            $log.debug('Connecting...');
            var socket = new SockJS('http://localhost:8090/twidder')
            self.stompClient = Stomp.over(socket);
            self.stompClient.connect('', '',
                function (msg) {
                    self.connected = true;
                    if (msg.headers && msg.headers['user-name']) {
                        self.username = msg.headers['user-name'];
                    }
                    $log.info('Connection:', msg);
                    $log.info('\tusername:', self.username);

                    deferredConnection.resolve(self.stompClient)

                }, function (msg) {
                    self.connected = false;
                    $log.error('Connection failed!', msg);

                    deferredConnection.reject(msg)
                }
            );
            $log.debug('stompClient:', self.stompClient)
        }

        function getConnection() {
            if (!self.stompClient || !self.stompClient.connected) {
                $log.debug('Establishing connection');
                connect()
            } else {
                $log.debug('Already connected!')
            }
            return deferredConnection.promise
        }

        function wsSubscribe(dst, listener) {
            if (self.stompClient && self.stompClient.connected) {
                var sub = self.stompClient.subscribe(dst, listener);
                registeredSubscriptions.push(sub);
                $log.info('Adding new subscription ', sub)
                return sub
            }
            $log.error('Subscription failed. StompClient is disconnected or undefined', self.stompClient)
        }

        function sendToDestination(dst, obj) {
            self.stompClient.send(dst, {/*HEADERS*/}, JSON.stringify(obj))
        }

        function disconnect() {
            if (self.stompClient && self.stompClient.connected) {
                self.stompClient.disconnect();
            }
            self.connected = false;
            $log.info('Disconnected')
        }

        function getUsername() {
            return self.username
        }
    }

});
