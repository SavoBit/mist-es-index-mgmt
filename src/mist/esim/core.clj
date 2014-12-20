(ns mist.esim.core
  (:require [clojure.tools.nrepl.server :as nrepl]
            [mist.esim.workflow :as workflow]
            [mist.esim.config :as config]
            [clojure.tools.cli :refer [parse-opts]]
            [cider.nrepl :refer (cider-nrepl-handler)]))


(def cli-options
  [["-v" "--version Version" "Index Version"
    :default "v1"]
   ["-n" "--nrepl"
    :default false
    :parse-fn #(Boolean/valueOf %])
   ["-e" "--env ENV" "Environment"
    :default "dev"]
   ["-h" "--help"]])


(defn -main [& args]
  (let [{:keys [options]} (parse-opts args cli-options)]
    (println "Starting nREPL server on 8888")
    (if (:nrepl options)
      (nrepl/start-server :port (:nrepl-port options)
                          :handler cider-nrepl-handler)
      (workflow/run (:env options) (:version options)))))

