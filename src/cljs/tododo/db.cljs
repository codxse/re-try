(ns tododo.db)

(def default-db
  {:name "re-frame"
   :title "To DODO BIDAM"
   :leorm "Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n      Donec mattis pretium massa. Aliquam erat volutpat. Nulla facilisi.\n      Donec vulputate interdum sollicitudin. Nunc lacinia auctor quam sed pellentesque.\n      Aliquam dui mauris, mattis quis lacus id, pellentesque lobortis odio."
   :expanded true
   :columns [{:id (random-uuid)
              :title "Clojurescript"
              :cards [{:id (random-uuid)
                       :title "David Nolen"
                       :editing false}
                      {:id (random-uuid)
                       :title "Please update om.next!"
                       :editing true}]
              :editing true}
             {:id (random-uuid)
              :title "Late"
              :cards [{:id (random-uuid)
                       :title "Work out"
                       :editing false}
                      {:id (random-uuid)
                       :title "Go to school"
                       :editing false}]
              :editing false}]})
