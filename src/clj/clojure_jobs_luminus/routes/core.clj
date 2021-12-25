(ns clojure-jobs-luminus.routes.core
  (:require
   [clojure-jobs-luminus.layout :as layout]
   [clojure-jobs-luminus.db.core :as db]
   [clojure-jobs-luminus.middleware :as middleware]
   [clojure-jobs-luminus.routes.vacancies :refer [vacancies-routes]]
   [clojure.java.io :as io]
   [ring.util.response]))

(defn home-page [request]
  (layout/render request "home.html" {:vacancies (db/get-last-vacancies {:limit 10})}))

(defn guide-page [request]
  (layout/render request "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page [request]
  (layout/render request "about.html"))

(defn main-routes []
  [["/" {:get home-page}]
   ["/about" {:get about-page}]
   ["/dev/guide" {:get guide-page}]])

(defn all-routes []
  (conj 
   []
   [""
    {:middleware [middleware/wrap-csrf
                  middleware/wrap-formats]}]
   (main-routes)
   (vacancies-routes)))
