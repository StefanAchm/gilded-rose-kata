package com.gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemTest {

    @Test
    final void testToString() {

        Item item = new Item("foo", 5, 10);

        Assertions.assertEquals("foo, 5, 10", item.toString());

    }

}
