(ns clojure-jobs-luminus.util-test
  (:require
   [clojure.test :refer :all]
   [clojure-jobs-luminus.util :refer [time-ago]]
   [java-time :as time]))

(deftest time-ago-test
  (testing "right now"
    (is
     (=
      "только что"
      (time-ago (time/local-date-time))))
    (is
     (=
      "5 сек. назад"
      (time-ago (time/minus (time/local-date-time) (time/seconds 5)))))
    (is
     (=
      "59 сек. назад"
      (time-ago (time/minus (time/local-date-time) (time/seconds 59)))))
    (is
     (=
      "1 мин. назад"
      (time-ago (time/minus (time/local-date-time) (time/minutes 1)))))
    (is
     (=
      "59 мин. назад"
      (time-ago (time/minus (time/local-date-time) (time/minutes 59)))))
    (is
     (=
      "1 ч. назад"
      (time-ago (time/minus (time/local-date-time) (time/hours 1)))))
    (is
     (=
      "23 ч. назад"
      (time-ago (time/minus (time/local-date-time) (time/hours 23)))))
    (is
     (=
      "23 ч. назад"
      (time-ago (time/minus (time/local-date-time) (time/hours 23)))))
    (is
     (=
      "1 д. назад"
      (time-ago (time/minus (time/local-date-time) (time/days 1)))))
    (is
     (=
      "6 д. назад"
      (time-ago (time/minus (time/local-date-time) (time/days 6)))))
    (is
     (=
      "1 нед. назад"
      (time-ago (time/minus (time/local-date-time) (time/weeks 1)))))
    (is
     (=
      "4 нед. назад"
      (time-ago (time/minus (time/local-date-time) (time/weeks 4)))))
    (is
     (=
      "1 мес. назад"
      (time-ago (time/minus (time/local-date-time) (time/months 1)))))
    (is
     (=
      "11 мес. назад"
      (time-ago (time/minus (time/local-date-time) (time/months 11)))))
    (is
     (=
      "1 год назад"
      (time-ago (time/minus (time/local-date-time) (time/months 13)))))
    ))
