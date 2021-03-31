require "benchmark"
require_relative "./searching/linear_search"
require_relative "./searching/binary_search"
require_relative "./sorting/bubble_sort"
require_relative "./sorting/selection_sort"
require_relative "./sorting/insertion_sort"
require_relative "./sorting/quick_sort"

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
worst_case = Benchmark.realtime { bubble_sort(arr.take(2_000).reverse) } * 1000
average_case = Benchmark.realtime { bubble_sort(arr.take(2_000).shuffle) } * 1000
best_case = Benchmark.realtime { bubble_sort(arr.take(2_000)) } * 1000
puts("worst: #{worst_case.to_s}, average: #{average_case.to_s}, best: #{best_case.to_s}")

puts("\nSelection Sort:")
worst_case = Benchmark.realtime { selection_sort(arr.take(2_000).reverse) } * 1000
average_case = Benchmark.realtime { selection_sort(arr.take(2_000).shuffle) } * 1000
best_case = Benchmark.realtime { selection_sort(arr.take(2_000)) } * 1000
puts("worst: #{worst_case.to_s}, average: #{average_case.to_s}, best: #{best_case.to_s}")

puts("\nInsertion Sort:")
worst_case = Benchmark.realtime { insertion_sort(arr.take(2_000).reverse) } * 1000
average_case = Benchmark.realtime { insertion_sort(arr.take(2_000).shuffle) } * 1000
best_case = Benchmark.realtime { insertion_sort(arr.take(2_000)) } * 1000
puts("worst: #{worst_case.to_s}, average: #{average_case.to_s}, best: #{best_case.to_s}")

puts("\nQuick Sort:")
worst_case = Benchmark.realtime { QuickSort.new(arr.take(2_000).reverse).run!(0, 1999) } * 1000
average_case = Benchmark.realtime { QuickSort.new(arr.take(2_000).shuffle).run!(0, 1999) } * 1000
best_case = Benchmark.realtime { QuickSort.new(arr.take(2_000)).run!(0, 1999) } * 1000
puts("worst: #{worst_case.to_s}, average: #{average_case.to_s}, best: #{best_case.to_s}")