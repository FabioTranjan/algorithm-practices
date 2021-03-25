# Find alphabet letter
#
# Basic algorithm to find missing alphabet letter from a sentence
# Performance: O(N) linear

def find_alphabet(string)
  hash_table = {}
  alphabet = [*('a'..'z')]

  string.split('').each do |c|
    hash_table[c] = true
  end

  alphabet.each do |a|
    return a unless hash_table[a]
  end

  return
end
