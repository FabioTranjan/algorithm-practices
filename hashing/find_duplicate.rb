# Find duplicate algorithm
#
# Basic algorithm to find a duplicate value on the array
# Performance: O(N) linear

def find_duplicate(array)
  hashTable = {}

  array.each do |a1|
    return a1 if hashTable[a1]
    hashTable[a1] = true
  end

  return
end
