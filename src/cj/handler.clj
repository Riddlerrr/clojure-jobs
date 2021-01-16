(ns cj.handler
  (:require
    [reitit.ring :as ring]
    [reitit.swagger :as swagger]
    [reitit.swagger-ui :as swagger-ui]
    [reitit.ring.middleware.muuntaja :as muuntaja]
    [reitit.ring.middleware.exception :as exception]
    [reitit.ring.middleware.multipart :as multipart]
    [reitit.ring.middleware.parameters :as parameters]
    [reitit.coercion.spec]
    [reitit.ring.coercion :as coercion]
    [muuntaja.core :as m]
    [reitit.dev.pretty :as pretty]
    [cj.middleware :as mw]))

(defn ok [{:keys [db]}]
  (println "db:" db)
  {:status 200 :body "Ok"})

(def routes
  [["/swagger.json"
    {:get {:no-doc true
           :swagger {:info {:title "Clojure Jobs API"
                            :description "Clojure Jobs API"}}
           :handler (swagger/create-swagger-handler)}}]
   ["/ping"
    {:get {:summary "Just to check the API works fine"
           :handler ok}}]])

(defn create-app [db]
  (ring/ring-handler
    (ring/router routes
                 {:exception pretty/exception
                  :data {:db db
                         :coercion reitit.coercion.spec/coercion
                         :muuntaja m/instance
                         :middleware [swagger/swagger-feature
                                      ;; query-params & form-params
                                      parameters/parameters-middleware
                                      ;; content-negotiation
                                      muuntaja/format-negotiate-middleware
                                      ;; encoding response body
                                      muuntaja/format-response-middleware
                                      ;; exception handling
                                      exception/exception-middleware
                                      ;; decoding request body
                                      muuntaja/format-request-middleware
                                      ;; coercing response bodies
                                      coercion/coerce-response-middleware
                                      ;; coercing request parameters
                                      coercion/coerce-request-middleware
                                      ;; multipart
                                      multipart/multipart-middleware

                                      mw/db]}})
    (ring/routes
      (swagger-ui/create-swagger-ui-handler
        {:path "/"})
      (ring/create-default-handler))))
