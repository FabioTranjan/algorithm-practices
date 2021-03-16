# Linear Search algorithm
#
# Most basic algorithm used to find a value inside an array
# Performance: N linear

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
