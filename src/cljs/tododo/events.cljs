(ns tododo.events
    (:require [re-frame.core :as f]
              [tododo.db :as db]))

(f/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(f/reg-event-db
  :click/title
  (fn [{:keys [expanded] :as db} [_ _]]
    (assoc db :expanded (not expanded))))