package com.narxoz.rpg.artifact;


public abstract class Artifact {

    private final String name;
    private final int value;
    private final int weight;

    protected Artifact(String name, int value, int weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public abstract void accept(ArtifactVisitor visitor);
}
