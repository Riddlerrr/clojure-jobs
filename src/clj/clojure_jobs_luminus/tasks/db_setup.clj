(ns clojure-jobs-luminus.tasks.db-setup
  (:require
   [clojure-jobs-luminus.db.core :refer [*db*] :as db]
   [luminus-migrations.core :as migrations]
   [next.jdbc :as jdbc]
   [clojure-jobs-luminus.config :refer [env]]
   [mount.core :as mount]))

(defn connect []
  (mount/start
   #'clojure-jobs-luminus.config/env
   #'clojure-jobs-luminus.db.core/*db*))

(def init-db {:dbtype "postgresql" :dbname "postgres" :user "postgres" :password "postgres"})
(def init-ds (jdbc/get-datasource init-db))

(defn create-db [db-name]
  (let [db-exists-sql "select datname FROM pg_catalog.pg_database WHERE datname=?"
        create-sql (str "create database " db-name " with encoding 'utf8'")
        db-exists? (:pg_database/datname (jdbc/execute-one! init-ds [db-exists-sql db-name]))]
    (if db-exists?
      (println "Database" db-name "already exists, skipping creation")
      (do
        (println "Creating database: " db-name)
        (jdbc/execute! init-ds [create-sql])))))

(defn migrate-db []
  (migrations/migrate ["migrate"] (select-keys env [:database-url])))

(defn -main []
  (create-db "clojure_jobs_luminus_test")
  (connect)
  (migrate-db)
  (System/exit 0))

(comment
  (create-db "clojure_jobs_luminus_test"))
