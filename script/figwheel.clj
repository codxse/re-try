(require '[figwheel-sidecar.system :as sys]
         '[com.stuartsierra.component :as component])

(def figwheel (atom nil))

;(def system
;  (component/system-map
;    :figwheel-system (sys/figwheel-system (sys/fetch-config))))

(defn start-figwheel
  "Start Figwheel on the given builds, or defaults to build-ids in `figwheel-config`."
  ([]
   (let [figwheel-config (sys/fetch-config)
         props (System/getProperties)
         all-builds (->> figwheel-config :data :all-builds (mapv :id))]
     (start-figwheel (keys (select-keys props all-builds)))))
  ([build-ids]
   (let [figwheel-config (sys/fetch-config)
         default-build-ids (-> figwheel-config :data :build-ids)
         build-ids (if (empty? build-ids) default-build-ids build-ids)
         preferred-config (assoc-in figwheel-config [:data :build-ids] build-ids)]
     (reset! figwheel (component/system-map
                        :css-watcher (sys/css-watcher {:watch-paths ["resources/public/css"]})
                        :figwheel-system (sys/figwheel-system preferred-config)))
     (println "STARTING FIGWHEEL ON BUILDS: " build-ids)
     (swap! figwheel component/start)
     (sys/cljs-repl (:figwheel-system @figwheel)))))

(start-figwheel)