(ns tododo.views
    (:require [re-frame.core :as f]
              [reagent.core :as r]
              [cljsjs.material-ui]
              [cljs-react-material-ui.core :refer [get-mui-theme color]]
              [cljs-react-material-ui.reagent :as ui]
              [cljs-react-material-ui.icons :as ic]))

(defn cardr []
  (let [leorm (f/subscribe [:leorm])
        exp (f/subscribe [:expanded])]
    (fn []
      [ui/card
       {:expanded @exp
        :initialy-expanded true}
       [ui/card-header
        {:title "Some title"
         :subtitle "subtitle"
         :act-as-expander true
         :show-expandable-button true
         :on-click #(f/dispatch-sync [:click/title])
         :on-touch-tap #(f/dispatch-sync [:click/title])}]
       [ui/card-actions
        [ui/flat-button {:label "Action 1"}]
        [ui/flat-button {:label "Action 2"}]]
       [ui/card-text {:expandable true}
        @leorm]])))


(defn main-panel []
  (let [name (f/subscribe [:name])
        title (f/subscribe [:title])]
    (fn []
      [ui/mui-theme-provider {:mui-theme (get-mui-theme {:palette {:text-color (color :green600)}})}
       [:div
        [ui/app-bar {:title @title
                     :icon-element-right
                            (r/as-element [ui/icon-button
                                           (ic/action-account-balance-wallet)])}]
        [cardr]]])))
