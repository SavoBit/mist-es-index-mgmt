(ns mist.esim.template)

(defn beacon []
  {:mappings {:mist-sdk
                {:properties {:UUID {:type "string",
                                       :index "not_analyzed"},
                               :Beacon {:properties {:Device {:type "string",
                                                              :index "not_analyzed"}}}}}}})

(defn template [prefix env version]
  (let [name (str prefix "-client" "-beacon" "-" env "-" version)]
    {:template "ios-client-beacon-staging-"
     :order 1
     :settings {:number_of_shards 2}
     :mappings {:mist-sdk
                {:properties {:UUID {:type "string",
                                       :index "not_analyzed"}}}}
     :aliases {:ios-client-beacon-staging {}}}))

(defn river [prefix env version]
  (let [name (str prefix "-client" "-beacon" "-" env "-" version)]
    {:type : "kafka",
    :kafka : {
       :zookeeper.connect : "zookeeper-000-staging.mistsys.net:2181,zookeeper-001-staging.mistsys.net:2181,zookeeper-002-staging.mistsys.net:2181",
       :zookeeper.connection.timeout.ms : 10000,
       :topic : "ios-client-beacon-staging"         
   },
   :index : {
       :index : "ios-client-beacon-staging-v1",
       :type : "mist-sdk",
       :bulk.size : 100,
       :concurrent.requests : 1,
       :kafka.message.json : true,
       :log.kafka.message : false}}))




