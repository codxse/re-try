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
  (fn [db _]
    (let [dbb (f/subscribe [:db])
          columns (f/subscribe [:board/columns])
          new-column {:title "EDIT COLUMN"
                      :id (random-uuid)
                      :editing true
                      :cards []}
          new-columns (conj @columns new-column)]
      (assoc @dbb :columns new-columns))))

(f/reg-event-db
  :column/add-new-card!
  (fn [db [_ column-index]]
    (let [dbb (f/subscribe [:db])]
     (update-in @dbb [:columns column-index :cards] conj {:id (random-uuid)
                                                          :title "Please edit this card!"
                                                          :editing true}))))

(f/reg-event-db
  :column/update-title!
  (fn [db [_ idx-column value]]
    (let [dbb (f/subscribe [:db])
          this-column (get-in @dbb [:columns idx-column])
          updated-column (update this-column :title (fn [] value))]
      (update-in @dbb [:columns idx-column] (fn [] updated-column)))))

(f/reg-event-db
  :column/set-editing!
  (fn [db [_ idx-column value]]
    (let [dbb (f/subscribe [:db])
          this-column (get-in @dbb [:columns idx-column])
          updated-column (update this-column :editing (fn [] value))]
      (println "THIS COLUMN" this-column)
      (update-in @dbb [:columns idx-column] (fn [] updated-column)))))

(f/reg-event-db
  :card/update-title!
  (fn [db [_ idx-column idx-card value]]
    (let [dbb (f/subscribe [:db])]
          ;new-db (update-in @dbb [:columns idx-column :cards idx-card] :title (fn [] value))
          ;edit (get-in @dbb [:columns idx-column :cards idx-card])]
      ;(println "THIS CARD" edit)
      (update-in @dbb [:columns idx-column :cards idx-card :title] (fn [] value)))))

(f/reg-event-db
  :card/set-editing!
  (fn [db [_ idx-column idx-card value]]
    (let [dbb (f/subscribe [:db])]
      (update-in @dbb [:columns idx-column :cards idx-card :editing] (fn [] value)))))


