(ns cj.system
  (:require
    [integrant.core :as ig]
    [ring.adapter.jetty :as jetty]
    [cj.handler :as handler]))

(defn system-config []
  {:system/db     {}
   :system/app    {:db (ig/ref :system/db)}
   :system/server {:app  (ig/ref :system/app)
                   :port 8000}})

(defmethod ig/init-key :system/db [_ _]
  {})

(defmethod ig/init-key :system/app [_ {:keys [db]}]
  (handler/create-app db))

(defmethod ig/init-key :system/server [_ {:keys [app port]}]
  (println "server running in port" port)
  (jetty/run-jetty app {:port port :join? false}))

(defmethod ig/halt-key! :system/server [_ server]
  (.stop server))

(defn start []
  (ig/init (system-config)))

(comment
  ;; start system
  (def system (ig/init system-config))
  ;; stop system
  (ig/halt! system))
