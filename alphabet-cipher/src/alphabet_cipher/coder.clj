(ns alphabet-cipher.coder
  (require [clojure.string :as string]))

(def looped-alphabet
  "(concat (a-z) (a-z))"
  (let [a 97
        z (+ 26 97)]
    (->> (range a z)
         (map (comp str char)) ;; letter 
         cycle))) ;; forever!! 

(defn letter-idx
  "Gives the index of a letter in a looping alphabet, optionally with an offset."
  ([x] (.indexOf looped-alphabet (str x)))
  ([offset x] (.indexOf (drop (letter-idx offset) looped-alphabet) (str x)))) 

(defn encode-value [k m]
  (nth looped-alphabet
       (+ (letter-idx k) (letter-idx m))))

(defn decode-value
  "find index of m at row k"
  [k m]
  (nth looped-alphabet
       (letter-idx k m)))

(defn encode [keyword message]
  (string/join
    (map encode-value
         (cycle keyword)
         message)))

(defn decode [keyword message]
  (string/join
    (map decode-value
         (cycle keyword)
         message)))

(defn decipher [cipher message]
  "decypherme")

