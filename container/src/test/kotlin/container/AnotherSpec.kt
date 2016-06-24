package container

import org.junit.Test as spec
import kotlin.test.assertEquals

class AnotherSpec {
    @spec fun specA() {
        assertEquals(1, 1)
    }

    @spec fun specB() {
        assertEquals(2, 2)
    }

}

