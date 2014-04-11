(ns constraint.validations.email-test
  (:require [clojure.test :refer :all]
            [constraint.core :refer :all]
            [constraint.validations.email :refer :all]))

(def ^:private valid-email
  "hello@listora.com")

(def ^:private valid-local-email
  "me@localhost")

(def ^:private invalid-email
  "nope")

(deftest test-email-with-no-args
  (testing "with valid email"
    (is (valid? (email) valid-email)))

  (testing "with local email"
    (is (not (valid? (email) valid-local-email))))

  (testing "with invalid email"
    (is (not (valid? (email) invalid-email))))

  (testing "validate with local email"
    (is (= (validate (email) valid-local-email)
           [{:error :invalid-email
             :message "Expected valid email address but found \"me@localhost\""
             :found valid-local-email}])))

  (testing "validate with invalid email"
    (is (= (validate (email) invalid-email)
           [{:error :invalid-email
             :message "Expected valid email address but found \"nope\""
             :found invalid-email}]))))

(deftest test-email-with-allow-local
  (testing "valid email with allow local"
    (is (valid? (email :allow-local) valid-email)))

  (testing "valid local email with allow local"
    (is (valid? (email :allow-local) valid-local-email)))

  (testing "invalid email with allow local"
    (is (not (valid? (email :allow-local) invalid-email))))

  (testing "validate with invalid email with allow local"
    (is (= (validate (email :allow-local) invalid-email)
           [{:error :invalid-email
             :message "Expected valid email address but found \"nope\""
             :found invalid-email}]))))
