(ns sqlkorma.models.examples
  (:refer-clojure :exclude [get])
  (:require [clojure.string :as string])
  (:use [clojure.java.io]))

(def examples (atom {}))

(def korma-vars ["defdb" "postgres" "mssql" "mysql" "oracle"
                 "defentity" "has-one" "has-many" "belongs-to" "transform" "prepare" "entity-fields" "table" "pk" "database"
                 "select" "select\\*" "where" "fields" "with" "order" "limit" "offset" "join" "values" "update" "insert" "delete" "update\\*" "delete\\*" "insert\\*" "set-fields" "aggregate" "group"
                 "like" "and" "or" "=" ">=" ">" "<" "<=" "not" "not=" "in" "count" "sum" "avg"
                 "exec" "sql-only" "dry-run"])

(def clojure-vars ["def" "->" "defn" "use" "require" "ns"])
(def keywords ["(:[a-zA-Z_><\\.#\\-]+?)"])
(def comments [#";;.*"])

(defn file-filter [coll]
  (filter (fn [f]
            (let [fname (.getName f)
                  ext (subs fname (inc (.lastIndexOf fname ".")))]
              (and (.isFile f)
                   (= ext "clj"))))
          coll))

(defn get-files [dir]
  (-> (file dir)
    file-seq
    rest
    file-filter))

(def standard-boundaries "[\\(\\[](%s)[\\s{}\\(\\)]")
(defn replace-words [ex-str form words & [boundaries]]
  (reduce #(let [pat (cond
                       (string? %2) (re-pattern (if boundaries 
                                                  (format boundaries %2)
                                                  (format standard-boundaries %2)))
                       :else %2)]
             (string/replace % 
                             pat
                             (fn [rep] 
                               (println rep)
                               (if (coll? rep)
                                 (let [[all rep] rep]
                                   (string/replace all rep (format form rep)))
                                 (format form rep)))))
          ex-str
          words))

(defn prepare [ex-str]
  (-> ex-str
    (replace-words "<em>%s</em>" korma-vars)
    (replace-words "<em3>%s</em3>" keywords "(%s)[\\s{}\\(\\)]")
    (replace-words "<em4>%s</em4>" comments "%s")
    (replace-words "<em2>%s</em2>" clojure-vars)))

(defn read-ex [ex-file]
  (prepare (slurp ex-file)))

(defn read-exs []
  (doseq [f (get-files "src/sqlkorma/examples/")]
    (let [fname (.getName f)
          fname (subs fname 0 (.lastIndexOf fname "."))]
      (swap! examples assoc (keyword fname) (read-ex f)))))

(defn get [k]
  (@examples k))

(defn init []
  (read-exs))

(init)
