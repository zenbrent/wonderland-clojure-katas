(ns card-game-war.game
  (:import [java.lang Comparable]))

;; TODO:
;; Make a cli ui for playing.
;; Make it play on it's own.

;; x There are two players.
;; x The cards are all dealt equally to each player.
;; - Each round, player 1 lays a card down face up at the same time that
;;   player 2 lays a card down face up.  Whoever has the highest value
;;   card, wins both round and takes both cards.
;; - The winning cards are added to the bottom of the winners deck.
;; x Aces are high.
;; x If both cards are of equal value, then the winner is decided upon by
;;   the highest suit.  The suits ranks in order of ascending value are
;;   spades, clubs, diamonds, and hearts.
;; - The player that runs out of cards loses.

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])

(defn compare-idx [xs a b]
  (compare (.indexOf xs a) (.indexOf xs b)))

(defmulti str-card-rank class)
(defmethod str-card-rank java.lang.Long [rank] rank)
(defmethod str-card-rank clojure.lang.Keyword [rank] (name rank))

(defrecord Card [suit rank]
  Object
  (toString [this]
    (str (str-card-rank rank) " of " (name suit) "s"))
  Comparable
  (compareTo [this b]
    (let [t (compare-idx ranks (:rank this) (:rank b))]
      (if (= 0 t)
        (compare-idx suits (:suit this) (:suit b))
        t))))

(defn play-round
  "Check 2 cards and determine which wins."
  [player1-card player2-card]
  (compare player1-card player2-card))

(defn play-game
  "Given 2 vectors of cards, plays through all the cards and determines which wins."
  [player1-cards player2-cards])

(def deck 
  (set (for [suit suits
        rank ranks]
    (->Card suit rank))))

(defn deal
  "Returns a vector with 52 cards split between the 2 elements."
  [c]
  (loop [[player1 player2] [#{} #{}]
         cards c]
    (if (< (count cards) 2)
      [player1 player2]
      (let [card1 (rand-nth (vec cards))
            card2 (rand-nth (vec (disj cards card1)))]
        (recur
          [(conj player1 card1) (conj player2 card2)]
          (disj cards card1 card2))))))

  ; (let [half (/ (count cards) 2)]
  ;   [(take half cards) (drop half cards)]))

