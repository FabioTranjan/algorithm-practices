# Selection Sort algorithm
#
# Basic algorithm to sort an array
# Performance: N2 quadratic

def selection_sort(array)
  (0..(array.length - 2)).to_a.each do |i|
    lowest_number_index =  i
    init_index = i + 1
    (init_index..(array.length - 1)).to_a.each do |j|
      lowest_number_index = j if (array[j] < array[lowest_number_index])
    end

    if (lowest_number_index != i)
        array[i], array[lowest_number_index] = array[lowest_number_index], array[i]
    end
  end

  return array
end
