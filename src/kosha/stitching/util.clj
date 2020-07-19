(ns kosha.stitching.util
  (:require [loom.graph :as loom]))

(defn graph
  "Convert a set of edges retrieved from Postgres into a loom graph."
  [edge-set]
  (apply loom/graph (map vec edge-set)))
