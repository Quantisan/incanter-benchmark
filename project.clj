(defproject incanter-benchmark "0.1.0-SNAPSHOT"
  :description "incanter benchmark"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [criterium "0.3.1"]
                 [incanter/incanter-core "1.4.1"]]
  :profiles {:blas {:dependencies [[incanter/incanter-core "1.5.0-SNAPSHOT"]]}}
  :main incanter-benchmark.matrix)
