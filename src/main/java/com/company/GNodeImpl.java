package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class GNodeImpl implements GNode{

    private String name;

    private ArrayList<GNode> children;

    public GNodeImpl(String name){
        this.name = name;
        this.children = null;
    }

    public GNodeImpl(String name, GNode[] children){
        this.name = name;
        if(children==null){
            this.children = new ArrayList<>();
        }
        else{
            this.setChildren(children);
        }
    }

    @Override
    public String getName(){
        return name;
    }

    public void setChildren(GNode[] children){
        // this allows children to be added to nodes after their initialization if they initially had no children
        if(this.children==null){
            this.children = new ArrayList<>();
            this.children.addAll(Arrays.asList(children));
        }
        if(children!=null){
            this.children.addAll(Arrays.asList(children));
        }
    }

    @Override
    public GNode[] getChildren(){
        // if the node has no children, then return an empty list
        if(children==null){
            return new ArrayList<GNode>().toArray(new GNode[0]);
        }
        return children.toArray(new GNode[0]);
    }

    // overwrites the default toString() for objects to allow a node's name to be used instead of something like "com.foo.MyType@2f92e0f4"
    public String toString(){
        return name;
    }
}
