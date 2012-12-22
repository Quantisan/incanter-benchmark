require('ggplot2')
require('reshape2')

result <- read.csv("../data/result1.csv")
result <- melt(result, id = c("name", "size"), variable.name="library", value.name="benchmark")
p <- ggplot(result, aes(x=size, y=benchmark, colour=library))
p + geom_line() + facet_wrap(~ name, scales="free") + ylab("benchmark result [s]") + xlab("matrix size")