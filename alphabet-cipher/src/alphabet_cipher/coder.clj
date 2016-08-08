(ns alphabet-cipher.coder
  (require [clojure.string :as string]))

; (for [x (range 26)
;       y (range y (+ 26 y))])

(def looped-alphabet
  (->> (+ 26 97) ;; char code for z
       (range 97) ;; char code for a
       (cycle) ;; forever!!
       (map (comp str char)))) ;; letter

(println (take (* 3 26) looped-alphabet))

(defn letter-idx [x]
  (.indexOf looped-alphabet x))

(defn chart-row [x]
  (take 26 (drop (letter-idx x) looped-alphabet)))

(defn encode-value [k m]
  (nth
    (chart-row k)
    (letter-idx m)))

(defn encode [keyword message]
  (string/join (map (fn [a b] (encode-value (str a) (str b)))
                    message
                    (take (count message) (cycle keyword)))))

(defn decode [keyword message]
  "decodeme")

(defn decipher [cipher message]
  "decypherme")

