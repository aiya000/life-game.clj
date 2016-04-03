(ns life-game.life-test
  (:require [clojure.test        :refer :all]
            [clojure.set         :as    cset]
            [life-game.life      :as    life]
            [life-game.data.cell :as    cell]))

; This pattern will birth (Cell. 2 2)
(def basic-birth #{(cell/new-cell 1 1)
                   (cell/new-cell 2 1)
                   (cell/new-cell 1 2)})

; These cells will dead by depopulated
(def basic-die-depop #{(cell/new-cell 1 1)
                       (cell/new-cell 1 2)})

; THese cells will dead by overcrowded
(def basic-die-over #{(cell/new-cell 1 1)
                      (cell/new-cell 1 2)
                      (cell/new-cell 1 3)
                      (cell/new-cell 2 1)
                      (cell/new-cell 2 2)})

(deftest birth-test
  (testing "birth func generate birthed cells rightly."
    (let [birth-cells (life/birth basic-birth)]
      (is (contains? birth-cells (cell/new-cell 2 2))))))

(deftest die-depopulated-test
  (testing "die-by-depop func generate dead cells rightly."
    (let [dead-cells (life/die-by-depop basic-die-depop)]
      (is (empty? (cset/difference dead-cells basic-die-depop))))))

(deftest die-overcrowded-test
  (testing "die-by-over func generate dead cells rightly."
    (let [dead-cells (life/die-by-over basic-die-over)]
      (is (empty? (cset/difference dead-cells basic-die-over))))))
