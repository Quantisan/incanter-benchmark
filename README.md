# incanter-benchmark

Benchmark incanter

## Result

![benchmark result](https://raw.github.com/Quantisan/incanter-benchmark/master/data/incanter_benchmark_blas.png)

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
