(ns kosha.stitching.scoring
  (:require [clojure.set :as s]
            [kosha.stitching.util :as util]
            [loom.alg :as graph-alg]))

(defn false-positives
  "Edges/matches that shouldn't exist, but do."
  [correct-edges edges]
  (s/difference edges correct-edges))

(defn false-negatives
  "Edges/matches that should exist, but don't."
  [correct-edges edges]
  (s/difference correct-edges edges))

(defn true-positives
  "Edges/matches that should exist, and do. I.e. correct matches."
  [correct-edges edges]
  (s/intersection correct-edges edges))

(defn fully-identified-sets
  "List all the groups of similar strings that were correctly and fully identified."
  [correct-graph graph]
  (let [correct-groups (graph-alg/connected-components correct-graph)
        groups (graph-alg/connected-components graph)]
    (s/intersection (set (map set correct-groups))
                    (set (map set groups)))))

(defn wrongly-identified-sets
  "List all the wrongly or partially identified groups of similar strings."
  [correct-graph graph]
  (let [correct-groups (graph-alg/connected-components correct-graph)
        groups (graph-alg/connected-components graph)]
    (s/difference (set (map set groups))
                  (set (map set correct-groups)))))

(defn unidentified-sets
  "List all the groups of similar ragams that should've been identified, but were not or were only partially identified."
  [correct-graph graph]
  (let [correct-groups (graph-alg/connected-components correct-graph)
        groups (graph-alg/connected-components graph)]
    (s/difference (set (map set correct-groups))
                  (set (map set groups)))))

(defn partially-correct-subsets
  "How many wrongly identified sets are subsets of unidentified ones?"
  [correct-graph graph]
  (let [wrongly-identified (wrongly-identified-sets correct-graph graph)
        unidentified      (unidentified-sets correct-graph graph)]
    (filter #(not (nil? %))
            (for [sub wrongly-identified
                  super unidentified]
              (when (s/subset? sub super)
                sub)))))

(defn partially-correct-supersets
  "How many wrongly identified sets are supersets of unidentified ones?"
  [correct-graph graph]
  (let [wrongly-identified (wrongly-identified-sets correct-graph graph)
        unidentified      (unidentified-sets correct-graph graph)]
    (filter #(not (nil? %))
            (for [super wrongly-identified
                  sub unidentified]
              (when (s/superset? super sub)
                super)))))

(defn compare-edge-lists-by-sets
  [correct-edges result-edges]
  (let [correct-graph (util/graph correct-edges)
        result-graph (util/graph result-edges)]
    {:fully-identified (count (fully-identified-sets correct-graph result-graph))
     :wrongly-identified (count (wrongly-identified-sets correct-graph result-graph))
     :unidentified (count (unidentified-sets correct-graph result-graph))
     :partially-correct-subsets (count (partially-correct-subsets correct-graph result-graph))
     :partially-correct-supersets (count (partially-correct-supersets correct-graph result-graph))}))

(defn compare-edge-lists-by-edges
  [correct-edges result-edges]
  (let [expected-matches-no (count correct-edges)
        result-matches-no (count result-edges)
        true-positives-no (count (true-positives correct-edges result-edges))]
    {:totals       {:result-matches result-matches-no
                    :expected-matches expected-matches-no}
     :matches      {:false-negatives (count (false-negatives correct-edges result-edges))
                    :false-positives (count (false-positives correct-edges result-edges))
                    :true-positives true-positives-no}
     :percentages  {:true-pos-by-exp-matches (float (/ (* true-positives-no 100) expected-matches-no))
                    :true-pos-by-res-matches (float (/ (* true-positives-no 100) result-matches-no))}}))

(defn compare-edge-lists
  [correct-edges result-edges]
  {:by-edges (compare-edge-lists-by-edges correct-edges result-edges)
   :by-sets (compare-edge-lists-by-sets correct-edges result-edges)})
