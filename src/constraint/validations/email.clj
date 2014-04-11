(ns constraint.validations.email
  (:import [org.apache.commons.validator.routines EmailValidator])
  (:require [clojure.string :as str]
            [constraint.core :refer [Transform]]))

(defn validator [allow-local]
  (proxy [EmailValidator] [allow-local]))

(defn error-message [value]
  (str "Expected valid email address but found \"" value "\""))

(defn- invalid-email [value]
  {:error :invalid-email
   :message (error-message value)
   :found value})

(defn- valid? [allow-local value]
  (.isValid (validator allow-local) value))

(deftype Email [allow-local]
  Transform
  (transform* [_ value]
    (if-not (valid? allow-local value)
      {:errors #{(invalid-email value)}})))

(defn email
  "Create an email validation. Can optionally allow local email addresses if
  `allow-local` is true. Defaults to considering local addresses invalid."
  ([]            (email false))
  ([allow-local] (Email. (boolean allow-local))))
