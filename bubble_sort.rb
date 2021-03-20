# Bubble Sort algorithm
#
# Basic algorithm to sort an array
# Performance: N2 quadratic

def bubble_sort(array)
  unsorted_until_index = array.length - 1
  sorted = false

  while !sorted do
    sorted = true
    unsorted_until_index -= 1

    (0..unsorted_until_index).to_a.each do |i|
        if array[i] > array[i + 1]
            array[i], array[i + 1] = array[i + 1], array[i]
            sorted = false
        end
    end
  end

  return array
end
