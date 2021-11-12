(ns cj.events
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-event-db
 :initialise-db
 (fn [db _]
   (assoc db :current-path "/")))
