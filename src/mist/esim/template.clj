(ns mist.esim.template
  (:use mist.esim.util)
  (:refer-clojure :exclude [alias])
  (:require [mist.esim.es :as es]))

(defn- beacon []
  {:mappings {:mist-sdk
              {:properties
               {:UUID {:type "string",
                       :index "not_analyzed"},
                :Beacon {:properties {:Device {:type "string",
                                               :index "not_analyzed"}}}}}}})

(defn- template [name alias]
  {:template name
     :order 1
     :settings {:number_of_shards 2}
     :mappings {:mist-sdk
                {:properties {:UUID {:type "string"
                                     :index "not_analyzed"}}}}
   :aliases {alias* {}}})

;; Expored fns

(defn create [prefix type env version]
  (fmt "template/create" prefix type env version)
  (let [name (iname prefix type env version)
        alias (alias* prefix type env)
        data (template name alias)]
    (es/create name data)))

(defn blank [prefix type env version]
  (fmt "template/blank" prefix type env version)
  )

(defn alias [prefix type env version]
  (fmt "template/alias" prefix type env version))
