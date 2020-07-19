(ns kosha.db.ragam
  (:require [clojure.set :as set]
            [kosha.db.util :as db-util]))

(def ragam-ks
  [:ragam-id :name :arohanam :avarohanam :ragam-link :melakartha :mela-ragam-id :data-source])

(def new-ragam-ks
  {:ragam-link :wiki-page})

(defn ragam
  "Retrieves details of a ragam given it's id."
  [ragam-id]
  (let [q ["SELECT *
            FROM ragams
            WHERE ragam_id = ?; "
           ragam-id]]
    (-> (db-util/run-query q)
        first
        (select-keys ragam-ks)
        (set/rename-keys new-ragam-ks))))

(def kriti-ks
  [:kriti-id :name :composer :taala :language :ragam-id :url :data-source])

(defn kritis-of-ragam
  "Retrieves all kritis of a ragam with the given id."
  [ragam-id]
  (let [q ["SELECT *
            FROM kritis
            WHERE ragam_id = ?; "
           ragam-id]]
    (->> (db-util/run-query q)
         (map #(select-keys % kriti-ks))
         vec)))
