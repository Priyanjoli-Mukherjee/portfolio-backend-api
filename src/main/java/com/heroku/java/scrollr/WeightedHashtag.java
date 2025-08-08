package com.heroku.java.scrollr;

public class WeightedHashtag {
    private String tag;
    private int weight;

    public String getTag() {
        return tag;
    }

    public WeightedHashtag setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public WeightedHashtag setWeight(int weight) {
        this.weight = weight;
        return this;
    }
}
