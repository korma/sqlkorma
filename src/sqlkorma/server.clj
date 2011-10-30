(ns sqlkorma.server
  (:require [noir.server :as server]
            [sqlkorma.models.examples :as examples]))

(server/load-views "src/sqlkorma/views/")

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8091"))]
    (server/start port {:mode mode
                        :ns 'sqlkorma})))

