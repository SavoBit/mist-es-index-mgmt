(ns mist.esim.util)

(defn iname [prefix type env version]
  (str prefix "-client" "-" type  "-" env "-" version))

(defn alias* [prefix type env]
  (str prefix "-client" "-" type "-" env))

(defn fmt [op prefix type env version]
  (println (format "%s/%s-%s" (name op) (alias* prefix type env) version)))

