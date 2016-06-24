package container

import kotlin.test.assertEquals
import org.junit.Test as spec

class LinkedListSpec {

    @spec fun valuesCanBeAddedToALinkedList() {
        // given
        val target = LinkedList<Int>()

        // when
        target.add(1)

        // then
        assertEquals(1, target.count())
    }

    @spec fun aFailingSpec() {
        assertEquals(1, 2)
    }

}