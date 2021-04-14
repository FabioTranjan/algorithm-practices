require_relative './queue'

class Vertex
  attr_accessor :value, :adjacent_vertices

  def initialize(value)
    @value = value
    @adjacent_vertices = []
  end

  def add_adjacent_vertex(vertex)
    @adjacent_vertices << vertex
  end

  def dfs_traverse(vertex, visited_vertices = {})
    visited_vertices[vertex.value] = true

    vertex.adjacent_vertices.each do |adjacent_vertex|
      next if visited_vertices[adjacent_vertex.value]
      dfs_traverse(adjacent_vertex, visited_vertices)
    end
  end

  def bfs_traverse(starting_vertex)
    queue = []

    visited_vertices = {}
    visited_vertices[starting_vertex.value] = true
    queue.enqueue(starting_vertex)

    while queue.read
      current_vertex = queue.dequeue

      current_vertex.adjacent_vertices.each do |adjacent_vertex|
        if !visited_vertices[adjacent_vertex.value]
          visited_vertices[adjacent_vertex.value] = true
          queue.enqueue(adjacent_vertex)
        end
      end
    end
  end

  def dfs(vertex, search_value, visited_vertices={})
    return vertex if vertex.value == search_value
    visited_vertices[vertex.value] = true

    vertex.adjacent_vertices.each do |adjacent_vertex|
      next if visited_vertices[adjacent_vertex.value]
      return adjacent_vertex if adjacent_vertex.value == search_value
      vertex_were_searching_for = dfs(adjacent_vertex, search_value, visited_vertices)
      return vertex_were_searching_for if vertex_were_searching_for
    end

    return nil
  end

  def bfs(vertex, search_value, visited_vertices={})
    queue = QueueArray.new

    visited_vertices[vertex.value] = true
    queue.enqueue(vertex)

    while queue.read
      current_vertex = queue.dequeue
      return current_vertex if current_vertex.value == search_value

      current_vertex.adjacent_vertices.each do |adjacent_vertex|
        next if visited_vertices[adjacent_vertex.value]
        visited_vertices[adjacent_vertex.value] = true
        queue.enqueue(adjacent_vertex)
      end
    end

    return nil
  end
end

vertex_a = Vertex.new('a')
vertex_b = Vertex.new('b')
vertex_c = Vertex.new('c')
vertex_d = Vertex.new('d')
vertex_e = Vertex.new('e')
vertex_f = Vertex.new('f')
vertex_g = Vertex.new('g')

vertex_a.adjacent_vertices = [vertex_b, vertex_c, vertex_d]
vertex_b.adjacent_vertices = [vertex_e, vertex_f]
vertex_c.adjacent_vertices = [vertex_g]

p vertex_a.bfs(vertex_a, 'g')
