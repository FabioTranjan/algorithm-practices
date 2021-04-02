# Quicksort algorithm
#
# Algorithm to sort an array based on recursion and partitioning
# Performance: O(N * logN) quadratic

require_relative '../utils/sortable_array'

class QuickSort
  attr_reader :sortable_array

  def initialize(array)
    @sortable_array = SortableArray.new(array)
  end

  def run!(left_index, right_index)
    return if right_index - left_index <= 0
  
    pivot_index = sortable_array.partition!(left_index, right_index)

    run!(left_index, pivot_index - 1)
    run!(pivot_index + 1, right_index)
  end
end
