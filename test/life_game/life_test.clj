(ns life-game.life-test
  (:require [clojure.test        :refer :all]
            [clojure.set         :as    cset]
            [life-game.life      :as    life]
            [life-game.data.cell :as    cell]))

; This pattern will birth (Cell. 2 2)
(def basic-birth #{(cell/new-cell 1 1)
                   (cell/new-cell 2 1)
                   (cell/new-cell 1 2)})

(deftest birth-test
  (testing "birth func generate new cell rightly"
    (let [birth-cells (life/birth basic-birth)]
      (is (contains? birth-cells (cell/new-cell 2 2))))))
