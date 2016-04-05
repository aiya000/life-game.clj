(ns life-game.core
  (:gen-class)
  (:require [lanterna.screen       :as screen]
            [clojure.set           :as cset]
            [life-game.data.cell   :as cell]
            [life-game.data.values :as values]
            [life-game.life        :as life]))

(def life-speed 200)

(defn- remove-out-of-bounds-cells
  "Remove unused cells"
  [cells]
  (do
    (defn- is-out [c] (or (< (.x c) 0)
                          (< (.y c) 0)))
    (set (for [x cells :when (not (is-out x))] x))))

(defn game-core
  "life-game's main loop"
  [cells scr]
  (do
    (screen/clear scr)
    (doall (map #(.draw % scr) cells))
    (Thread/sleep life-speed)
    (let [birthed-cells (-> cells life/birth        remove-out-of-bounds-cells)
          dead-cells-1  (-> cells life/die-by-depop remove-out-of-bounds-cells)
          dead-cells-2  (-> cells life/die-by-over  remove-out-of-bounds-cells)
          increased-cells (cset/union cells birthed-cells)
          dead-cells      (cset/union dead-cells-1 dead-cells-2)
          result-cells    (cset/difference increased-cells dead-cells)]
      (recur result-cells scr))))

(defn -main []
  (let [scr (screen/get-screen)]
    (screen/start scr)
    (game-core values/nebra scr)
    (screen/stop scr)))
