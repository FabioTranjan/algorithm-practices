# Tree based data-structures with BST methods

class TreeNode
  attr_accessor :value, :left, :right

  def initialize(value, left, right)
    @value = value
    @left = left
    @right = right
  end

  def search(value)
    return self if self.nil? || self.value == value

    return left.search(value) if value < self.value
    right.search(value)
  end

  def insert(value)
    if value < self.value
      if left
        left.insert(value)
      else
        self.left = TreeNode.new(value, nil, nil)
      end
    elsif value > self.value
      if right
        right.insert(value)
      else
        self.right = TreeNode.new(value, nil, nil)
      end
    end
  end

  def print
    return if self.nil?

    left.print unless left.nil?
    p value
    right.print unless right.nil?
  end

  def greatest
    return self if right.nil? && left.nil?

    if self.right
      right.greatest
    elsif self.left
      left.greatest
    end
  end
end

left = TreeNode.new(1, nil, nil)
right = TreeNode.new(3, nil, nil)
root = TreeNode.new(2, left, right)

p root.search(1)
root.insert(4)
root.print

p root.greatest