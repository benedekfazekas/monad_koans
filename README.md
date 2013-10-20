# monad-koans

Koans are an excellent way to understand language concepts in an active, coding way. This project is dedicated to understanding monads in clojure. So it is somewhat assumed that monad padawans know a bit of clojure when they get here.
Current monad koans are based on [Jim Duey's tutorial](http://www.intensivesystems.net/tutorials/monads_101.html). More to follow.

### Running the Koans

To run the koans, simply run:

`script/run` on Mac/\*nix

`script\run` on Windows

If you're running from a checkout using lein 2, run the koans via

`lein koan run`

It's an auto-runner, so as you save your files with the correct answers, it will
advance you to the next koan or file (conveniently, all files are prefixed with
the sequence that you should follow).

You'll see something like this:

    Now meditate upon /home/benedek/projects/monad-koans/src/koans/1_monad_basics.clj:23
    ---------------------
    Assertion failed!
    Contemplate how the function signatures corelate to the m(onad)-bind and m(onad)-result
    (= [5 4 3 6 5 4 7 6 5 25 24 23 26 25 24 27 26 25] ((fn [x] (->> x f3 (mapcat f2) (mapcat f1))) 5) ((monad-compose f1 f2 f3) 5))

The output is telling you that you have a failing test in `src/koans/1_monad-basics.clj`. Open that file up and make it pass!  In general, you just fill in the blanks to make tests pass.  Sometimes there are several (or even an infinite number) of correct answers: any of them will work in these cases.

The koans differ from normal TDD in that the tests are already written for you, so you'll have to pay close attention to the failure messages, because up until the very end, making a test pass just means that the next failure message comes up.

While it might be easy (especially at first) to just fill in the blanks making things pass, you should work thoughtfully, making sure you understand why the answer is what it is.  Enjoy your path to enlightenment!

### Trying more things out

monad-koans comes with a REPL (Read-Evaluate-Print Loop).

`script/repl` on Mac/\*nix

`script\repl` on Windows

Here are some interesting commands you might try once you've got the REPL running:

    (find-doc "vec")
    (find-doc #"vec$")
    (doc vec)

And if those still don't make sense:

    (doc doc)
    (doc find-doc)

will show you what those commands mean.

You can exit the REPL with `CTRL-d` on any OS.

### Credits

This project was created using the goodies created by the koan-engine guys: https://github.com/functional-koans/clojure-koans/contributors and using their leiningen template as well. Specially thanks to [Colin Jones](https://github.com/trptcolin).

#### Original Credits

These exercises were started by [Aaron Bedra](http://github.com/abedra) of
[Relevance, Inc.](http://github.com/relevance) in early 2010, as a learning
tool for newcomers to functional programming. Aaron's macro-fu makes these
koans extremely simple and fun to use, and to improve upon, and without
Relevance's initiative, this project would not exist.

Using the [koans](http://en.wikipedia.org/wiki/koan) metaphor as a tool for
learning a programming language started with the
[Ruby Koans](http://rubykoans.com) by [EdgeCase](http://github.com/edgecase).

Monad koans are based on [Jim Duey's tutorial](http://www.intensivesystems.net/tutorials/monads_101.html).

### License

Copyright (C) 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
