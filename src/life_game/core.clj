(ns life-game.core
  (:gen-class)
  (:require [lanterna.screen       :as screen]
            [life-game.data.cell   :as cell]
            [life-game.data.values :as values]
            [life-game.life        :as life]))

(def life-speed 200)

(defn game-core
  "life-game's main loop"
  [cells scr]
  (do
    (screen/clear scr)
    (doall (map #(.draw % scr) cells))
    (screen/get-key-blocking scr) ;NOTE: for debug
    ;(Thread/sleep life-speed)
    (let [birthed-cells (life/birth cells)
          dead-cells-1 (life/die-by-depop cells)
          dead-cells-2 nil]
      (recur dead-cells-1 scr))))

(defn -main []
  (let [scr (screen/get-screen)]
    (screen/start scr)
    (game-core values/basic-die scr) ;NOTE: for debug
    ;(game-core values/nebra scr)
    (screen/stop scr)))
