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

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

/**
 * A {@link MutableTreeNode<T>} generic implementation. The specific type of the collection could be
 * changed by derived classes. By default uses LinkedHashSet<T> in order to guarantee initial
 * insertion order for getChildren. This internal collection can be changed by derived classes using
 * the protected constructor or the setCollection method. This class can be serialized to JSON but
 * is not intended for JSON deserialization.
 *
 * @author Javier Alcala
 * @param <T> the type of data object in this node
 * @since 1.0
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class DefaultMutableTreeNode<T> implements MutableTreeNode<T> {
    /**
     * This node's parent, or null if this node has no parent.
     */
    private MutableTreeNode<T> parent;

    /**
     * Data object in this node.
     */
    private T data;

    /**
     * Collection of node's child nodes.
     */
    private Collection<MutableTreeNode<T>> collection;

    /**
     * Unmodifiable view of node's child nodes.
     */
    private Iterable<TreeNode<T>> unmodifiableChildren;

    /**
     * Unmodifiable view of node's child nodes.
     */
    private Iterable<MutableTreeNode<T>> unmodifiableMutableChildren;

    /**
     * Constructs a new, empty root node with no data.
     */
    public DefaultMutableTreeNode() {
        this(null, null);
    }

    /**
     * Constructs a new, empty root node.
     * @param data the data object in this node
     */
    public DefaultMutableTreeNode(final T data) {
        this(data, null);
    }

    /**
     * Constructs a new, empty node with parent.
     * @param data the data object in this node
     * @param parent this node's parent TreeNode, or null if this node has no parent
     */
    public DefaultMutableTreeNode(final T data, final MutableTreeNode<T> parent) {
        this(data, parent, new LinkedHashSet<MutableTreeNode<T>>());
    }

    /**
     * Constructs a new, empty node with parent. Sets an specific initial collection and parent
     * node.
     * @param data the data object in this node
     * @param parent this node's parent TreeNode, or null if this node has no parent
     * @param collection the collection of node's child nodes
     */
    protected DefaultMutableTreeNode(final T data, final MutableTreeNode<T> parent,
            final Collection<MutableTreeNode<T>> collection) {
        this.data = data;
        setCollection(collection);
        setParent(parent);
    }

    /**
     * Gets the collection of node's child nodes.
     *
     * @return the collection of node's child nodes
     */
    protected final Collection<MutableTreeNode<T>> getCollection() {
        return collection;
    }

    /**
     * Sets the collection of node's child nodes.
     *
     * @param collection the new collection of node's child nodes
     */
    protected final void setCollection(final Collection<MutableTreeNode<T>> collection) {
        this.unmodifiableChildren = Collections
                .unmodifiableCollection((Collection<? extends TreeNode<T>>) collection);
        this.unmodifiableMutableChildren = Collections.unmodifiableCollection(collection);
        this.collection = collection;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.itagile.logic.trees.MutableTreeNode#add()
     */
    @Override
    public final MutableTreeNode<T> add(final MutableTreeNode<T> newChild) {
        if (!collection.contains(newChild)) {
            collection.add(newChild);
            if (newChild.getParent() != this) {
                newChild.setParent(this);
            }
        }
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.itagile.logic.trees.TreeNode#getData()
     */
    @Override
    public final T getData() {
        return data;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.itagile.logic.trees.MutableTreeNode#setData()
     */
    @Override
    public final MutableTreeNode<T> setData(final T newData) {
        this.data = newData;
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.itagile.logic.trees.TreeNode#getChildren()
     */
    @Override
    public final Iterable<TreeNode<T>> getChildren() {
        return unmodifiableChildren;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.itagile.logic.trees.MutableTreeNode#getMutableChildren()
     */
    @Override
    public final Iterable<MutableTreeNode<T>> getMutableChildren() {
        return unmodifiableMutableChildren;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.itagile.logic.trees.TreeNode#isLeaf()
     */
    @Override
    public final boolean isLeaf() {
        return collection.isEmpty();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.itagile.logic.trees.TreeNode#getParent()
     */
    @Override
    public final TreeNode<T> getParent() {
        return parent;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.itagile.logic.trees.MutableTreeNode#setParent(com.itagile.logic.trees.MutableTreeNode)
     */
    @Override
    public final MutableTreeNode<T> setParent(final MutableTreeNode<T> newParent) {
        if (newParent != this.parent) {
            removeFromParent();
            this.parent = newParent;
            if (newParent != null) {
                newParent.add(this);
            }
        }
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.itagile.logic.trees.MutableTreeNode#removeFromParent()
     */
    @Override
    public final void removeFromParent() {
        if (this.parent != null) {
            final MutableTreeNode<T> oldParent = this.parent;
            this.parent = null;
            oldParent.remove(this);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.itagile.logic.trees.MutableTreeNode#remove(com.itagile.logic.trees.MutableTreeNode)
     */
    @Override
    public final boolean remove(final MutableTreeNode<T> child) {
        final boolean removed = collection.remove(child);
        if (removed) {
            child.removeFromParent();
        }
        return removed;
    }

}
