(defproject esim "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clojurewerkz/elastisch "2.1.0"]

                 [org.clojure/tools.cli    "0.3.1"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.clojure/tools.nrepl  "0.2.5"]
                 [org.clojure/tools.cli   "0.3.1"]
                 [cider/cider-nrepl        "0.8.1"]]

  :main mist.esim.core)
