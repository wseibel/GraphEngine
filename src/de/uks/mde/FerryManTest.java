package de.uks.mde;

import junit.framework.Assert;

import org.junit.Test;

public class FerryManTest {
	@Test
	public void testBuildFerryManGraph(){
		Graph graph = new Graph();
		
		Node wolf = graph.createNode("Wolf");
		Node leftBank = graph.createNode("Bank").withAttribute("side", "left");
		
		wolf.createOutgoingEdge("at", leftBank);
		
		int size = graph.getNodes().size();
		Assert.assertTrue("Graph should have two nodes: " + size, size == 2);
	}
}
