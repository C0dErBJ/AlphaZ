package com.alphaz.infrastructure.domain.model.treeentity

import com.alphaz.infrastructure.domain.ValueObject


/**
 *@Author: c0der
 *@Date: 下午7:35 2018/3/29
 *@Description:
 */
open class TreeValue<C : BaseTreeEntity<*>> : ValueObject {
    var currentNode: C? = null;
    var childNodes: MutableSet<TreeValue<C>> = mutableSetOf();

    fun searchNode(id: Long, node: TreeValue<C>): C? {
        if (node.currentNode != null && node.currentNode!!.id == id) {
            return currentNode!!;
        }
        for (index in node.childNodes) {

            if (index.childNodes.size > 0) {
                searchNode(index.currentNode!!.id!!, index)
            }
        }

        return null;
    }
    fun addChildNode(node: C, tree: TreeValue<C>) {
        for (index in tree.childNodes) {
            if (index.currentNode!!.id == node.parentId) {
                val newNode = TreeValue<C>()
                newNode.currentNode = node
                tree.childNodes.add(newNode)
                break;
            } else {
                if (tree.childNodes.any()) {
                    addChildNode(node, index)
                }
            }
        }
    }

}