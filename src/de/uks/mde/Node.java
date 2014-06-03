package de.uks.mde;

import java.util.LinkedHashMap;

public class Node {

	public Node(long id){
		this.id = id;
	}
	private long id;
	private String label;
	private LinkedHashMap<String, Object> attributes;
	private LinkedHashMap<String, NodeSet> outgoingLinks;
	private LinkedHashMap<String, NodeSet> incomingLinks;
	
	public void createOutgoingEdge(String edgeLabel, Node target) {
		if(outgoingLinks == null){
			outgoingLinks = new LinkedHashMap<String, NodeSet>();
		}
		NodeSet nodeSet = outgoingLinks.get(edgeLabel);
		if(nodeSet == null){
			nodeSet = new NodeSet();
			outgoingLinks.put(edgeLabel, nodeSet);
		}
		
		boolean add = nodeSet.add(target);
		
		if(add){
			target.createIncomingEdge(edgeLabel, this);
		}
	}
	
	public void createIncomingEdge(String edgeLabel, Node target) {
		if(incomingLinks == null){
			incomingLinks = new LinkedHashMap<String, NodeSet>();
		}
		NodeSet nodeSet = incomingLinks.get(edgeLabel);
		if(nodeSet == null){
			nodeSet = new NodeSet();
			incomingLinks.put(edgeLabel, nodeSet);
		}
		
		boolean add = nodeSet.add(target);
		
		if(add){
			target.createOutgoingEdge(edgeLabel, this);
		}
	}

	public void setLabel(String nodeLabel) {
		this.label = nodeLabel;
	}
	
	public Node withLabel(String label){
		setLabel(label);
		return this;
	}

	public Node withAttribute(String attributeName, Object value) {
		if(attributes == null){
			attributes = new LinkedHashMap<String, Object>();
		}
		this.attributes.put(attributeName, value);
		return this;
	}
}
