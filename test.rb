require "benchmark"
require_relative "./searching/linear_search"
require_relative "./searching/binary_search"
require_relative "./sorting/bubble_sort"
require_relative "./sorting/selection_sort"

puts("Running performance tests on different algorithms (measured in ms)")

arr = [*1..1_000_000]

puts("\nLinear Search:")
lower_bound = Benchmark.realtime { linear_search(arr, 1) / 1000 } * 1000
middle_bound = Benchmark.realtime { linear_search(arr, 500_000) / 1000 } * 1000
upper_bound = Benchmark.realtime { linear_search(arr, 1_000_000) } * 1000
average = (lower_bound + middle_bound + upper_bound) / 3

puts("lower: #{lower_bound.to_s}, middle: #{middle_bound.to_s}, upper: #{upper_bound.to_s},
average: #{average.to_s}")

puts("\nBinary Search:")
lower_bound = Benchmark.realtime { binary_search(arr, 1) / 1000 } * 1000
middle_bound = Benchmark.realtime { binary_search(arr, 500_000) / 1000 } * 1000
upper_bound = Benchmark.realtime { binary_search(arr, 1_000_000) } * 1000
average = (lower_bound + middle_bound + upper_bound) / 3

puts("lower: #{lower_bound.to_s}, middle: #{middle_bound.to_s}, upper: #{upper_bound.to_s},
average: #{average.to_s}")

puts("\nBubble Sort:")
lower_bound = Benchmark.realtime { bubble_sort(arr.take(10).shuffle) } * 1000
middle_bound = Benchmark.realtime { bubble_sort(arr.take(100).shuffle) } * 1000
upper_bound = Benchmark.realtime { bubble_sort(arr.take(1_000).shuffle) } * 1000
average = (lower_bound + middle_bound + upper_bound) / 3

puts("lower: #{lower_bound.to_s}, middle: #{middle_bound.to_s}, upper: #{upper_bound.to_s},
average: #{average.to_s}")

puts("\nSelection Sort:")
lower_bound = Benchmark.realtime { selection_sort(arr.take(10).shuffle) } * 1000
middle_bound = Benchmark.realtime { selection_sort(arr.take(100).shuffle) } * 1000
upper_bound = Benchmark.realtime { selection_sort(arr.take(1_000).shuffle) } * 1000
average = (lower_bound + middle_bound + upper_bound) / 3

puts("lower: #{lower_bound.to_s}, middle: #{middle_bound.to_s}, upper: #{upper_bound.to_s},
average: #{average.to_s}")
