# Find duplicate algorithm
#
# Basic algorithm to find a duplicate value on the array
# Performance: O(N) linear

def find_duplicate(array)
  hash_table = {}

  array.each do |a1|
    return a1 if hash_table[a1]
    hash_table[a1] = true
  end

  return
end
