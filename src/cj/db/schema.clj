(ns cj.db.schema
  (:require
    [next.jdbc :as jdbc]))

(def schema "empty"
  (slurp "src/cj/db/schema.sql"))

(defn load-schema
  "Called at application startup. Attempts to create the
  database table and populate it. Takes no action if the
  database table already exists."
  [db]
  (try
    (jdbc/execute-one! db [schema])
    (println "DB schema was updated")
 
    (catch Exception e
      (println "Exception:" (ex-message e))
      (println "Something went wrong during update DB schema."))))
