(ns tododo.events
    (:require [re-frame.core :as f]
              [tododo.db :as db]))

(f/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(f/reg-event-db
  :test-click
  (fn db [_ _]
    (do (println "FIRE!!")
        db/default-db)))

(f/reg-event-db
  :click/title
  (fn [{:keys [expanded] :as db} [_ _]]
    (assoc db :expanded (not expanded))))

(f/reg-event-db
  :board/add-column!
  (fn db [_ _]
    (let [columns @(f/subscribe [:board/columns])
          new-columns (conj columns {:id (random-uuid)
                                     :title "Edit Me"
                                     :editing true})]
      (assoc db/default-db :columns new-columns))))

(f/reg-event-db
  :column/add-new-card!
  (fn db [_ column-index]
    (let [columns (f/subscribe [:board/columns])
          column (get columns column-index)
          card-list (conj (:cards column) {:id (random-uuid)
                                           :title "Edit this card!"
                                           :editing true})
          updated-column-with-new-card (assoc column :cards card-list)]
     (update-in db/default-db [:columns column-index] (fn [] updated-column-with-new-card)))))
