(ns clojure-jobs-luminus.routes.home
  (:require
   [clojure-jobs-luminus.layout :as layout]
   [clojure-jobs-luminus.db.core :as db]
   [clojure.java.io :as io]
   [clojure-jobs-luminus.middleware :as middleware]
   [ring.util.response]))

(defn home-page [request]
  (layout/render request "home.html" {:vacancies (db/get-last-vacancies {:limit 10})}))

(defn guide-page [request]
  (layout/render request "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page [request]
  (layout/render request "about.html"))

(defn home-routes []
  [ "" 
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/about" {:get about-page}]
   ["/dev/guide" {:get guide-page}]])

