(ns kosha.app.util
  (:require [cheshire.core :as json]
            [immuconf.config :as config]
            [ring.util.response :as res]))

(defonce config-map (config/load "resources/config.edn"))
(defn get-config [& ks]
  (apply config/get config-map ks))

(defn json-response [response]
  {:status 200
   :body (json/encode response)
   :content-type "application/json"})

(defn error-response
  [status msg]
  (-> (res/response {:error msg})
      (res/status status)))
