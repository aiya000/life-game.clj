(ns life-game.core
  (:gen-class)
  (:require [lanterna.screen       :as screen]
            [life-game.data.cell   :as cell]
            [life-game.data.values :as values]))

(def life-speed 200)

(defn game-core
  "life-game's main loop"
  [cells scr]
  (do
    (screen/clear scr)
    (doall (map #(.draw % scr) cells))
    (Thread/sleep life-speed)
    (recur cells scr)))

(defn -main []
  (let [scr (screen/get-screen)]
    (screen/start scr)
    (game-core values/nebra scr)
    (screen/stop scr)))
