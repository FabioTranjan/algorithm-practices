# Quicksort algorithm
#
# Algorithm to sort an array based on recursion and partitioning
# Performance: O(N2) quadratic

class QuickSort
  attr_reader :array

  def initialize(array)
    @array = array
  end

  def partition!(left_pointer, right_pointer)
    pivot_index = right_pointer
    pivot = @array[pivot_index]

    right_pointer -= 1

    while true
      while @array[left_pointer] < pivot do
        left_pointer += 1
      end

      while @array[right_pointer] > pivot do
        right_pointer -= 1
      end

      if left_pointer >= right_pointer
        break
      else
        @array[left_pointer], @array[right_pointer] =
          @array[right_pointer], @array[left_pointer]

        left_pointer += 1
      end
    end

    @array[left_pointer], @array[pivot_index] =
      @array[pivot_index], @array[left_pointer]

    return left_pointer
  end

  def run!(left_index, right_index)
    return if right_index - left_index <= 0
  
    pivot_index = partition!(left_index, right_index)

    run!(left_index, pivot_index - 1)
    run!(pivot_index + 1, right_index)
  end
end

array = [0, 5, 2, 1, 6, 3]
quick_sort = QuickSort.new(array)
quick_sort.run!(0, array.length - 1)
p quick_sort.array