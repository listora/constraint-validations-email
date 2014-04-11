# constraint-validations-email

Adds support for validating emails to [constraint][]

## Installation

To install via Leiningen add the following dependency to your `project.clj`:

``` clj
[listora/constraint-validations-email "0.0.1"]
```

## Usage

``` clj
(require '[constraint.core :refer [valid?]]
         '[constraint.validations.email :refer [email]])

(valid? (email) "hello@example.com") ; => true
```

Validation is handled by [commons-validator][] internally. Support for
validation of local addresses will be made available as soon as we can [update
commons-logging][].

## License

Copyright © 2014 Listora

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

[constraint]: https://github.com/listora/constraint
[commons-validator]: http://commons.apache.org/proper/commons-validator/apidocs/overview-summary.html
[update commons-logging]: http://stackoverflow.com/questions/5706914/noclassdeffounderror-problems-with-help-of-apache-commons-validator
