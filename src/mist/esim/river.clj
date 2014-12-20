(ns mist.esim.river
  (:use mist.esim.util)
  (:refer-clojure :exclude [alias])
  (:require [mist.esim.es :as es]))

(defn river [name topic]
  {:type "kafka",
     :kafka {
             :zookeeper.connect "zookeeper-000-staging.mistsys.net:2181,zookeeper-001-staging.mistsys.net:2181,zookeeper-002-staging.mistsys.net:2181"
             :zookeeper.connection.timeout.ms 10000
             :topic topic
             }
     :index {
             :index name
             :type "mist-sdk"
             :bulk.size 100
             :concurrent.requests 1
             :kafka.message.json true
             :log.kafka.message false}})


(defn create [prefix type env version]
  (fmt "river/create" prefix type env version)
  (let [name (iname prefix type env version)
        alias (alias* prefix type env)
        mappings (river name alias)]
    (es/create-river name :mist-sdk)))

(defn blank [prefix type env version]
  (fmt "river/blank" prefix type env version)
  )

(defn alias [prefix type env version]
  (fmt "river/alias" prefix type env version))

