(ns alphabet-cipher.coder
  (require [clojure.string :as string]))

(def substitution-chart
  (mapv (fn [xs] (mapv #(str (char %)) xs))
        (mapv
          (fn [n] (take 26 (drop n (cycle (range 97 (+ 26 97))))))
          (range 26))))

(defn val-at [k m]
  (let [row (first (filter #(= (first %) k) substitution-chart))
        col (.indexOf (first substitution-chart) m)]
    (nth row col)))

(defn encode [keyword message]
  (string/join (map (fn [a b] (val-at (str a) (str b)))
                    message
                    (take (count message) (cycle keyword)))))

(defn decode [keyword message]
  "decodeme")

(defn decipher [cipher message]
  "decypherme")

