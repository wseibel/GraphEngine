package de.uks.mde;

import junit.framework.Assert;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class FerryManTest {
	@Test
	public void testBuildFerryManGraph(){
		Graph graph = new Graph();
		
		Node wolf = graph.createNode("Wolf");
		Node leftBank = graph.createNode("Bank").withAttribute("side", "left");
		Node rightBank = graph.createNode("Bank").withAttribute("side", "right");
		
		wolf.createOutgoingEdge("at", leftBank);
		
		Node goat = graph.createNode("Goat");
		goat.createOutgoingEdge("at", leftBank);
		
		Node cabbage = graph.createNode("Cabbage");
		cabbage.createOutgoingEdge("at", leftBank);
		
		Node boat = graph.createNode("Boat");
		boat.createOutgoingEdge("at", leftBank);
		
		wolf.removeOutgoingEdge("at", leftBank);	
		wolf.createOutgoingEdge("at", boat);
		
		boat.removeOutgoingEdge("at", leftBank);
		
		graph.removeNode(cabbage);
		
		int size = graph.getNodes().size();
		Assert.assertTrue("Graph should have nodes:  3" + size, size == 3);
	}
}
