# Intersetction algorithm
#
# Basic algorithm to intersect two arrays
# Performance: O(N) linear

def intersect(array1, array2)
  hash_table = {}
  mergedArray = []

  array1.each do |a1|
    hash_table[a1] = true
  end

  array2.each do |a2|
    mergedArray << a2 if hash_table[a2]
  end

  return mergedArray
end
