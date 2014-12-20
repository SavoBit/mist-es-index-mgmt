(ns mist.esim.template
  (:require [mist.esim.es :as es]))

(defn iname [prefix env version]
  (str prefix "-client" "-beacon" "-" env "-" version))

(defn beacon []
  {:mappings {:mist-sdk
                {:properties: {:UUID: {:type "string",
                                       :index "not_analyzed"},
                               :Beacon {:properties {:Device {:type "string",
                                                              :index "not_analyzed"}}}}}}})

(defn template [prefix env version]
  (let [name ]
    {:template "ios-client-beacon-staging-"
     :order 1
     :settings {:number_of_shards 2}
     :mappings {:mist-sdk
                {:properties: {:UUID: {:type "string",
                                       :index "not_analyzed"}}}}
     :aliases {:ios-client-beacon-staging: {}}}))

(defn create [prefix env version]
  (let [name (iname prefix env version)
        data (template prefix env version)]
    (es/create name data)))

(defn delete [name]
  (es/delete name))

(defn get [name]
  (es/get name "/_mapping"))


