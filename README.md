# incanter-benchmark

Benchmark incanter

##

![benchmark result](http://www.quantisan.com/static/images/2012/incanter_benchmark_blas.png)

## Usage

With standard library

```bash
lein do clean, run 10 1110 100
```

With BLAS profile

```bash
lein with-profile blas do clean, run 10 1110 100
```

## License

Copyright © 2012 Paul Lam

Distributed under the Eclipse Public License, the same as Clojure.
