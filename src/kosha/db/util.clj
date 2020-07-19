(ns kosha.db.util
  (:require [clojure.java.jdbc :as j]
            [kosha.db.pool :as db-pool]))

(defn ->hyphens [^String x]
  (keyword (.replace x \_ \-)))

(defn run-query
  [query]
  (j/query db-pool/conn query :identifiers ->hyphens))

(defn execute
  [query]
  (j/execute! db-pool/conn query))

(defn insert-multiple-names
  [values table]
  (let [table-name (:name table)
        col (:name-column table)
        rows (for [row values]
               {(keyword col) row})]
    (apply j/insert! db-pool/conn table-name rows)))
