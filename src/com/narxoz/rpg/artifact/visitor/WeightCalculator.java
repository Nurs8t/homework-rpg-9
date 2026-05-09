package com.narxoz.rpg.artifact.visitor;

import com.narxoz.rpg.artifact.*;

public class WeightCalculator implements ArtifactVisitor {
    private int totalWeight = 0;

    public int getTotalWeight() { return totalWeight; }

    private void record(Artifact a, String type) {
        totalWeight += a.getWeight();
        System.out.printf("  [WeightCalculator] %-22s -> %2d kg  (%s)%s%n",
                a.getName(), a.getWeight(), type, a.getWeight() >= 10 ? " [HEAVY]" : "");
    }

    @Override public void visit(Weapon w) { record(w, "Weapon"); }
    @Override public void visit(Potion p) { record(p, "Potion"); }
    @Override public void visit(Scroll s) { record(s, "Scroll"); }
    @Override public void visit(Ring r)   { record(r, "Ring"); }
    @Override public void visit(Armor a)  { record(a, "Armor"); }
}