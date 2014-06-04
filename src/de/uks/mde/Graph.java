package de.uks.mde;

public class Graph {
	
	private NodeSet nodes = new NodeSet();
	private long maxId = 0;

	public Node createNode(String nodeLabel){
		Node node = new Node(++maxId);
		node.setLabel(nodeLabel);
		nodes.add(node);
		return node;
	}
	
	public void createEdge(Node source, String edgeLable, Node target){
		source.createOutgoingEdge(edgeLable, target);
	}

	public NodeSet getNodes() {
		return nodes;
	}

	public void removeNode(Node node) {
		for(String edgeLabel: node.getOutgoingLinks().keySet()){
			node.removeAllOutgoingEdges(edgeLabel);
		}

		for(String edgeLabel: node.getIncomingLinks().keySet()){
			node.removeAllIncomingEdges(edgeLabel);
		}
		
		nodes.remove(nodes);		
	}
}
