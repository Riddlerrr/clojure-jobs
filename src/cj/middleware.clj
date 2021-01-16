(ns cj.middleware)

(def db
  {:name    ::db
   :compile (fn [router-data _]
              (fn [handler]
                (fn [request]
                  (handler (assoc request :db (:db router-data))))))})
