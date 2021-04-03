require_relative './doubly_linked_list'

# Queue data structure using array
class QueueArray
  def initialize
    @data = []
  end

  def enqueue(element)
    @data << element
  end

  def dequeue
    @data.shift
  end

  def read
    @data.first
  end
end

# Queue data structure using doubly linked-list
class QueueList
  attr_accessor :queue

  def initialize
    @data = DoublyLinkedList.new
  end

  def enqueue(element)
    @data.insert_at_end(element)
  end

  def dequeue
    removed_node = @data.remove_from_front
    return removed_node.data
  end

  def read
    return nil unless @data.first_node
    return @data.first_node.data
  end
end