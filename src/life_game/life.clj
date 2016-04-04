(ns life-game.life
  (:require [life-game.data.cell :as cell]
            [clojure.set         :as cset]))

(defn- new-cell-from-vec
  "Return new Cell instance from vector [x y]"
  [[a b]]
  (cell/new-cell a b))

(defn- spread
  "Return set of c spread cells"
  [c]
  (let [x (.x c)
        y (.y c)
        xs [(dec x) x (inc x)]
        ys [(dec y) y (inc y)]
        vecs (set (for [a xs b ys] [a b]))]
    (map new-cell-from-vec vecs)))

(defn- surround
  "Return set of c surronded cells"
  [c]
  (let [ss (set (spread c))]
    (cset/difference ss #{c})))

(defn- live?
  "The predicate of that cell is lived ?"
  [c prev-cells]
  (let [surround-cells (surround c)
        exists-cells (cset/intersection surround-cells prev-cells)]
    (>= (count exists-cells) 3)))

(defn birth
  "Return birthed cell-set"
  [prev-cells]
  (let [spread-cells (set (flatten (map spread prev-cells)))
        surround-cells (cset/difference spread-cells prev-cells)]
    (set (for [z surround-cells :when (live? z prev-cells)] z))))

(defn- depop?
  "The predicate of that cell will be dead by depopulated"
  [c prev-cells]
  (let [cells (cset/difference prev-cells #{c})
        surround-cells (surround c)
        exists-cells (cset/intersection surround-cells cells)]
    (<= (count exists-cells) 1)))

(defn- over?
  "The predicate of that cell will be dead by overcrowded"
  [c prev-cells]
  (let [cells (cset/difference prev-cells #{c})
        surround-cells (surround c)
        exists-cells (cset/intersection surround-cells cells)]
    (>= (count exists-cells) 5)))

(defn die-by-depop
  "Return dead cell as set, its cause is depopulated"
  [prev-cells]
  (set (for [z prev-cells :when (depop? z prev-cells)] z)))

(defn die-by-over
  "Return dead cells as set, its cause overcrowded"
  [prev-cells]
  (set (for [z prev-cells :when (over? z prev-cells)] z)))
