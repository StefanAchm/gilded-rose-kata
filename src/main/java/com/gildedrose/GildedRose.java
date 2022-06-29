package com.gildedrose;

import java.util.List;

class GildedRose {

    private static final int MIN_QUALITY = 0;
    private static final int MAX_QUALITY = 50;

    private static final String ITEM_BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String ITEM_SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String ITEM_AGED_BRIE = "Aged Brie";

    List<Item> items;

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void update() {

        items
            .stream()
            .filter(item -> !item.getName().contains(ITEM_SULFURAS))
            .forEach(item -> {

                updateQualityOfItem(item);
                updateSellInOfItem(item);

            });

    }

    private void updateQualityOfItem(Item item) {

        int qualityChange = getQualityChangeDirection(item) * getQualityChangeAmount(item);

        int newQuality = item.getQuality() + qualityChange;

        newQuality = Math.max(MIN_QUALITY, newQuality);

        newQuality = Math.min(MAX_QUALITY, newQuality);

        item.setQuality(newQuality);

    }
    private void updateSellInOfItem(Item item) {

        item.setSellIn(item.getSellIn() - 1);

    }

    private int getQualityChangeAmount(Item item) {

        if(item.getName().equals(ITEM_BACKSTAGE_PASSES)) {

            if (item.getSellIn() <= 0) {
                return item.getQuality();
            }

            if(item.getSellIn() <= 5) {
                return 3;
            }

            if(item.getSellIn() <= 10) {
                return 2;
            }

        } else {

            if(item.getSellIn() <= 0) {
                return 2;
            }

        }

        return 1; // default

    }

    private int getQualityChangeDirection(Item item) {

        if(item.getName().equals(ITEM_AGED_BRIE)) {
            return 1;
        }

        if(item.getName().equals(ITEM_BACKSTAGE_PASSES)) {

            if(item.getSellIn() <= 0) {

                return -1;

            } else {

                return  1;

            }

        }

        return -1; // default

    }




}
