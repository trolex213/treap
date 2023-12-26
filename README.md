# Treap
Implementation of the Treap data structure utilizing a self-balancing BST mixed with a heap priority to organize and search ordered data. 

## What is a Treap? 
A treap is a binary search tree (BST) which additionally maintains heap priorities. An example is given in Figure 1. A node consists of:

• A key k (given by the letter in the example),
• A random heap priority p (given by the number in the example). The heap priority p
is assigned at random upon insertion of a node. It should be unique in the treap.
• A pointer to the left child and to the right child node.

For example, the root node in the treap in Figure 1 has “h” as key and 9 as heap priority.

![treap](https://github.com/trolex213/treap/assets/65372052/cb560301-00e8-4419-ba0c-4f1512934976)


## Basic Operations of the Treap:
• Supports core BST operations - adding nodes, deleting nodes by trickling down to leaf and setting to null, finding/searching nodes.
Maintains heap property through left and right rotations when adding or removing nodes, based on priority comparisons.

• Includes helper methods for rotations, deleting nodes, traversing the tree.

• Implements comparable generic typing for the data elements.

• Provides a toString() method to print out the tree structure for testing/visualization.
