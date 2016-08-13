(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [clojure.set :refer [intersection]]
            [card-game-war.game :refer :all]))

(def card-count 52)

;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round")
  (testing "queens are higher rank than jacks"
    (is (= 1 (compare (->Card :spade :queen) (->Card :spade :jack)))))
  (testing "kings are higher rank than queens"
    (is (= 1 (compare (->Card :spade :king) (->Card :spade :queen)))))
  (testing "aces are higher rank than kings"
    (is (= 1 (compare (->Card :spade :ace) (->Card :spade :king)))))
  (testing "if the ranks are equal, clubs beat spades"
    (is (= 1 (compare (->Card :club :ace) (->Card :spade :ace)))))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (= 1 (compare (->Card :diamond 2) (->Card :club 2)))))
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (= 1 (compare (->Card :heart 9) (->Card :diamond 9))))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"))

(deftest deal-cards
  (testing "52 cards are dealt"
    (is (apply = (conj (map count (deal deck)) (/ 52 2))))) 
  (testing "The cards don't overlap"
    (is (= 0 (count (apply intersection (deal deck)))))))

