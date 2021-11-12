(ns cj.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame.core/reg-sub
 :current-path
 (fn  [db _]
   (:current-path db)))
