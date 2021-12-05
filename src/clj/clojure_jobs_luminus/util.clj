(ns clojure-jobs-luminus.util
  (:require
   [java-time :as time]
   [camel-snake-kebab.extras :refer [transform-keys]]
   [camel-snake-kebab.core :refer [->kebab-case-keyword]]
   [hugsql.adapter]))

(defn time-ago [from-time]
  (let [units [{:name "сек." :limit 60 :in-second 1}
               {:name "мин." :limit 3600 :in-second 60}
               {:name "ч." :limit 86400 :in-second 3600}
               {:name "д." :limit 604800 :in-second 86400}
               {:name "нед." :limit 2629743 :in-second 604800}
               {:name "мес." :limit 31556926 :in-second 2629743}
               {:name "год" :limit Long/MAX_VALUE :in-second 31556926}]
        diff (time/time-between from-time (time/local-date-time) :seconds)]

    (if (< diff 5)
      "только что"
      (let [unit (first (drop-while #(or (>= diff (:limit %))
                                         (not (:limit %)))
                                    units))]
        (-> (/ diff (:in-second unit))
            Math/floor
            int
            (#(str % " " (:name unit) " назад")))))))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn result-one-snake->kebab [this result options]
  (->> (hugsql.adapter/result-one this result options)
       (transform-keys ->kebab-case-keyword)))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn result-many-snake->kebab
  [this result options]
  (->> (hugsql.adapter/result-many this result options)
       (map #(transform-keys ->kebab-case-keyword %))))

(comment
  (time-ago (time/local-date-time 2021 10 1 0)))
