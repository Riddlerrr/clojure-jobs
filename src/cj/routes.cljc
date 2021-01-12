(ns cj.routes
  (:require
    [reitit.ring :as rring]
    [reitit.ring.middleware.parameters :as parameters]
    [reitit.ring.middleware.exception :as exception]
    [reitit.dev.pretty :as pretty]))


(def routes
  (rring/ring-handler
    (rring/router
      [["/"
        {:get {:summary "main page"
               :handler (fn [_]
                          {:status 200 :body "Hello"})}}]]

      {:exception pretty/exception
       :data {:middleware [;; query-params & form-params
                           parameters/parameters-middleware
                           ;; exception handling
                           exception/exception-middleware]}})))


