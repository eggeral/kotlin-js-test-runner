package container

@native("inspect") fun inspect(obj: Any) {
    noImpl
}

class LinkedList<TValue> : Iterable<TValue> {
    data class Node<TValue>(val value: TValue, val next: Node<TValue>?)

    private var first: Node<TValue>? = null;

    fun add(value: TValue) {
        val node = Node(value, first)
        inspect(node)
        first = node
    }

    override fun iterator(): Iterator<TValue> {
        class Itr(var current: Node<TValue>?) : Iterator<TValue> {

            override fun hasNext(): Boolean {
                return current != null
            }

            override fun next(): TValue {
                val tmp = current ?: throw IllegalStateException("Next called but we do not have any elements.")
                current = tmp.next
                return tmp.value
            }
        }
        return Itr(first)
    }

}
