;; this file should contain a vector of 2-vectors; each 2-vector must
;; contain a koan name and a map of koan answers, in that order.

[["1_container_monads" {"__" [mapcat
                              [x]
                              f2
                              f1
                              [1 2 3 3 4 5]
                              nil
                              #{1 2 3 4 5}]}]

 ["2_comprehensions" {"__" [symbols
                            numbers
                            [:a :b]
                            [1 2]
                            f1
                            f2
                            f1
                            #{2 3 4 5 6}
                            6]}]

 ["3_state_monad" {"__" [inc
                         "incremented!"
                         "decremented!"
                         "incremented!"
                         6
                         "decremented!"
                         "incremented!"
                         6
                         "decremented!"
                         42
                         next-rand-int
                         next-rand-int
                         next-rand-int]}]

 ["4_parser" {"__" [1234567890
                    alpha
                    (one-of "_-.")
                    digit
                    "m"
                    "onad"
                    nil
                    "foobar"
                    "foobar and baz"
                    alpha
                    digit
                    digit
                    digit
                    (match-string " birds in ")
                    digit
                    (match-string " firtrees")
                    :foo-bar
                    "baz 14"
                    "# some comment"]
              "___" [alpha]}]]
