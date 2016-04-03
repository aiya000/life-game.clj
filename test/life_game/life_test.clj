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

(deftest birth-test
  (testing "birth func generate birthed cells rightly."
    (let [birth-cells (life/birth basic-birth)]
      (is (contains? birth-cells (cell/new-cell 2 2))))))

(deftest die-test
  (testing "die func generate dead cells rightly."
    (let [dead-cells (life/die-by-depop basic-die-depop)]
      (is (empty? (cset/difference dead-cells basic-die-depop))))))
