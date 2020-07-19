(ns kosha.scrapers.classified-data.karnatik-reader
  (:require [clojure.edn :as edn]))

(defn read-scraped
  [file]
  (-> file
      slurp
      edn/read-string))

(defn- not-singleton?
  [set]
  (not (= (count set) 1)))

(defn edge-list
  "Takes the scraped classified ragam names and returns an edge list of strings."
  [classified-test-data]
  (set (filter not-singleton?
               (flatten
                (for [[_ group] classified-test-data]
                  (for [str-1 group
                        str-2 group]
                    (hash-set str-1 str-2)))))))
