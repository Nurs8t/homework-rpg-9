package com.narxoz.rpg.artifact.visitor;

import com.narxoz.rpg.artifact.*;

public class EnchantmentScanner implements ArtifactVisitor {

    @Override
    public void visit(Weapon w) {
        String lvl = w.getAttackBonus() >= 10 ? "LEGENDARY" : w.getAttackBonus() >= 5 ? "RARE" : "COMMON";
        System.out.printf("  [EnchantmentScanner] %-22s -> Attack +%d  [%s]%n",
                w.getName(), w.getAttackBonus(), lvl);
    }

    @Override
    public void visit(Potion p) {
        String fx = p.getHealing() >= 50 ? "Grand Heal" : p.getHealing() >= 20 ? "Moderate Heal" : "Minor Heal";
        System.out.printf("  [EnchantmentScanner] %-22s -> Heals %d HP  [%s]%n",
                p.getName(), p.getHealing(), fx);
    }

    @Override
    public void visit(Scroll s) {
        System.out.printf("  [EnchantmentScanner] %-22s -> Spell: \"%s\"  [ARCANE]%n",
                s.getName(), s.getSpellName());
    }

    @Override
    public void visit(Ring r) {
        String lvl = r.getMagicBonus() >= 8 ? "MYSTICAL" : r.getMagicBonus() >= 4 ? "ENCHANTED" : "FAINT";
        System.out.printf("  [EnchantmentScanner] %-22s -> Magic +%d  [%s]%n",
                r.getName(), r.getMagicBonus(), lvl);
    }

    @Override
    public void visit(Armor a) {
        String gr = a.getDefenseBonus() >= 10 ? "FORTIFIED" : a.getDefenseBonus() >= 5 ? "REINFORCED" : "BASIC";
        System.out.printf("  [EnchantmentScanner] %-22s -> Defense +%d  [%s]%n",
                a.getName(), a.getDefenseBonus(), gr);
    }
}