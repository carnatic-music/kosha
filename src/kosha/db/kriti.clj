(ns kosha.db.kriti
  (:require [kosha.db.util :as db-util]))

(def kriti-ks
  [:kriti-id :name :composer :taala :url :lyrics :meaning :ragam-id :data-source :ragam-name])

(defn kriti
  "Retrieve details of a kriti given it's kriti_id."
  [kriti-id]
  (let [q ["SELECT k.*, r.name AS ragam_name
            FROM kritis k
            JOIN ragams r
            ON k.kriti_id = ? AND r.ragam_id = k.ragam_id; "
           kriti-id]]
    (-> (db-util/run-query q)
        first
        (select-keys kriti-ks))))

(def rendition-ks
  [:rendition-id :concert-id :concert-url :track-number :url :main-artist :kriti-name :kriti-id])

(defn renditions-of-kriti
  "Retrieve all renditions of a kriti."
  [kriti-id]
  (let [q ["SELECT *
            FROM renditions
            WHERE kriti_id = ?; "
           kriti-id]]
    (->> (db-util/run-query q)
         (map #(select-keys % rendition-ks))
         vec)))

(defn all-kritis []
  (let [q ["SELECT k.* FROM kritis k"]]
    (->> (db-util/run-query q)
         (map #(select-keys % kriti-ks)))))
