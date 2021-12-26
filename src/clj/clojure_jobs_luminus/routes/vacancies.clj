(ns clojure-jobs-luminus.routes.vacancies
  (:require
   [clojure-jobs-luminus.layout :as layout]
   [clojure-jobs-luminus.db.core :as db]
   [struct.core :as st]
   [ring.util.http-response :as response]
   [java-time :as time]))

(def required
  [st/required :message "обязательное поле"])

(def integer
  [st/integer-str :message "должно быть числом"])

(def vacancy-schema
  {:title [required st/string]
   :description [required st/string {:validate #(> (count %) 9) :message "слишком короткое описание вакансии"}]
   :location [required st/string]
   :salary-min [integer]
   :salary-max [integer]
  ;;  :remote-available [st/boolean]
  ;;  :moderated [st/boolean]
   })

(defn validate-vacancy [vacancy-params]
  (st/validate vacancy-params vacancy-schema))

(defn new-page [{:keys [flash] :as request}]
  (layout/render
   request
   "vacancies/new.html"
   flash))

(defn save-vacancy! [{:keys [params]}]
  (let [[errors vacancy-params] (validate-vacancy (:vacancy params))]
    (if errors
      (-> (response/found "/vacancies/new")
          (assoc :flash {:vacancy vacancy-params :errors errors}))
      (do
        (db/create-vacancy!
         (assoc vacancy-params
                :expired-at (time/plus (time/local-date-time) (time/days 14))
                :remote-available true))
        (response/found "/")))))

(defn vacancies-routes []
  [["/vacancies/new" {:get new-page}]
   ["/vacancies" {:post save-vacancy!}]])
