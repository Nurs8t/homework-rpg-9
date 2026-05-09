package com.narxoz.rpg;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");

        Inventory kiraInv = new Inventory();
        kiraInv.addArtifact(new Weapon("Iron Sword",  50, 5, 7));
        kiraInv.addArtifact(new Potion("Health Vial", 20, 1, 25));

        Hero kira  = new Hero("Kira",  120, 80, 15, 8, 300, kiraInv);
        Hero thane = new Hero("Thane",  90, 40, 20, 5, 150, new Inventory());

        VaultRunResult result = new ChronomancerEngine().runVault(List.of(kira, thane));

        System.out.println("\n=== VAULT RUN COMPLETE ===");
        System.out.println(result);
    }
}