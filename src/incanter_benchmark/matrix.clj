;; based on https://github.com/forward/incanter-BLAS/blob/master/src/main/clojure/incanter/benchmark.clj 

(ns incanter-benchmark.matrix
  (:use incanter.core)
  (:require [criterium.core :as b])
  (:gen-class))

(defn rand-mat [size]
  (let [r (range 0 size)]
    (matrix (for [_ r] (for [_ r] (rand))))))

(defmacro benchmark-test [name & body]
  `(let [t# (fn [~'m ~'size] ~@body)]
     [~name
      (fn [size#]
        (let [m# (rand-mat size#)]
          (double (first (:mean (b/quick-benchmark (t# m# size#)))))))]))

(defn def-suite [& tests]
  (reduce (fn [ac [name f]]
            (assoc ac name f))
    {}
    tests))

(defn benchmark [min-size max-size step suite]
  (let [sizes (range min-size max-size step)
        results {}]
    (reduce (fn [total-results test-name]
              (let [to-test (test-name suite)
                    test-results (reduce (fn [test-results size]
                                           (let [result (to-test size)]
                                             (assoc test-results size {:result result})))
                                          {}
                                          sizes)]
                (assoc total-results test-name test-results)))
      {}
      (keys suite))))

(defn report [results]
  (println "name,size,run-time")
  (doseq [test-name (keys results)]
    (let [test-results (test-name results)
          sizes (sort (keys test-results))]
      (doseq [size sizes]
        (let [this-results (get test-results size)]
          (println (str (name test-name) "," size "," (:result this-results))))))))

(def ^:dynamic *benchmark-suite*  (def-suite

                                    (benchmark-test
                                      :matrix-creation
                                      (do (rand-mat size) :ok))

                                    (benchmark-test
                                      :matrix-multiplication
                                      (do (mmult m m) :ok))

                                    (benchmark-test
                                      :matrix-element-pow
                                      (do (pow m m) :ok))

                                    (benchmark-test
                                      :solve
                                      (do (solve m) :ok))

                                    (benchmark-test
                                      :transpose-matrix-multiplication
                                      (do (mmult m (trans m)) :ok))

                                    (benchmark-test
                                      :matrix-addition
                                      (do (plus m m) :ok))

                                    (benchmark-test
                                      :matrix-scalar-multiplication
                                      (do (mult m 1) :ok))

                                    (benchmark-test
                                      :eigenvalues
                                      (do (decomp-eigenvalue m) :ok))))

(defn -main [min max step]
  (report (benchmark (Integer/parseInt min) (Integer/parseInt max) (Integer/parseInt step) *benchmark-suite*)))
