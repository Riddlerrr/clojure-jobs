(ns clojure-jobs-luminus.db.core-test
  (:require
   [clojure-jobs-luminus.db.core :refer [*db*] :as db]
   [java-time.pre-java8]
   [luminus-migrations.core :as migrations]
   [clojure.test :refer :all]
   [next.jdbc :as jdbc]
   [clojure-jobs-luminus.config :refer [env]]
   [mount.core :as mount]
   [java-time :as time]))

(use-fixtures
  :once
  (fn [f]
    (mount/start
     #'clojure-jobs-luminus.config/env
     #'clojure-jobs-luminus.db.core/*db*)
    (migrations/migrate ["reset"] (select-keys env [:database-url]))
    (f)))

(deftest test-vacancies
  (let [expired-at (time/plus (time/local-date-time) (time/days 14))]
    (jdbc/with-transaction [t-conn *db*]
      (is
       (= 1
          (db/create-vacancy!
           t-conn
           {:title "Senior Clojure Developer"
            :description "В нашу супер компанию требуются гребцы"
            :location "Москва"
            :salary-min 5000
            :salary-max 7000
            :remote-available true
            :expired-at expired-at}
           {})))

      (testing "get inserted id"
        (let [vacancy (db/get-vacancy t-conn {:id 1} {})]
          (is
           (= {:title "Senior Clojure Developer"
               :description "В нашу супер компанию требуются гребцы"
               :location "Москва"
               :salary-min 5000
               :salary-max 7000
               :remote-available true
               :expired-at expired-at}
              (select-keys vacancy [:title :description :location :salary-min :salary-max :remote-available :expired-at]))))))))
