(ns mist.esim.es
  (:require [clojurewerkz.elastisch.rest :as esr]
            [clojurewerkz.elastisch.rest.document :as esd]
            [clojurewerkz.elastisch.rest.index :as esi]
            [clojurewerkz.elastisch.query         :as q]
            [clojurewerkz.elastisch.rest.response :as esrsp]
            [mist.esim.config :as config]))

(defn create-river [x y])

(defn create-index [name mapping-name mappings]
  (let [host (format "http://%s:%d"
                     (config/lookup :host)
                     (config/lookup :port))
        conn (esr/connect host)
        mapping-types {mapping-name mappings}]
    (esi/create conn name :mappings mapping-types)))
