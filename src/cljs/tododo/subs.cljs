(ns tododo.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as f]))

(f/reg-sub
 :name
 (fn [db]
   (:name db)))

(f/reg-sub
  :title
  (fn [db]
    (:title db)))

(f/reg-sub
  :leorm
  (fn [db] (:leorm db)))

(f/reg-sub
  :expanded
  (fn [db] (:expanded db)))

(f/reg-sub
  :board/columns
  (fn [db] (:columns db)))

(f/reg-sub
  :db
  (fn [db _] db))

(f/reg-sub
  :field/default-value
  (fn [db _] (:default-value db)))