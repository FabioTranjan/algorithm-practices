require "benchmark"
require_relative "./linear_search"
require_relative "./binary_search"

puts("Running performance tests on different algorithms (measured in ms)")

arr = [*1..1_000_000]

# Examples
puts("\nLinear Search:")
lower_bound = Benchmark.realtime { linear_search(arr, 1) / 1000 } * 1000
middle_bound = Benchmark.realtime { linear_search(arr, 500_000) / 1000 } * 1000
upper_bound = Benchmark.realtime { linear_search(arr, 1_000_000) } * 1000
average = (lower_bound + middle_bound + upper_bound) / 3

puts("lower: #{lower_bound.to_s}, middle: #{middle_bound.to_s}, upper: #{upper_bound.to_s},
average: #{average.to_s}")

# Examples
puts("\nBinary Search:")
lower_bound = Benchmark.realtime { binary_search(arr, 1) / 1000 } * 1000
middle_bound = Benchmark.realtime { binary_search(arr, 500_000) / 1000 } * 1000
upper_bound = Benchmark.realtime { binary_search(arr, 1_000_000) } * 1000
average = (lower_bound + middle_bound + upper_bound) / 3

puts("lower: #{lower_bound.to_s}, middle: #{middle_bound.to_s}, upper: #{upper_bound.to_s},
average: #{average.to_s}")
