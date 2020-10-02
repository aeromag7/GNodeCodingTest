package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {

    public ArrayList walkGraph(GNode node){
        // used to store every node (excluding duplicates) in the graph
        ArrayList<GNode> nodes = new ArrayList<>();
        // this will be used to keep track of nodes that have already been visited to prevent duplicates
        HashMap<String, GNode> visited = new HashMap<>();
        walkNode(node, nodes, visited);
        return nodes;
    }

    // function for traversing the graph using depth first search (DFS)
    private void walkNode(GNode node, List<GNode> nodes, HashMap<String, GNode> visited){
        // checks for duplicates and null nodes
        if(node == null || visited.get(node.getName())!=null){
            return;
        }
        // add the current node to the list and and the map to prevent duplicates
        nodes.add(node);
        visited.put(node.getName(), node);
        // visits the current node's children
        for(GNode n : node.getChildren()){
            // recursion is used to traverse the graph
            walkNode(n, nodes, visited);
        }
    }

    public ArrayList<ArrayList<GNode>> paths(GNode node){
        // this will be used to keep track of nodes that have already been visited to prevent duplicate paths
        HashMap<String, GNode> visited = new HashMap<>();
        ArrayList<ArrayList<GNode>> nodes = new ArrayList<>();
        nodes.addAll(walkPaths(node, visited));
        // print the paths for the node used as the parameter
        printPaths(node, nodes);
        return nodes;
    }

    private ArrayList<ArrayList<GNode>> walkPaths(GNode node, HashMap<String, GNode> visited){
        // checks for duplicates and null nodes
        if(node == null || visited.get(node.getName())!=null){
            return new ArrayList<>();
        }
        visited.put(node.getName(), node);
        ArrayList<ArrayList<GNode>> paths = new ArrayList<>();
        // if a node has no children, then that's the end of the path
        if(node.getChildren().length==0){
            // another arraylist is created because its an array list that contains an array list
            // and this one is used to add the leaf nodes (nodes with no children)
            ArrayList<GNode> temp = new ArrayList<>();
            temp.add(node);
            paths.add(temp);
        }
        else{
            // visit the children to get more paths
            for (GNode n : node.getChildren()){
                ArrayList<ArrayList<GNode>> currentPaths = new ArrayList<>();
                // recursion used for depth first traversal and addAll() is used because we're adding an arraylist to an arraylist
                currentPaths.addAll(walkPaths(n, visited));
                for (ArrayList<GNode> children : currentPaths){
                    // add paths from the children of the node in the current path
                    ArrayList<GNode> temp = new ArrayList<>();
                    temp.add(node);
                    temp.addAll(children);
                    paths.add(temp);
                }
            }
        }
        return paths;
    }

    // this will ONLY print the path(s) of the INITIAL node that was passed to paths()
    // if the root node is passed in then only the paths from the root/starting node will be shown
    // if you want to see paths from the other non-root/non-starting nodes, use them as the initial parameter when executing paths()
    private void printPaths(GNode node, ArrayList<ArrayList<GNode>> paths){
        System.out.print("paths(" + node + ") = ( ");
        for (ArrayList<GNode> list : paths){
            for (GNode n : list) {
                System.out.print("(" + n + ")");
            }
            System.out.print(" ");
        }
        System.out.print(")");
    }
}
