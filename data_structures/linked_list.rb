require_relative './node'

class LinkedList
  attr_accessor :first_node

  def initialize(first_node)
    @first_node = first_node
  end

  def read(index)
    current_node = first_node
    current_index = 0

    while current_index < index do
      current_node = current_node.next_node
      current_index += 1
      
      return nil unless current_node
    end

    return current_node.data
  end

  def index_of(value)
    current_node = first_node
    current_index = 0

    begin
      if current_node.data == value
        return current_index
      end

      current_node = current_node.next_node
      current_index += 1
    end while current_node

    return nil
  end

  def insert_at_index(index, value)
    new_node = Node.new(value)

    if index == 0
      new_node.next_node = first_node
      self.first_node = new_node
      return
    end

    current_node = first_node
    current_index = 0

    while current_index < (index - 1) do
      current_node = current_node.next_node
      current_index += 1
    end

    new_node.next_node = current_node.next_node
    current_node.next_node = new_node
  end

  def delete_at_index(index)
    if index === 0
      self.first_node = first_node.next_node
      return
    end

    current_node = first_node
    current_index = 0

    while current_index < (index - 1) do
      current_node = current_node.next_node
      current_index += 1
    end

    node_after_deleted_node = current_node.next_node.next_node
    current_node.next_node = node_after_deleted_node
  end
end

node_1 = Node.new('once')
node_2 = Node.new('upon')
node_3 = Node.new('a')
node_4 = Node.new('time')

node_1.next_node = node_2
node_2.next_node = node_3
node_3.next_node = node_4

list = LinkedList.new(node_1)

p list.read(3)
p list.index_of('time')
p list.insert_at_index(3, 'purple')
p list.read(3)