(ns mist.esim.core
  (:require [clojurewerkz.elastisch.rest :as esr]
            [clojure.tools.nrepl.server :as nrepl]
            [mist.esim.config :as config]
            [cider.nrepl :refer (cider-nrepl-handler)]))

(defn -main [& args]
  (println "Starting nREPL server on 8888")
  (nrepl/start-server :port 8888 :handler cider-nrepl-handler))

