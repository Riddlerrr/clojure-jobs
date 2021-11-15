(ns clojure-jobs-luminus.util
  (:require
   [clj-time.core :as t]))

(defn time-ago [from-time]
  (let [units [{:name "second" :limit 60 :in-second 1}
               {:name "minute" :limit 3600 :in-second 60}
               {:name "hour" :limit 86400 :in-second 3600}
               {:name "day" :limit 604800 :in-second 86400}
               {:name "week" :limit 2629743 :in-second 604800}
               {:name "month" :limit 31556926 :in-second 2629743}
               {:name "year" :limit Long/MAX_VALUE :in-second 31556926}]
        diff (t/in-seconds (t/interval from-time (t/now)))]
    (print diff)
    ;; (if (< diff 5)
    ;;   "just now"
    ;;   (let [unit (first (drop-while #(or (>= diff (:limit %))
    ;;                                      (not (:limit %)))
    ;;                                 units))]
    ;;     (-> (/ diff (:in-second unit))
    ;;         Math/floor
    ;;         int
    ;;         (#(str % " " (:name unit) (when (> % 1) "s") " ago")))))
    ))

(comment
  (t/in-seconds (t/interval (t/now) (t/now)))
  (time-ago t/now)
  )
