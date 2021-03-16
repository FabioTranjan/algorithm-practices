# Linear Search algorithm
#
# Most basic algorithm used to find a value inside an array
# Performance: N linear

require "benchmark"

def linear_search(array, search_value)
  array.each_with_index do |element, index|
    if element == search_value
        return index
    elsif element > search_value
        break
    end
  end

  return nil
end

# Examples
arr = [*1..1_000_000]
puts(Benchmark.realtime { linear_search(arr, 1) / 1000 } * 1000)
puts(Benchmark.realtime { linear_search(arr, 1_000_000) } * 1000)

# Execution time
# 0.009007000016936217 ms
# 45.520635000002585 ms