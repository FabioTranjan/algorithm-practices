# Insertion Sort algorithm
#
# Algorithm to sort an array
# Performance: O(N2) quadratic

def insertion_sort(array)
  (1..array.length - 1).to_a.each do |index|
    tmp_value = array[index]
    position = index - 1

    while position >= 0 do
        if array[position] > tmp_value
            array[position + 1] = array[position]
            position = position - 1
        else
            break
        end
    end

    array[position + 1] = tmp_value
  end

  return array
end
