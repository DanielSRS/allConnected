package br.uefs.ecomp.allconnected.util;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class GraphTest {

	@Test
	void test() {
		Graph<Integer> g = new Graph<Integer>();
		
		g.addEdge(0, 1, true); 
		g.addEdge(0, 4, true); 
		g.addEdge(1, 2, true); 
		g.addEdge(1, 3, true); 
		g.addEdge(1, 4, true); 
		g.addEdge(2, 3, true); 
		g.addEdge(3, 4, true);
		
		assertEquals("print the graph.", "Graph:\n0: 1 4 \n1: 0 2 3 4 \n2: 1 3 \n3: 1 2 4 \n4: 0 1 3 \n", "Graph:\n" + g.toString());
		assertEquals("gives the no of vertices in the graph.", "The graph has 5 vertex", g.getVertexCount());
		assertEquals("gives the no of edges in the graph.", "The graph has 7 edges.", g.getEdgesCount(true));
		assertEquals("tells whether the edge is present or not.", "The graph has an edge between 3 and 4.", g.hasEdge(3, 4));
		assertEquals("tells whether vertex is present or not", "The graph does not contain 5 as a vertex.", g.hasVertex(5));
	}

}

