(ns clojure-jobs-luminus.handler-test
  (:require
   [clojure.test :refer :all]
   [ring.mock.request :refer [request]]
   [mount.core :as mount]
   [clojure-jobs-luminus.handler :refer [app]]
   [clojure-jobs-luminus.config]))

(use-fixtures
  :once
  (fn [f]
    (mount/start #'clojure-jobs-luminus.config/env
                 #'clojure-jobs-luminus.handler/app-routes)
    (f)))

(deftest test-app
  (testing "main route"
    (let [response ((app) (request :get "/"))]
      (is (= 200 (:status response)))))

  (testing "not-found route"
    (let [response ((app) (request :get "/invalid"))]
      (is (= 404 (:status response)))))

  (testing "new vacancy page"
    (let [response ((app) (request :get "/vacancies/new"))]
      (println response)
      (is (= 200 (:status response))))))
