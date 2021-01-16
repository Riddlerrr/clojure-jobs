(ns cj.db.schema
  (:require
    [next.jdbc :as jdbc]))
    ;[next.jdbc.sql :as sql]))

(def schema "empty"
  (slurp "src/cj/db/schema.sql"))

(defn load-schema
  "Called at application startup. Attempts to create the
  database table and populate it. Takes no action if the
  database table already exists."
  [db]
  (try
    (jdbc/execute-one! db [schema])
    (println "Created database and addressbook table!")
    ;; if table creation was successful, it didn't exist before
    ;; so populate it...
    (try
      ;(doseq [row initial-user-data]
      ;  (sql/insert! db :addressbook row))
      (println "Populated database with initial data!")
      (catch Exception e
        (println "Exception:" (ex-message e))
        (println "Unable to populate the initial data -- proceed with caution!")))

    (catch Exception e
      (println "Exception:" (ex-message e))
      (println "Looks like the database is already setup?"))))
