package com.alphaz.infrastructure.domain.model.treeentity


/**
 *@Author: c0der
 *@Date: 下午7:49 2018/3/29
 *@Description:
 */
class TreeFactory<C : BaseTreeEntity<*>, T : TreeValue<C>> {
    fun toTreeValue(list:MutableSet<C>): TreeValue<C> {
        val sortedTreeList = list.sortedBy { a -> a.layer }
        val tree = TreeValue<C>()
        for (index in sortedTreeList) {
            if (index.parentId == null) {
                tree.currentNode = index
            } else {
                tree.addChildNode(index, tree)
            }
        }
        return tree;
    }

}