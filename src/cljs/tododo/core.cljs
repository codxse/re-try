(ns tododo.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [tododo.events]
              [tododo.subs]
              [tododo.views :as views]
              [tododo.config :as config]
              [re-frisk.core :refer [enable-re-frisk!]]))


(defn dev-setup []
  (when config/debug?
    (enable-re-frisk!)
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
