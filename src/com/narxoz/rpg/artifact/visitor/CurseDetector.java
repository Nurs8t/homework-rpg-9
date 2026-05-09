package com.narxoz.rpg.artifact.visitor;

import com.narxoz.rpg.artifact.*;

public class CurseDetector implements ArtifactVisitor {
    private int cursedCount = 0;

    public int getCursedCount() { return cursedCount; }

    private void check(Artifact a, String extra) {
        boolean cursed = (a.getValue() % 2 != 0) && (a.getWeight() < 3);
        if (cursed) cursedCount++;
        System.out.printf("  [CurseDetector] %-22s -> %s  (%s)%n",
                a.getName(), cursed ? "*** CURSED ***" : "Clean", extra);
    }

    @Override public void visit(Weapon w) { check(w, "attack: " + w.getAttackBonus()); }
    @Override public void visit(Potion p) { check(p, "healing: " + p.getHealing()); }
    @Override public void visit(Scroll s) { check(s, "spell: " + s.getSpellName()); }
    @Override public void visit(Ring r)   { check(r, "magic: " + r.getMagicBonus()); }
    @Override public void visit(Armor a)  { check(a, "defense: " + a.getDefenseBonus()); }
}