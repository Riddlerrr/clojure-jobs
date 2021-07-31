(ns cj.system
  (:require
    [integrant.core :as ig]
    [next.jdbc :as jdbc]
    [ring.adapter.jetty :as jetty]
    [cj.handler :as handler]
    [cj.db.schema :refer [load-schema]]))

(defn system-config []
  {:system/db     {:dbtype "sqlite" :dbname "db/clojure_jobs_development.db"}
   :system/app    {:db (ig/ref :system/db)}
   :system/server {:app  (ig/ref :system/app)
                   :port 8000}})

(defmethod ig/init-key :system/db [_ db-spec]
  (let [conn (jdbc/get-datasource db-spec)]
    (load-schema conn)
    conn))

(defmethod ig/init-key :system/app [_ {:keys [db]}]
  (handler/create-app db))

(defmethod ig/init-key :system/server [_ {:keys [app port]}]
  (println "server running in port" port)
  (jetty/run-jetty app {:port port :join? false}))

(defmethod ig/halt-key! :system/server [_ server]
  (.stop server))

(defn start []
  (ig/init (system-config)))
