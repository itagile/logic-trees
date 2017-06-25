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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Defines methods for manipulating generic tree nodes with data content.
 * @param <T> the type of data object in this node
 * @author Javier Alcala
 * @since 1.0
 */
public interface MutableTreeNode<T> extends TreeNode<T> {
    /**
     * Returns this node's parent or null if this node has no parent.
     * @return this node's parent, or null if this node has no parent
     */
    @JsonIgnore
    TreeNode<T> getParent();

    /**
     * Sets this node's parent and add this as a child if not yet added. Node is removed from
     * original parent if it was already added.
     *
     * @param newParent the new parent node, or null if this node has no parent
     * @return this object
     */
    MutableTreeNode<T> setParent(MutableTreeNode<T> newParent);

    /**
     * Removes this node from its parent. Does nothing if parent is null.
     */
    void removeFromParent();

    /**
     * Removes item from this child collection, if it is present (optional operation). Updates
     * child's parent to null if it was removed.
     * @param child element to be removed from this collection, if present
     * @return true if an element was removed as a result of this call
     */
    boolean remove(MutableTreeNode<T> child);

    /**
     * Ensures that this collection contains the specified element (optional operation). Sets
     * child's parent to this node.
     *
     * @param newChild the child object to add
     * @return this object
     */
    MutableTreeNode<T> add(MutableTreeNode<T> newChild);

    /**
     * Sets the data object in this node.
     *
     * @param newData the new data object in this node
     * @return this object
     */
    MutableTreeNode<T> setData(T newData);

    /**
     * Gets the children of this node as an unmodifiable collection of MutableTreeNode.
     *
     * @return the children of this node, never null
     */
    @JsonIgnore
    Iterable<MutableTreeNode<T>> getMutableChildren();

}
