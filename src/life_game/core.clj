(ns life-game.core
  (:gen-class)
  (:require [lanterna.screen     :as screen]
            [life-game.data.cell :as cell]))

(def life-speed 200)

(defn game-core [cells scr]
  (do
    (screen/clear scr)
    (doall (map #(.draw % scr) cells))
    (Thread/sleep life-speed)
    (let [[scr-width scr-height] (screen/get-size scr)
          x (int (rand scr-width))
          y (int (rand scr-height))
          c (cell/new-cell x y)]
      (recur (conj cells c) scr))))

(defn -main []
  (let [scr (screen/get-screen)
        cells #{}]
    (screen/start scr)
    (game-core cells scr)
    (screen/stop scr)))
