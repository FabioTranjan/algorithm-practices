# Find first non duplicated
#
# Algorithm to find non-duplicated character in a string
# Performance: O(N) linear

def find_non_duplicate(string)
  hash_table = {}
  splitted_string = string.split('')

  splitted_string.each do |c|
    if hash_table[c]
      hash_table[c] += 1
    else
      hash_table[c] = 1
    end
  end

  splitted_string.each do |c|
    return c if hash_table[c] == 1
  end

  return
end

p find_non_duplicate('minimum')
p find_non_duplicate('abac')
p find_non_duplicate('aaa')
