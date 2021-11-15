(ns clojure-jobs-luminus.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[clojure-jobs-luminus started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[clojure-jobs-luminus has shut down successfully]=-"))
   :middleware identity})
