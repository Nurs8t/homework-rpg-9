package com.narxoz.rpg.artifact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Inventory {

    private final List<Artifact> artifacts;

    public Inventory() {
        this.artifacts = new ArrayList<>();
    }

    public Inventory(List<Artifact> artifacts) {
        this.artifacts = artifacts == null ? new ArrayList<>() : new ArrayList<>(artifacts);
    }

    public void addArtifact(Artifact artifact) {
        if (artifact != null) {
            artifacts.add(artifact);
        }
    }

    public List<Artifact> getArtifacts() {
        return Collections.unmodifiableList(artifacts);
    }

    public int size() {
        return artifacts.size();
    }

    public void accept(ArtifactVisitor visitor) {
        for (Artifact artifact : artifacts) {
            artifact.accept(visitor);
        }
    }

    public Inventory copy() {
        return new Inventory(artifacts);
    }
}
