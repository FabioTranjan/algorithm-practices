require_relative './trie_node'

class Trie
  def initialize()
    @root = TrieNode.new
  end

  def search(word)
    currentNode = @root

    word.split('').each do |char|
      return nil unless currentNode.children[char]

      currentNode = currentNode.children[char]
    end

    return currentNode
  end

  def insert(word)
    currentNode = @root

    word.split('').each do |char|
      if currentNode.children[char]
        currentNode = currentNode.children[char]
      else
        newNode = TrieNode.new
        currentNode.children[char] = newNode
        currentNode = newNode
      end
    end

    currentNode.children['*'] = nil
  end

  def collect_all_words(node = nil, word = '', words = [])
    currentNode = node || @root

    currentNode.children.each do |key, child|
      if key == '*'
        words << word
      else
        collect_all_words(child, word + key, words)
      end
    end

    return words
  end

  def autocomplete(prefix)
    currentNode = search(prefix)
    return nil unless currentNode

    return collect_all_words(currentNode)
  end
end
