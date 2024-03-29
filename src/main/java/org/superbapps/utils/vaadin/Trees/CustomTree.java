/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superbapps.utils.vaadin.Trees;

import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.Tree;
import com.vaadin.v7.ui.VerticalLayout;
import org.superbapps.utils.vaadin.Exceptions.CustomTreeNodesEmptyException;
import java.util.ArrayList;
import java.util.List;
import static org.superbapps.utils.vaadin.MyWindows.WindowFormProp.WINDOW_HEIGHT_DEFAULT_NORM;
import static org.superbapps.utils.vaadin.MyWindows.WindowFormProp.WINDOW_WIDTH_DEFAULT_NORM;

/**
 *
 * @author д06ри
 * @param <T>
 */
public class CustomTree<T> extends Tree {

    protected VerticalLayout propPanel;
    protected List<?> propTrees;

    // Calling form : form caption and (right) property panel
    protected String winFormCaption;
    protected Panel winFormPropPanel;

    // Calling form : (left) image parameters...
    protected String winFormImagePath;
    protected int winFormImgWidth;
    protected int winFormImgHeight;

    // Calling form : height and width
    protected int winFormHeight;
    protected int winFormWidth;

    protected boolean readOnly = true;

    protected boolean expandRootNodes = false;

    /**
     * <b>elements</b> - List of the root nodes elements for this Custom tree.
     */
    protected List<T> elements;

    private void init(String caption) {
        setCaption(caption);
        elements = new ArrayList<>();

        if (items.size() > 0) {
            items.removeAllItems();
        }

        winFormHeight = WINDOW_HEIGHT_DEFAULT_NORM;
        winFormWidth = WINDOW_WIDTH_DEFAULT_NORM;

        propPanel = new VerticalLayout();
        propPanel.setMargin(true);
        propTrees = new ArrayList<>();
    }

    public CustomTree(String caption) {
        init(caption);
    }

    public CustomTree(String caption, boolean expandRootNodes) {
        this.expandRootNodes = expandRootNodes;
        init(caption);
    }

    /**
     * Custom tree creation with treeItems as root nodes list.
     *
     * @param caption Tree caption
     * @param rootItems root nodes list
     * @throws org.superbapps.utils.vaadin.Exceptions.CustomTreeNodesEmptyException
     */
    public CustomTree(String caption, List<T> rootItems) throws CustomTreeNodesEmptyException, NullPointerException {
        this(caption, rootItems, false);
    }

    public CustomTree(String caption, List<T> rootItems, boolean expandRootNodes) throws CustomTreeNodesEmptyException, NullPointerException {
        this.expandRootNodes = expandRootNodes;
        createRoots(caption, rootItems);
    }

    protected final void createRoots(String caption, List<T> rootItems) throws UnsupportedOperationException, CustomTreeNodesEmptyException, NullPointerException {
        if (rootItems == null) {
            throw new NullPointerException();
        }

        if (rootItems.isEmpty()) {
            throw new CustomTreeNodesEmptyException();
        }

        init(caption);
        addItems(rootItems);

        elements.clear();
        elements.addAll(rootItems.subList(0, rootItems.size()));
    }

    /**
     * <p>
     * Create this tree with nodes from the supplied bean container.</p>
     * Very useful in the cases where real-time updates are needed in the UI.
     *
     * @param caption
     * @param container BeanContainer
     * @throws org.superbapps.utils.vaadin.Exceptions.CustomTreeNodesEmptyException
     */
    public CustomTree(String caption, BeanItemContainer<T> container) throws CustomTreeNodesEmptyException, NullPointerException {
        if (container == null) {
            throw new RuntimeException("Container does not exist.");
        }

        if (container.size() <= 0) {
            throw new CustomTreeNodesEmptyException();
        }

        init(caption);
        super.setContainerDataSource(container);

        elements.clear();
        elements.addAll(container.getItemIds());
    }

    /**
     * <b>Root nodes sub-nodes creation.</b>
     *
     * @param rootNode root node.
     * @param rootNodeChildItemsList List of the sub nodes for the root node.
     * @param expandRootNodes
     */
    protected void setNodeItems(Object rootNode, List<?> rootNodeChildItemsList, boolean expandRootNodes) {
        for (Object childItem : rootNodeChildItemsList) {
            if (this.containsId(rootNode)) {
                addItem(childItem);
                setParent(childItem, rootNode);
                setChildrenAllowed(childItem, false);
            }
        }

        if (expandRootNodes) {
            expandItem(rootNode);
        }
    }

    protected void setNodeItems(Object rootNode, List<?> rootNodeChildItemsList) {
        this.setNodeItems(false, rootNodeChildItemsList, readOnly);
    }

}
