(ns mist.esim.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.tools.nrepl.server :as nrepl]
            [mist.esim.workflow :as workflow]
            [mist.esim.config :as config]
            [cider.nrepl :refer (cider-nrepl-handler)]))

(def cli-options
  [["-v" "--version Version" "Index Version"
    :default "v1"]
   ["-n" "--nrepl"
    :default false
    :parse-fn #(Boolean/valueOf %)]
   ["-e" "--env ENV" "Environment"
    :default "dev"]
   ["-h" "--help"]])

(defn -main [& args]
  (let [{:keys [options]} (parse-opts args cli-options)]
    (config/init (str "config.edn." (:env options)))
    (if (:nrepl options)
      (do
        (println "Starting nREPL server on 8888")
        (nrepl/start-server :port 8888
                            :handler cider-nrepl-handler))
      (do
        (println "Running workflow")
        (workflow/run (:env options) (:version options))
        (System/exit 0)))))
