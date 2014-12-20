(ns mist.esim.core
  (:require [clojurewerkz.elastisch.rest :as esr]
            [clojurewerkz.elastisch.rest.document :as esd]
            [clojurewerkz.elastisch.query         :as q]
            [clojurewerkz.elastisch.rest.response :as esrsp]
            [clojure.pprint :as pp]
            [clojure.tools.cli :refer [parse-opts]]
            [clojure.tools.nrepl.server :as nrepl]
            [mist.esim.config :as config]
            [cider.nrepl :refer (cider-nrepl-handler)]))

(def cli-options
  [["-n" "--nrepl-port PORT" "nREPL Port Number"
    :default 8888
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]
   ["-e" "--env ENV" "Environment"
    :default "dev"]
   ["-h" "--help"]])

(defn -main [& args]
  (let [{:keys [options]} (parse-opts args cli-options)]
    (config/init (str "config.edn." (:env options)))
    (println "Starting nREPL server on " (:nrepl-port options))
    (nrepl/start-server :port (:nrepl-port options) :handler cider-nrepl-handler)))

