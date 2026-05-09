package com.narxoz.rpg.artifact.visitor;

import com.narxoz.rpg.artifact.*;

public class GoldAppraiser implements ArtifactVisitor {
    private int totalValue = 0;

    public int getTotalValue() { return totalValue; }

    @Override
    public void visit(Weapon w) {
        int v = w.getValue() + w.getAttackBonus() * 5;
        System.out.printf("  [GoldAppraiser] %-22s -> %4d gold  (base %d + attack %d*5)%n",
                w.getName(), v, w.getValue(), w.getAttackBonus());
        totalValue += v;
    }

    @Override
    public void visit(Potion p) {
        int v = p.getValue() + p.getHealing() * 2;
        System.out.printf("  [GoldAppraiser] %-22s -> %4d gold  (base %d + healing %d*2)%n",
                p.getName(), v, p.getValue(), p.getHealing());
        totalValue += v;
    }

    @Override
    public void visit(Scroll s) {
        int v = s.getValue() * 3;
        System.out.printf("  [GoldAppraiser] %-22s -> %4d gold  (base %d *3 rare multiplier)%n",
                s.getName(), v, s.getValue());
        totalValue += v;
    }

    @Override
    public void visit(Ring r) {
        int v = r.getValue() + r.getMagicBonus() * 10;
        System.out.printf("  [GoldAppraiser] %-22s -> %4d gold  (base %d + magic %d*10)%n",
                r.getName(), v, r.getValue(), r.getMagicBonus());
        totalValue += v;
    }

    @Override
    public void visit(Armor a) {
        int v = a.getValue() + a.getDefenseBonus() * 4;
        System.out.printf("  [GoldAppraiser] %-22s -> %4d gold  (base %d + defense %d*4)%n",
                a.getName(), v, a.getValue(), a.getDefenseBonus());
        totalValue += v;
    }
}