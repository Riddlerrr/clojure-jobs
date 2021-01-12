(ns cj.server
  (:require
    [integrant.core :as ig]
    [aero.core :refer [read-config]]
    [ring.adapter.jetty :refer  [run-jetty]]
    [cj.routes :refer [routes]]))

;(def app (constantly {:status 200 :body "Hello"}))
(def app routes)

(defmethod ig/init-key :server
  [_ options]
  (run-jetty app options))

(defmethod ig/halt-key! :server
  [_ server]
  (.stop server))

(def system-config
  (:system-config (read-config (clojure.java.io/resource "cj/config/config.edn"))))

(def _sys (ig/init system-config))

(defn start [& args]
  _sys
  (println "Server started"))




