(ns alphabet-cipher.coder
  (require [clojure.string :as string]))

(def looped-alphabet
  "(concat (a-z) (a-z))"
  (let [a 97
        z (+ 26 97)]
    (->> (range a z)
         (map (comp str char)) ;; letter 
         cycle))) ;; forever!! 

(defn letter-idx-of
  "Gives the index of a letter in a looping alphabet, optionally with an offset."
  ([str-list x] (.indexOf str-list (str x)))
  ([str-list offset x] (.indexOf (drop (letter-idx-of str-list offset) str-list) (str x)))) 

(defn letter-idx
  "Gives the index of a letter in a looping alphabet, optionally with an offset."
  ([x] (letter-idx-of looped-alphabet x))
  ([offset x] (letter-idx-of looped-alphabet offset x))) 

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

(defn match-all-and-start-of-last [items]
  (if (apply = (map count items))
    (apply = items)
    (and (apply = (butlast items))
         (let [l (subvec items (count items))
               f (take (count l) (first items))]
           (= f l)))))

;; (a . . .) (a . . .) (a .)
;; if you slice them at intervals every (last repeats), and they all match
;; that's it
;; if not, slice them at every (second-to-last repeats), and they all match
;; that's it
;; if not, slice them at every (third-to-last repeats), and they all match
;; that's it
(defn decipher
  "if the message is shorter than the key, the full key can't be recovered."
  [cipher message]
  (let [looped-key (vec (map decode-value
                             message
                             cipher))
        first-char-repeats (reduce-kv
                             (fn [acc idx values]
                               (if (= (first looped-key) values)
                                 (conj acc idx)
                                 acc))
                             []
                             looped-key)
        string-repeats (mapcat
                         (fn [n] (let [xs (partition n looped-key)]
                                   (if (< n (/ (count looped-key) 2))
                                     [xs]
                                     [])))
                         (filter #(not= 0 %) first-char-repeats))
        matches (map first (filter match-all-and-start-of-last string-repeats))]
    (string/join (first matches))))

