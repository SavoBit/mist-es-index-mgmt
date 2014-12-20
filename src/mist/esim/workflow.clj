(ns mist.esim.workflow
  (:require [mist.esim.river :as river]
            [mist.esim.template :as template]
            [clojure.pprint :as pp]))

(defn run [env ver]
  (doseq [type ["beacon" "sensor" "location"]]
      (template/create "ios" type env ver)
      (template/blank  "ios" type env ver)
      (template/alias  "ios" type env ver)
      (river/create    "ios" type env ver))

  (doseq [type ["wifi" "location" "beacon" "sensor"]]
    (template/create "android" type env ver)
    (template/blank  "android" type env ver)
    (template/alias  "android" type env ver)
    (river/create    "android" type env ver)))




