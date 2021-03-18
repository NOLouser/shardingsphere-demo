package com.nolouser.demo.note.structure;

import com.google.gson.Gson;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 树结构
 */
public class Node {

    private String value;

    private List<Node> childrens;

    public Node(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Node> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Node> childrens) {
        this.childrens = childrens;
    }

    /**
     * 列表元素转换为树结构
     *
     * @param tree
     * @param elements
     * @return
     */
    public static Node toTree(Node tree, List<String> elements) {
        // 空元素，无需遍历树
        if (CollectionUtils.isEmpty(elements)) {
            return tree;
        }

        // 当前节点与元素不匹配（递归的时候已经做了限制，只有当前节点与第一个元素匹配才会进入递归）
        // 作用：主要是第一次遍历的时候，根节点不匹配就直接返回了
        if (!tree.value.equals(elements.get(0))) {
            return tree;
        }

        // 只有一个元素
        if (elements.size() == 1) {
            return tree;
        }

        // 去掉当前节点的元素，生成下一个元素的节点
        elements = elements.subList(1, elements.size());

        // 子节点存在，遍历子节点，截取后elements的第一个元素在子节点上，直接修改该子节点
        for (int i = 0; tree.childrens != null && i < tree.childrens.size(); i++) {
            Node children = tree.childrens.get(i);
            if (children.value.equals(elements.get(0))) {
                Node tempNode = toTree(children, elements);
                tree.childrens.set(i, tempNode);
                return tree;
            }
        }

        // elements的第一个元素不在子节点上，直接新增该子节点
        Node children = toTree(new Node(elements.get(0)), elements);
        if (tree.childrens == null) {
            List<Node> childes = new ArrayList<>();
            childes.add(children);
            tree.childrens = childes;
        } else {
            tree.childrens.add(children);
        }

        return tree;
    }

    /**
     * 遍历树，合并节点的值
     *                   dep
     *                  ↙
     *                RPA
     *               ↙  ↘
     *             44   55                      =====>     [ "dep:RPA:44:TASK_TYPE1:1001",
     *            ↙      ↘                                   "dep:RPA:55:TASK_TYPE1:1002"]
     *       TASK_TYPE1 TASK_TYPE1
     *          ↙            ↘
     *        1001          1002
     * @param tree
     * @return
     */
    public static List<String> traverseTree(Node tree) {
        if (CollectionUtils.isEmpty(tree.childrens)) {
            return Arrays.asList(tree.value);
        }

        List<String> childrenValues = new ArrayList<>();
        for (Node children : tree.childrens) {
            childrenValues.addAll(traverseTree(children));
        }

        return childrenValues.stream().map(childrenValue -> childrenValue + ":" + tree.value).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        Node tree = new Node("head");
        tree = toTree(tree, Arrays.asList("head:RPA:44:TASK10001:10001:tail".split(":")));
        tree = toTree(tree, Arrays.asList("head:RPA:44:TASK10002:10001:tail".split(":")));
        tree = toTree(tree, Arrays.asList("head:RPA:44:TASK10003:10002:tail".split(":")));
        tree = toTree(tree, Arrays.asList("head:RPA:44:TASK10004:10002:tail".split(":")));
        tree = toTree(tree, Arrays.asList("head:RPA:54:TASK10002:10001:tail".split(":")));
        tree = toTree(tree, Arrays.asList("head:RPA:54:TASK10002:10002:tail".split(":")));
        System.out.println(gson.toJson(tree));

        List<String> trasverseResult = traverseTree(tree);
        System.out.println(gson.toJson(trasverseResult));
        Node tailTree = new Node("tail");
        for (String s : trasverseResult) {
            tailTree = toTree(tailTree, Arrays.asList(s.split(":")));
        }
        System.out.println(gson.toJson(tailTree));

    }
}
