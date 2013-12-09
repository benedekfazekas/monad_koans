(defn f1 [x] [(dec x) x (inc x)])

(defn f2 [x] [x (* x x)])

(defn monad-bind [monad-value monad-function] (__ monad-function monad-value))

(defn monad-result [x] __)

(defn monad-compose [fn1 fn2] (fn [x] (-> x monad-result (monad-bind fn2) (monad-bind fn1))))

(defn increment [state-int]
  ["incremented!" (inc state-int)])

(defn decrement [state-int]
  ["decremented!" (dec state-int)])

(meditations
 "Contemplate how the function signatures corelate to the m(onad)-bind and m(onad)-result"
 (= [4 5 6 24 25 26]
    ((fn [x] (->> x f2 (mapcat f1))) 5)
    ((monad-compose f1 f2) 5))

 "Do you think it is comp what you are doing? hm..."
 (= [9 10 11 99 100 101]
    ((fn [x] (domonad sequence-m [a (__ x)
                                 b (__ a)]
                     b)) 10))

 "Dynamic balance means lots of movement but no change
Relize that the increment and decrement functions are monadic values for the state monad"
 (= '([__ __] 5)
    ((domonad state-m [a increment
                       b decrement]
              [a b]) 5))

 "You are allowed to peek"
 (= '([__ __ __] 5)
    ((domonad state-m [a increment
                       x (fetch-state)
                       b decrement]
              [a x b]) 5))

 "Why not take controll even?"
 (= '([__ __ __] __)
    ((domonad state-m [a increment
                       x (set-state 43)
                       b decrement]
              [a x b]) 5)))
