/*
 * Copyright 2017-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.itagile.logic.trees;

/**
 * Defines methods for accessing generic tree nodes with data content. This interface is intended
 * for unmodifiable nodes from outside of its model. Doesn't support getParent because it would
 * require mutating either the parent or child depending on the creation order.
 * @param <T> the type of data object in this node
 * @author Javier Alcala
 * @since 1.0
 */
public interface TreeNode<T> {
    /**
     * Gets the data object in this node.
     *
     * @return the data object in this node
     */
    T getData();

    /**
     * Gets the children of this node as an unmodifiable collection.
     *
     * @return the children of this node, never null
     */
    Iterable<TreeNode<T>> getChildren();

    /**
     * Checks if the node is a leaf.
     * @return true if the node is a leaf
     */
    boolean isLeaf();
}
