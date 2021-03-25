# Linear Search algorithm
#
# Most Algorithm used to find a value inside an array
# Performance: O(N) linear

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
