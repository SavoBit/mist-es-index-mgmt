(ns mist.esim.template
  (:use mist.esim.util)
  (:refer-clojure :exclude [alias])
  (:require [mist.esim.es :as es]))

(def mappings 
  {"beacon" {:properties
               {:UUID {:type "string",
                       :index "not_analyzed"},
                :Beacon {:properties {:Device {:type "string",
                                               :index "not_analyzed"}}}}}

   "sensor"  {:properties {:UUID {:type "string"
                                 :index "not_analyzed"}}}

   "location" {:properties {:UUID {:type "string"
                                 :index "not_analyzed"}}}})

(def settings {:number_of_shards 2})

;; Expored fns

(defn create [prefix type env version]
  (fmt "template/create" prefix type env version)
  (let [name (iname prefix type env version)
        alias (alias* prefix type env)
        mappings (get mappings type)]
    
    (es/create-index name :mist-sdk mappings)))

(defn blank [prefix type env version]
  (fmt "template/blank" prefix type env version))

(defn alias [prefix type env version]
  (fmt "template/alias" prefix type env version))

  
