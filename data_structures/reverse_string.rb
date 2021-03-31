class Stack
  def initialize
    @data = []
  end

  def push(element)
    @data << element
  end

  def pop
    @data.pop
  end

  def read
    @data.last
  end
end

def reverse(string)
  stack = Stack.new

  string.split('').each do |c|
    stack.push(c)
  end

  while stack.read
    p stack.pop
  end
end

p reverse('abcde')