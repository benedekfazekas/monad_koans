(defn square [x] (* x x))

(defn f1 [x] [(dec x) x (inc x)])

(defn f2 [x] [x (square x)])

(defn as-set [x] (fn [y] (apply hash-set (x y))))

(def f1-set (as-set f1))

(def f2-set (as-set f2))

(defn monad-bind [monad-value monad-function] (__ monad-function monad-value))

(defn monad-result [x] __)

(defn monad-compose [fn1 fn2] (fn [x] (-> x monad-result (monad-bind fn2) (monad-bind fn1))))

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

 (with-monad sequence-m
   "Function composition is easy"
   (= __
      ((m-chain [f2 f1]) 2)))

 (with-monad set-m
   "Function composition is easy"
   (= __
      ((m-chain [f2-set f1-set]) 2)))

 "Just take a moment to comprehend list comprehension"
 (= [[:a 1] [:a 2] [:b 1] [:b 2]]
    (for [symbols [:a :b]
          numbers [1 2]]
      [__ __]))

 "How far is list comprehension from comprehensing the sequence monad?"
 (= [[:a 1] [:a 2] [:b 1] [:b 2]]
    (domonad sequence-m [symbols __
                         numbers __]
             [symbols numbers]))

 "Building lists out of lists: contemplate the type of 'a'"
 (= [2 3 4 4 5 6]
    (for [a (f2 2)
          b (__ a)]
      (+ b 1)))

 "Is there a real difference?"
 (= [2 3 4 4 5 6]
    (domonad sequence-m [a (__ 2)
                         b (__ a)]
             (+ b 1)))

 "Maybe the power of flexibilty"
 (= __
    (domonad set-m [a (f2 2)
                    b (f1 a)]
             (+ b 1)))

 "A monad not doing much lets you contemplate the basics of the language like let"
 (= __
    (domonad identity-m [a (square 2)
                         b (inc a)]
             (+ b 1))))
