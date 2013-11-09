(defmonad parser-m
  [m-result (fn [x]
              (fn [strn]
                (list x strn)))

   m-bind (fn [parser func]
            (fn [strn]
              (let [result (parser strn)]
                (when (not= nil result)
                  ((func (first result)) (second result))))))

   m-zero (fn [strn]
            nil)

   m-plus (fn [& parsers]
            (fn [strn]
              (first
               (drop-while nil?
                           (map #(% strn) parsers)))))])

(defn any-char [strn]
  (if (= "" strn)
    nil
    (list (first strn) (. strn (substring 1)))))


(defn char-test [pred]
  (domonad parser-m
           [c any-char
            :when (pred c)]
           (str c)))

(defn is-char [c]
  (char-test (partial = c)))

(defn one-of [target-strn]
  (let [str-chars (into #{} target-strn)]
    (char-test #(contains? str-chars %))))

(def alpha (one-of "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"))
(def whitespace (one-of " \t\n\r"))
(def digit (one-of "__"))

(with-monad parser-m

  (defn match-string [target-strn]
    (if (= "" target-strn)
      (m-result "")
      (domonad parser-m
               [c (is-char (first target-strn))
                cs (match-string (. target-strn (substring 1)))]
               (str c cs) )))

  (defn match-all [& parsers]
    (m-fmap (partial apply str)
            (m-seq parsers)))

  (defn optional [parser]
    (m-plus parser (m-result nil)))

  (def match-one m-plus)

  (declare one-or-more)

  (defn none-or-more [parser]
    (optional (one-or-more parser)))

  (defn one-or-more [parser]
    (domonad
     [a parser
      as (none-or-more parser)]
     (str a as))))

(def key-parser
  (match-all
   (match-one __)
   (none-or-more
    (match-one
     alpha
     __
     __))))

(def value-parser
  (none-or-more
   (match-one
    alpha
    whitespace
    digit)))

(def property-parser
  (domonad parser-m
           [key key-parser
            _ (is-char \=)
            value value-parser]
           {(keyword key) value}))


(meditations
 "Parsing a character means separating it from the rest"
 (= '(__ __)
    ((is-char \m) "monad"))

 "Is there anything to return if there is no match?"
 (= __
    ((is-char \m) "danom"))

 "creating new parsers is easy"
 (= '("5" " gherkins")
    (digit "5 gherkins"))

 "in fact, it is easy to write a parser for any string"
 (= '("foobar" " and baz")
    ((match-string __) __))

 "parse me: maybe"
 (= '(nil "4abc")
    ((optional ___) "4abc"))

 "parse this or that"
 (= '("4" "abc")
    ((match-one __ __) "4abc"))

 "parse all or burn"
 (= '("15 birds in 5 firtrees" ", [...] fry them, boil them and eat them hot")
    ((match-all
      __
      __
      __
      __
      __)
     "15 birds in 5 firtrees, [...] fry them, boil them and eat them hot"))

 "you have everything to build a bit more complex parser"
 (= true
    (not (nil? (key-parser "foo")))
    (not (nil? (key-parser "foo-bar")))
    (not (nil? (key-parser "foo_bar")))
    (not (nil? (key-parser "foo22")))
    (nil? (key-parser "1foo"))
    (nil? (key-parser " foobar")))

 "so lets parse a property into a simple data structure"
 (= '({__ __} __)
    (property-parser "foo-bar=baz 14# some comment"))
)
