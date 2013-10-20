(defn f1 [x] [(inc x) x (dec x)])

(defn f2 [x] [(dec x) x (inc x)])

(defn f3 [x] [x (* x x)])

(defn monad-bind [monad-value monad-function] (__ monad-function monad-value))

(defn monad-result [x] __)

(defn monad-compose [fn1 fn2 fn3] (fn [x] (-> x monad-result (monad-bind fn3) (monad-bind fn2) (monad-bind fn1))))

(defn increment [state-int]
  ["incremented!" (inc state-int)])

(defn decrement [state-int]
  ["decremented!" (dec state-int)])

(meditations
 "Contemplate how the function signatures corelate to the m(onad)-bind and m(onad)-result"
 (= [5 4 3 6 5 4 7 6 5 25 24 23 26 25 24 27 26 25]
    ((fn [x] (->> x f3 (mapcat f2) (mapcat f1))) 5)
    ((monad-compose f1 f2 f3) 5))

 "Do you think it is comp what you are doing? hm..."
 (= ((fn [x] (domonad __ [a (f3 x)
                         b (f2 a)
                         c (f1 b)]
                     c)) 10)
    ((fn [x] (->> x f3 (mapcat f2) (mapcat f1))) 10))

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
