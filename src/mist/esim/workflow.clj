(ns mist.esim.workflow
  (:require [mist.esim.river :as river]
            [mist.esim.template :as template]))

(defn run [env ver]
  (doseq [x [:beacon :sensor :location]]
    (-> x
        (template/create :ios env ver)
        (template/blank  :ios env ver)
        (template/alias  :ios env ver)
        (river/create    :ios env ver)))

    (doseq [y [:wifi :location :beacon :sensor]]
      (-> y
        (template/create :android env ver)
        (template/blank  :android env ver)
        (template/alias  :android env ver)
        (river/create    :android env ver))))




