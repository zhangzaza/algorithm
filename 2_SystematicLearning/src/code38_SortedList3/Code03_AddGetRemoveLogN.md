## `remove` 方法的时间复杂度如下：

### ArrayList
- **`remove(index)`**: 时间复杂度是 **O(n)**，因为在删除指定索引的元素时，可能需要将该索引之后的所有元素向左移动一位以填补空缺。
- **`remove(object)`**: 时间复杂度也是 **O(n)**，因为需要先遍历整个列表以找到该对象，然后执行删除操作。

### LinkedList
- **`remove(index)`**: 时间复杂度是 **O(n)**，因为需要从头遍历链表以找到指定索引的节点。
- **`remove(object)`**: 时间复杂度是 **O(n)**，因为也需要遍历整个链表以查找该对象。

### 总结
- 对于 `ArrayList` 和 `LinkedList`，删除操作的时间复杂度通常是 **O(n)**，主要原因是需要遍历链表或移动数组中的元素。
- `ArrayList` 在随机访问方面更快（**O(1)**），而 `LinkedList` 在插入和删除操作上更为灵活，尤其是在列表的头部或尾部执行时（**O(1)**）。

## 实现一个数据结构，`add` `remove` `get` 时间复杂度都是logN
看图片
```java
    public static class SBTNode<V>{
        public V value;
        public SBTNode<V> left;
        public SBTNode<V> right;
        public int size;
        public SBTNode(V value) {
            this.value = value;
            size=1;
        }
    }
```
    