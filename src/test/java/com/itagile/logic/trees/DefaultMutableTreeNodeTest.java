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

import static org.hamcrest.collection.IsIterableContainingInOrder.*;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests for DefaultMutableTreeNode.
 * @author Javier Alcala
 * @since 1.0
 */
public class DefaultMutableTreeNodeTest {

    /**
     * Test method for {@link com.itagile.logic.trees.DefaultMutableTreeNode#getCollection()}.
     */
    @Test
    public void testGetCollection() {
        final List<MutableTreeNode<Object>> collection = new ArrayList<>();
        final DefaultMutableTreeNode<Object> bean = new DefaultMutableTreeNode<>(null, null,
                collection);
        assertSame(collection, bean.getCollection());
    }

    /**
     * Test method for {@link com.itagile.logic.trees.DefaultMutableTreeNode#setCollection()}.
     */
    @Test
    public void testSetCollection() {
        final List<MutableTreeNode<Object>> collection = new ArrayList<>();
        final DefaultMutableTreeNode<Object> bean = new DefaultMutableTreeNode<>();
        bean.setCollection(collection);
        assertSame(collection, bean.getCollection());
    }

    /**
     * Test method for
     * {@link com.itagile.logic.trees.DefaultMutableTreeNode#add(com.itagile.logic.trees.TreeNode)}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAdd() {
        final MutableTreeNode<Object> bean = new DefaultMutableTreeNode<>();
        final MutableTreeNode<Object> child = new DefaultMutableTreeNode<>();
        bean.add(child);
        assertSame(bean, child.getParent());
        assertThat(bean.getMutableChildren(), contains(child));
    }

    /**
     * Test method for
     * {@link com.itagile.logic.trees.DefaultMutableTreeNode#add(com.itagile.logic.trees.TreeNode)}.
     */
    @Test
    public void testAddFluent() {
        final MutableTreeNode<Object> bean = new DefaultMutableTreeNode<>();
        assertSame(bean, bean.add(new DefaultMutableTreeNode<>()));
    }

    /**
     * Test method for {@link com.itagile.logic.trees.DefaultMutableTreeNode#getData()}.
     */
    @Test
    public void testGetData() {
        final String data = "test";
        final DefaultMutableTreeNode<String> bean = new DefaultMutableTreeNode<>(data);
        assertEquals(data, bean.getData());
    }

    /**
     * Test method for {@link com.itagile.logic.trees.DefaultMutableTreeNode#setData()}.
     */
    @Test
    public void testSetData() {
        final DefaultMutableTreeNode<Integer> bean = new DefaultMutableTreeNode<>(1);
        final Integer data = 2;
        bean.setData(data);
        assertEquals(data, bean.getData());
    }

    /**
     * Test method for {@link com.itagile.logic.trees.DefaultMutableTreeNode#getChildren()}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetChildren() {
        final MutableTreeNode<Object> bean = new DefaultMutableTreeNode<>();
        final MutableTreeNode<Object> child = new DefaultMutableTreeNode<>();
        bean.add(child);
        assertThat(bean.getChildren(), contains((TreeNode<Object>) child));
    }

    /**
     * Test method for {@link com.itagile.logic.trees.DefaultMutableTreeNode#getChildren()}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetMutableChildrenInOriginalOrder() {
        final MutableTreeNode<Object> bean = new DefaultMutableTreeNode<>();
        final MutableTreeNode<Object> child3 = new DefaultMutableTreeNode<>();
        final MutableTreeNode<Object> child2 = new DefaultMutableTreeNode<>();
        final MutableTreeNode<Object> child1 = new DefaultMutableTreeNode<>();
        bean.add(child1).add(child2).add(child3);
        assertThat(bean.getMutableChildren(), contains(child1, child2, child3));
    }

    /**
     * Test method for {@link com.itagile.logic.trees.DefaultMutableTreeNode#isLeaf()}.
     */
    @Test
    public void testIsLeafTrue() {
        final MutableTreeNode<Object> bean = new DefaultMutableTreeNode<>();
        assertTrue(bean.isLeaf());
    }

    /**
     * Test method for {@link com.itagile.logic.trees.DefaultMutableTreeNode#isLeaf()}.
     */
    @Test
    public void testIsLeafFalse() {
        final MutableTreeNode<Object> bean = new DefaultMutableTreeNode<>();
        final MutableTreeNode<Object> child = new DefaultMutableTreeNode<>();
        bean.add(child);
        assertFalse(bean.isLeaf());
    }

    /**
     * Test method for {@link com.itagile.logic.trees.DefaultMutableTreeNode#getParent()}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetParent() {
        final MutableTreeNode<Object> bean = new DefaultMutableTreeNode<>();
        final MutableTreeNode<Object> child = new DefaultMutableTreeNode<>(null, bean);
        assertEquals(bean, child.getParent());
        assertThat(bean.getMutableChildren(), contains(child));
    }

    /**
     * Test method for {@link com.itagile.logic.trees.DefaultMutableTreeNode#setParent()}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSetParentNewNode() {
        final MutableTreeNode<Object> bean = new DefaultMutableTreeNode<>();
        final MutableTreeNode<Object> child = new DefaultMutableTreeNode<>();
        child.setParent(bean);
        assertEquals(bean, child.getParent());
        assertThat(bean.getMutableChildren(), contains(child));
    }

    /**
     * Test method for {@link com.itagile.logic.trees.DefaultMutableTreeNode#setParent()}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testSetParentChange() {
        final MutableTreeNode<Object> bean1 = new DefaultMutableTreeNode<>();
        final MutableTreeNode<Object> bean2 = new DefaultMutableTreeNode<>();
        final MutableTreeNode<Object> child = new DefaultMutableTreeNode<>(null, bean1);
        child.setParent(bean2);
        assertEquals(bean2, child.getParent());
        assertThat(bean2.getMutableChildren(), contains(child));
        assertTrue(bean1.isLeaf());
    }

    /**
     * Test method for {@link com.itagile.logic.trees.DefaultMutableTreeNode#setParent()}.
     */
    @Test
    public void testSetParentRemove() {
        final MutableTreeNode<Object> bean = new DefaultMutableTreeNode<>();
        final MutableTreeNode<Object> child = new DefaultMutableTreeNode<>(null, bean);
        child.setParent(null);
        assertTrue(child.isLeaf());
        assertTrue(bean.isLeaf());
    }

    /**
     * Test method for {@link com.itagile.logic.trees.DefaultMutableTreeNode#removeFromParent()}.
     */
    @Test
    public void testRemoveFromParent() {
        final MutableTreeNode<Object> bean = new DefaultMutableTreeNode<>();
        final MutableTreeNode<Object> child = new DefaultMutableTreeNode<>(null, bean);
        child.removeFromParent();
        assertNull(child.getParent());
        assertTrue(bean.isLeaf());
    }

    /**
     * Test method for {@link com.itagile.logic.trees.DefaultMutableTreeNode#remove()}.
     */
    @Test
    public void testRemoveFalse() {
        final MutableTreeNode<Object> bean = new DefaultMutableTreeNode<>();
        final MutableTreeNode<Object> child = new DefaultMutableTreeNode<>();
        assertFalse(bean.remove(child));
    }

    /**
     * Test method for {@link com.itagile.logic.trees.DefaultMutableTreeNode#remove()}.
     */
    @Test
    public void testRemoveTrue() {
        final MutableTreeNode<Object> bean = new DefaultMutableTreeNode<>();
        final MutableTreeNode<Object> child = new DefaultMutableTreeNode<>(null, bean);
        assertTrue(bean.remove(child));
    }

    /**
     * Test for JSON serialization.
     * @throws JsonProcessingException fatal error on serialization
     * @throws JSONException fatal error on JSONAssert
     */
    @Test
    public void testJsonSerialization() throws JsonProcessingException, JSONException {
        final MutableTreeNode<String> root = new DefaultMutableTreeNode<>("root")
                .add(new DefaultMutableTreeNode<>("A")).add(new DefaultMutableTreeNode<>("B"));

        final ObjectMapper mapper = new ObjectMapper();
        final String json = mapper.writeValueAsString(root);
        final String expected = "{\"data\":\"root\",\"leaf\":false,\"children\":["
                + "{\"data\":\"A\",\"leaf\":true,\"children\":[]},"
                + "{\"data\":\"B\",\"leaf\":true,\"children\":[]}]}";
        JSONAssert.assertEquals(expected, json, false);
    }

}
