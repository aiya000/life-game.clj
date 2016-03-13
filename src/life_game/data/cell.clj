(ns life-game.data.cell
  (:require [lanterna.screen :as screen]))

(def looks-live "@")

(defprotocol CellActions
  (move [this])
  (draw [this scr]))

(defrecord Cell [x y]
  CellActions
  (move [this] (Cell. x (+ y 1)))
  (draw [this scr]
    (do
      (screen/put-string scr (.x this) (.y this) looks-live)
      (screen/redraw scr))))

(defn new-cell [x y] (Cell. x y))
