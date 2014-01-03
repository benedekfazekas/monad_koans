(defn increment [state-int]
  ["incremented!" (inc state-int)])

(defn decrement [state-int]
  ["decremented!" (dec state-int)])

(defn next-rand-int [from]
  "Generates random int between 'from' inclusive and Integer$MAX_VALUE exclusive.
   Returns the random and the next int which can be feed into the next call as from."
  (let [rand-between (fn [f t] (+ (rand-int (- t f)) f))
        random (rand-between from Integer/MAX_VALUE)]
    [random (__ random)]))

(meditations
 "Dynamic balance means lots of movement but no change
Realise that the increment and decrement functions are monadic values for the state monad"
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
              [a x b]) 5))

 "Let's generate a strictly monotonically increasing sequence with three elements
  Contemplate how the plumbing between the functions is hidden"
 (let [[inc-seq state] ((domonad state-m [rnd1 __
                                          rnd2 __
                                          rnd3 __]
                                 (list rnd1 rnd2 rnd3)) 10)]
   (and (< (first inc-seq) (second inc-seq))
        (< (second inc-seq) (last inc-seq))
        (< (last inc-seq) state))))
