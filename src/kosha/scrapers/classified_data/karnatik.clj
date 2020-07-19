(ns kosha.scrapers.classified-data.karnatik
  (:require [clojure.edn :as edn]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [kosha.scrapers.util :as util]
            [medley.core :as m])
  (:import org.jsoup.Jsoup
           [org.jsoup.nodes Document Element]
           org.jsoup.select.Elements))

(def output-filename "output/classified-test-data.edn")
(def test-data-output-filename "output/test-data.edn")

(defn karnatik-url
  [letter]
  (str "http://www.karnatik.com/ragas" letter ".shtml"))

(def karnatik-chars
  ["a" "b" "c" "d" "e" "g" "h" "i" "j" "k" "l" "m" "n" "o" "p" "r" "s" "t" "u" "v" "w" "y"])

(defn ragam-name
  [jsoup-anchor-tag]
  (.attr jsoup-anchor-tag "name"))

(defn has-children?
  [jsoup-anchor-tag]
  (not (empty? (.children jsoup-anchor-tag))))

(def annotate-children
  (juxt ragam-name has-children?))

(defn ragam-anchor-tags
  "Call the url to scrape, and obtain the data of interest, i.e. ragam names."
  [url]
  (-> (util/url->jsoup url)
      (.select "tbody")
      (.select "td[width=420]")
      (.select "font")
      (.select "font")
      (.select "blockquote > a[name]")))

(defn group-ragams [annotated-ragam-names]
  (reduce (fn [acc [ragam-name has-children?]]
            (let [created-groups (vec (butlast acc))
                  current-ragam-group (conj (last acc) ragam-name)]
              (if has-children?
                (conj created-groups current-ragam-group #{})
                (conj created-groups current-ragam-group))))
          [#{}]
          annotated-ragam-names))

(defn ragam-name-sets
  [letter]
  (->> (ragam-anchor-tags (karnatik-url letter))
       (mapv annotate-children)
       (group-ragams)))

(defn key-by-first [coll]
  (->> coll
       (group-by first)
       (m/map-vals first)))

(defn get-all-classified-ragams []
  (->> karnatik-chars
       (mapcat ragam-name-sets)
       key-by-first))

(defn get-all-ragam-names []
  (->>  karnatik-chars
        (map karnatik-url)
        (mapcat ragam-anchor-tags)
        (map ragam-name)))


(defn write-to-file
  []
  (spit output-filename (-> (get-all-classified-ragams) pp/pprint with-out-str))
  (println "Wrote classified test data.")
  (spit test-data-output-filename (-> (get-all-ragam-names) pp/pprint with-out-str)))
