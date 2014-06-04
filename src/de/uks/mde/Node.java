package de.uks.mde;

import java.util.LinkedHashMap;

public class Node {

	public Node(long id) {
		this.id = id;
	}

	private long id;
	private String label;
	public String getLabel() {
		return label;
	}

	public void setLabel(String nodeLabel) {
		this.label = nodeLabel;
	}

	private LinkedHashMap<String, Object> attributes;
	private LinkedHashMap<String, NodeSet> outgoingLinks;
	private LinkedHashMap<String, NodeSet> incomingLinks;
	
	public LinkedHashMap<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(LinkedHashMap<String, Object> attributes) {
		this.attributes = attributes;
	}

	public LinkedHashMap<String, NodeSet> getOutgoingLinks() {
		return outgoingLinks;
	}

	public void setOutgoingLinks(LinkedHashMap<String, NodeSet> outgoingLinks) {
		this.outgoingLinks = outgoingLinks;
	}

	public LinkedHashMap<String, NodeSet> getIncomingLinks() {
		return incomingLinks;
	}

	public void setIncomingLinks(LinkedHashMap<String, NodeSet> incomingLinks) {
		this.incomingLinks = incomingLinks;
	}

	public void createOutgoingEdge(String edgeLabel, Node target) {
		if (outgoingLinks == null) {
			outgoingLinks = new LinkedHashMap<String, NodeSet>();
		}
		NodeSet nodeSet = outgoingLinks.get(edgeLabel);
		if (nodeSet == null) {
			nodeSet = new NodeSet();
			outgoingLinks.put(edgeLabel, nodeSet);
		}

		boolean add = nodeSet.add(target);

		if (add) {
			target.createIncomingEdge(edgeLabel, this);
		}
	}

	public void createIncomingEdge(String edgeLabel, Node target) {
		if (incomingLinks == null) {
			incomingLinks = new LinkedHashMap<String, NodeSet>();
		}
		NodeSet nodeSet = incomingLinks.get(edgeLabel);
		if (nodeSet == null) {
			nodeSet = new NodeSet();
			incomingLinks.put(edgeLabel, nodeSet);
		}

		boolean add = nodeSet.add(target);

		if (add) {
			target.createOutgoingEdge(edgeLabel, this);
		}
	}

	public Node withLabel(String label) {
		setLabel(label);
		return this;
	}

	public Node withAttribute(String attributeName, Object value) {
		if (attributes == null) {
			attributes = new LinkedHashMap<String, Object>();
		}
		this.attributes.put(attributeName, value);
		return this;
	}

	public boolean changeAttribute(String attributeName, Object value) {
		if (attributes == null) {
			return false;
		}
		this.attributes.put(attributeName, value);
		return true;
	}

	public void removeOutgoingEdge(String edgeLabel, Node target) {
		if(outgoingLinks == null){
			return;
		}
		NodeSet nodeSet = this.outgoingLinks.get(edgeLabel);

		if (nodeSet == null) {
			return;
		}

		boolean remove = nodeSet.remove(target);

		if (remove) {
			target.removeIncomingEdge(edgeLabel, this);
		}
	}

	public void removeIncomingEdge(String edgeLabel, Node source) {
		if(incomingLinks == null){
			return;
		}
		NodeSet nodeSet = this.incomingLinks.get(edgeLabel);

		if (nodeSet == null) {
			return;
		}

		boolean remove = nodeSet.remove(source);

		if (remove) {
			source.removeOutgoingEdge(edgeLabel, this);
		}
	}

	public void removeAllOutgoingEdges(String edgeLabel) {
		if(outgoingLinks == null){
			return;
		}
		NodeSet nodeSet = (NodeSet) this.getOutgoingLinks().get(edgeLabel).clone();

		for (Node neighbor : nodeSet) {
			this.removeOutgoingEdge(edgeLabel, neighbor);
		}
	}

	public void removeAllIncomingEdges(String edgeLabel) {
		if(incomingLinks == null){
			return;
		}
		NodeSet nodeSet = (NodeSet) this.getIncomingLinks().get(edgeLabel).clone();

		for (Node neighbor : nodeSet) {
			this.removeIncomingEdge(edgeLabel, neighbor);
		}		
	}
}
