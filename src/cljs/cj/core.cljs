(ns cj.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [re-frame.core :as re-frame]
            [cj.events]
            [cj.subs]))

(defn ui []
  (let [current-path @(re-frame/subscribe [:current-path])]
    (js/console.log current-path)
    [:div
     [:pre current-path]]))

;; start is called by init and after code reloading finishes
(defn ^:dev/after-load start []
  (js/console.log "start")
  (rdom/render [ui] (js/document.getElementById "app"))
  )

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (js/console.log "init")
  (re-frame/dispatch-sync [:initialise-db])
  (start))

;; this is called before any code is reloaded
(defn ^:dev/before-load stop []
  (js/console.log "stop"))

(comment
  (+ 1 2 3)
  (js/alert "Yahoo!!")
  :initialise-db)
