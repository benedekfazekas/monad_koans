;; this file should contain a vector of 2-vectors; each 2-vector must
;; contain a koan name and a map of koan answers, in that order.

[["1_monad_basics" {"__" [mapcat
                          [x]
                          sequence-m
                          "incremented!"
                          "decremented!"
                          "incremented!"
                          6
                          "decremented!"
                          "incremented!"
                          6
                          "decremented!"
                          42]}]

 ["2_parser" {"__" [1234567890
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
