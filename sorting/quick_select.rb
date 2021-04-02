# Quickselect algorithm
#
# Algorithm to find the nth lowest value from an array
# Performance: O(N) linear

require_relative '../utils/sortable_array'

class QuickSelect
  attr_reader :sortable_array, :array

  def initialize(array)
    @array = array
    @sortable_array = SortableArray.new(array)
  end

  def run!(nth_lowest_value, left_index, right_index)
    return array[left_index] if right_index - left_index <= 0
  
    pivot_index = sortable_array.partition!(left_index, right_index)

    if nth_lowest_value < pivot_index
      run!(nth_lowest_value, left_index, pivot_index - 1)
    elsif nth_lowest_value > pivot_index
      run!(nth_lowest_value, pivot_index + 1, right_index)
    else
      return array[pivot_index]
    end
  end
end

array = [0, 50, 20, 10, 60, 30]
p QuickSelect.new(array).run!(3, 0, array.length - 1)
