(ns alphabet-cipher.coder
  (require [clojure.string :as string]))

(def looped-alphabet
  (->> (+ 26 97) ;; char code for z
       (range 97) ;; char code for a
       (cycle) ;; forever!!
       (map (comp str char)))) ;; letter

(defn letter-idx [x]
  (.indexOf looped-alphabet (str x)))

(defn encode-value [k m]
  (nth looped-alphabet
       (+ (letter-idx k) (letter-idx m))))

(defn encode [keyword message]
  (string/join
    (map encode-value
         message
         (cycle keyword))))

(defn decode [keyword message]
  "decodeme")

(defn decipher [cipher message]
  "decypherme")

