(ns kosha.app.ragam
  (:require [kosha.db.ragam :as db]
            [kosha.app.util :as util]
            [ring.util.response :as res]))

(defn parent-ragam
  "Retrieves the parent ragam of a ragam, given the ragam map."
  [ragam]
  (let [parent-ragam-id (:mela_ragam_id ragam)]
    (when parent-ragam-id (db/ragam parent-ragam-id))))

(defn handler
  "Retrieves details of a ragam and its kritis, given the request containing ragam-id param."
  [{:keys [params] :as request}]
  (let [ragam-id  (Integer/parseInt (:ragam-id params))
        ragam     (db/ragam ragam-id)]
    (if (empty? ragam)
      (util/error-response 404 "Resource not found.")
      (res/response {:ragam        ragam
                     :kritis       (db/kritis-of-ragam ragam-id)
                     :parent-ragam (parent-ragam ragam)}))))
