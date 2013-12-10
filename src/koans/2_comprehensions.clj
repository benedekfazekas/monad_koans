(defn square [x] (* x x))

(defn f1 [x] [(dec x) x (inc x)])

(defn f2 [x] [x (square x)])

(meditations
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
