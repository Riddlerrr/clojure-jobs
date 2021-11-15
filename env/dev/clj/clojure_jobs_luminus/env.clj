(ns clojure-jobs-luminus.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [clojure-jobs-luminus.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[clojure-jobs-luminus started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[clojure-jobs-luminus has shut down successfully]=-"))
   :middleware wrap-dev})
