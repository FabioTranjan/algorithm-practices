# Intersetction algorithm
#
# Basic algorithm to intersect two arrays
# Performance: O(N) linear

def intersect(array1, array2)
  hashTable = {}
  mergedArray = []

  array1.each do |a1|
    hashTable[a1] = true
  end

  array2.each do |a2|
    mergedArray << a2 if hashTable[a2]
  end

  return mergedArray
end
