(defn increment [state-int]
  ["incremented!" (inc state-int)])

(defn decrement [state-int]
  ["decremented!" (dec state-int)])

(meditations
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
