package de.uks.mde;

import junit.framework.Assert;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class TheExpandablesTest {
	@Test
	public void testTheExpandablesGraph(){
		Graph graph = new Graph();
		
		Node trench = graph.createNode("Expandable").withAttribute("time", 25);
		trench.setLabel("Trench");
		Node barney = graph.createNode("Expandable").withAttribute("time", 20);
		barney.setLabel("Barney");
		Node lee = graph.createNode("Expandable").withAttribute("time", 10);
		lee.setLabel("Lee Christmas");
		Node yin = graph.createNode("Expandable").withAttribute("time", 5);
		yin.setLabel("Yin Yang");
		
		Node nightGoggles = graph.createNode("NightGoggles").withAttribute("timeLeft", 60);
		
		Node leftBank = graph.createNode("Bank").withAttribute("side", "left");
		Node rightBank = graph.createNode("Bank").withAttribute("side", "right");
		
		trench.createOutgoingEdge("at", leftBank);
		barney.createOutgoingEdge("at", leftBank);
		lee.createOutgoingEdge("at", leftBank);
		yin.createOutgoingEdge("at", leftBank);
		
		trench.removeOutgoingEdge("at", leftBank);	
		trench.createOutgoingEdge("at", rightBank);
		
		barney.removeOutgoingEdge("at", leftBank);	
		barney.createOutgoingEdge("at", rightBank);
		
		int timeLeft = (int) nightGoggles.getAttributes().get("timeLeft");
		timeLeft = timeLeft - (int)trench.getAttributes().get("time");
		nightGoggles.changeAttribute("timeLeft", timeLeft);

		// All graph nodes created
		Assert.assertTrue("Graph should have nodes:  7", graph.getNodes().size() == 7);
		// Trench and barney at right bank
		Assert.assertTrue("Trench should be at the right bank", trench.getOutgoingLinks().get("at").contains(rightBank));
		Assert.assertTrue("Barney should be at the right bank", barney.getOutgoingLinks().get("at").contains(rightBank));
		// Trench and barney not at left bank
		Assert.assertFalse("Trench should not be at the left bank anymore", trench.getOutgoingLinks().get("at").contains(leftBank));
		Assert.assertFalse("Barney should not be at the left bank anymore", barney.getOutgoingLinks().get("at").contains(leftBank));
		// Lee and Yin at left bank
		Assert.assertTrue("Lee should be at the left bank", lee.getOutgoingLinks().get("at").contains(leftBank));
		Assert.assertTrue("Yin should be at the left bank", yin.getOutgoingLinks().get("at").contains(leftBank));
		// NightGoggles time left: 35 min
		Assert.assertTrue("Time left: 35 min", (int) nightGoggles.getAttributes().get("timeLeft") == 35);
	}

}
