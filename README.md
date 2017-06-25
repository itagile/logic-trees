# IT Agile Logic Trees
Generic Java tree data structures, builders and models

# Introduction

IT Agile Logic Trees brings utilities for generating tree objects. Code is not limited to a specific type of data like String, it could be any object you need, like a menu object with properties or indicators arranged for group aggregation.

# Example

The example below shows how to use MutableTreeNode to construct tree nodes and how to add children to each node.

```
import com.itagile.logic.trees.DefaultMutableTreeNode;
import com.itagile.logic.trees.MutableTreeNode;
import com.itagile.logic.trees.TreeNode;

...

    private static void printNode(TreeNode<String> node) {
        System.out.println(node.getData());
        for (TreeNode<String> child : node.getChildren()) {
            printNode(child);
        }
    }

    public static void main(String[] args) {
    	MutableTreeNode<String> root = new DefaultMutableTreeNode<>("root");
    	MutableTreeNode<String> a = new DefaultMutableTreeNode<>("A");
    	MutableTreeNode<String> b = new DefaultMutableTreeNode<>("B");
        root.add(a).add(b);
        a.add(new DefaultMutableTreeNode<>("A.2"))
         .add(new DefaultMutableTreeNode<>("A.1"));
        b.add(new DefaultMutableTreeNode<>("B.1"))
         .add(new DefaultMutableTreeNode<>("B.2"));
        printNode(root);
    }
```

This example prints:

```
    root
    A
    A.2
    A.1
    B
    B.1
    B.2
```

# Development
## Maven
This project uses [Apache Maven](http://maven.apache.org/) as a build tool.  The convention for version numbers is major.minor.patch as stated by [SemVer 2.0](http://semver.org/). Under development code is marked with SNAPSHOT following maven standard.

## Git branching
Developed code adheres to the set of branching rules defined by [OneFlow - a Git branching model and workflow](http://endoflineblog.com/oneflow-a-git-branching-model-and-workflow)
