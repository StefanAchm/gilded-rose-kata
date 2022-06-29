package com.gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.stream.IntStream;

class GildedRoseTest {

    @Test
    @DisplayName("Quality decreases once on every day passed")
    void testNormal() {

        GildedRose app = new GildedRose(Collections.singletonList(new Item("foo", 10, 20)));

        IntStream.range(0, 5).forEach(i -> app.update());

        Assertions.assertEquals("foo", app.items.get(0).getName());
        Assertions.assertEquals(5, app.items.get(0).getSellIn());
        Assertions.assertEquals(15, app.items.get(0).getQuality());

    }

    @Test
    @DisplayName("Once the sell by date has passed, Quality degrades twice as fast")
    void testSellByDateHasPassed() {

        GildedRose app = new GildedRose(Collections.singletonList(new Item("foo", 10, 20)));

        IntStream.range(0, 12).forEach(i -> app.update());

        Assertions.assertEquals("foo", app.items.get(0).getName());
        Assertions.assertEquals(-2, app.items.get(0).getSellIn());
        Assertions.assertEquals(6, app.items.get(0).getQuality());

    }

    @Test
    @DisplayName("The Quality of an item is never negative")
    void testNegativeQuality() {

        GildedRose app = new GildedRose(Collections.singletonList(new Item("foo", 10, 5)));

        IntStream.range(0, 10).forEach(i -> app.update());

        Assertions.assertEquals("foo", app.items.get(0).getName());
        Assertions.assertEquals(0, app.items.get(0).getSellIn());
        Assertions.assertEquals(0, app.items.get(0).getQuality());

    }

    @Test
    @DisplayName("'Aged Brie' actually increases in Quality the older it gets")
    void testAgedBrie() {

        GildedRose app = new GildedRose(Collections.singletonList(new Item("Aged Brie", 10, 5)));

        Assertions.assertEquals("Aged Brie", app.items.get(0).getName());

        IntStream.range(0, 5).forEach(i -> app.update());

        Assertions.assertEquals(5, app.items.get(0).getSellIn());
        Assertions.assertEquals(10, app.items.get(0).getQuality());

        IntStream.range(0, 5).forEach(i -> app.update());

        Assertions.assertEquals(0, app.items.get(0).getSellIn());
        Assertions.assertEquals(15, app.items.get(0).getQuality());

        app.update();

        Assertions.assertEquals(-1, app.items.get(0).getSellIn());
        Assertions.assertEquals(17, app.items.get(0).getQuality());

    }

    @Test
    @DisplayName("The Quality of an item is never more than 50")
    void testQualityOfItemMax50() {

        GildedRose app = new GildedRose(Collections.singletonList(new Item("Aged Brie", 10, 49)));

        IntStream.range(0, 5).forEach(i -> app.update());

        Assertions.assertEquals("Aged Brie", app.items.get(0).getName());
        Assertions.assertEquals(5, app.items.get(0).getSellIn());
        Assertions.assertEquals(50, app.items.get(0).getQuality());

    }

    @Test
    @DisplayName("'Sulfuras', being a legendary item, never has to be sold or decreases in Quality")
    void testSulfuras() {

        GildedRose app = new GildedRose(Collections.singletonList(new Item("Sulfuras, Hand of Ragnaros", 0, 80)));

        IntStream.range(0, 5).forEach(i -> app.update());

        Assertions.assertEquals("Sulfuras, Hand of Ragnaros", app.items.get(0).getName());
        Assertions.assertEquals(0, app.items.get(0).getSellIn());
        Assertions.assertEquals(80, app.items.get(0).getQuality());

    }

    @Test
    @DisplayName("'Backstage passes', like aged brie, increases in Quality as it’s SellIn value approaches; Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but Quality drops to 0 after the concert")
    void testBackstagePasses() {

        GildedRose app = new GildedRose(Collections.singletonList(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 10)));

        Assertions.assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items.get(0).getName());

        IntStream.range(0, 5).forEach(i -> app.update());

        Assertions.assertEquals(10, app.items.get(0).getSellIn());
        Assertions.assertEquals(15, app.items.get(0).getQuality());

        IntStream.range(0, 5).forEach(i -> app.update());

        Assertions.assertEquals(5, app.items.get(0).getSellIn());
        Assertions.assertEquals(25, app.items.get(0).getQuality());

        IntStream.range(0, 5).forEach(i -> app.update());

        Assertions.assertEquals(0, app.items.get(0).getSellIn());
        Assertions.assertEquals(40, app.items.get(0).getQuality());

        app.update();

        Assertions.assertEquals(-1, app.items.get(0).getSellIn());
        Assertions.assertEquals(0, app.items.get(0).getQuality());

    }

}
