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
end
