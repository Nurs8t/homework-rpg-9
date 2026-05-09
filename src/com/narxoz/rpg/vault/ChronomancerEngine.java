package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.artifact.visitor.CurseDetector;
import com.narxoz.rpg.artifact.visitor.EnchantmentScanner;
import com.narxoz.rpg.artifact.visitor.GoldAppraiser;
import com.narxoz.rpg.artifact.visitor.WeightCalculator;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;
import java.util.List;

public class ChronomancerEngine {

    public VaultRunResult runVault(List<Hero> party) {
        int artifactsAppraised = 0, mementosCreated = 0, restoredCount = 0;

        // Build vault inventory (6 artifacts, 5 types)
        Inventory vault = new Inventory();
        vault.addArtifact(new Weapon("Chrono Blade",    120, 8,  12));
        vault.addArtifact(new Potion("Elixir of Ages",   35, 1,  60));
        vault.addArtifact(new Scroll("Scroll of Rewind", 51, 1, "Temporal Rift"));
        vault.addArtifact(new Ring  ("Ring of Echoes",   77, 1,   9));
        vault.addArtifact(new Armor ("Timewarden Plate",200,15,  13));
        vault.addArtifact(new Weapon("Rusty Dagger",     13, 2,   2));
        artifactsAppraised = vault.size();

        System.out.println("\n========================================");
        System.out.println("  CHRONOMANCER'S VAULT - APPRAISAL");
        System.out.println("========================================");

        System.out.println("\n[Visitor 1] GoldAppraiser:");
        GoldAppraiser gold = new GoldAppraiser();
        vault.accept(gold);
        System.out.println("  Total value: " + gold.getTotalValue() + " gold");

        System.out.println("\n[Visitor 2] EnchantmentScanner:");
        vault.accept(new EnchantmentScanner());

        System.out.println("\n[Visitor 3] CurseDetector:");
        CurseDetector curse = new CurseDetector();
        vault.accept(curse);
        System.out.println("  Cursed items found: " + curse.getCursedCount());

        System.out.println("\n[Visitor 4 - Open/Closed proof] WeightCalculator:");
        WeightCalculator wc = new WeightCalculator();
        vault.accept(wc);
        System.out.println("  Total weight: " + wc.getTotalWeight() + " kg");

        System.out.println("\n========================================");
        System.out.println("  MEMENTO WORKFLOW - SNAPSHOT & REWIND");
        System.out.println("========================================");

        Caretaker[] caretakers = new Caretaker[party.size()];
        for (int i = 0; i < party.size(); i++) caretakers[i] = new Caretaker();

        for (int i = 0; i < party.size(); i++) {
            Hero hero = party.get(i);
            Caretaker ct = caretakers[i];

            System.out.println("\n> Hero: " + hero.getName());
            System.out.println("  BEFORE trap: " + hero);

            ct.save(hero.createMemento());
            mementosCreated++;
            System.out.println("  [Memento] Snapshot saved. Stack size: " + ct.size());

            hero.takeDamage(40);
            hero.spendMana(30);
            hero.spendGold(50);
            System.out.println("  Vault trap! -40 HP, -30 mana, -50 gold");
            System.out.println("  AFTER trap:  " + hero);

            hero.restoreFromMemento(ct.undo());
            restoredCount++;
            System.out.println("  [Memento] Rewound. Stack size: " + ct.size());
            System.out.println("  AFTER rewind: " + hero);
        }

        System.out.println("\n========================================");

        return new VaultRunResult(artifactsAppraised, mementosCreated, restoredCount);
    }
}