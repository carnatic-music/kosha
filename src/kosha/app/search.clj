(ns kosha.app.search
  (:require [kosha.db.search :as db-search]
            [kosha.app.util :as util]
            [ring.util.response :as res]))


(def no-of-results (util/get-config :api :search-results))

(defn get-results
  "Retrieves 2n best matches for given ragam or kriti name."
  [query n]
  (let [ragams  (future (db-search/ragams query n))
        kritis  (future (db-search/kritis query n))
        results (sort-by :similarity > (concat @ragams @kritis))]
    (res/response (into [] results))))

(defn handler [{:keys [params]}]
  (let [query (:query params)]
    (get-results query no-of-results)))
