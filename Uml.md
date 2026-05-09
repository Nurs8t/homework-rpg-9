@startuml

interface ArtifactVisitor {
+visit(w: Weapon): void
+visit(p: Potion): void
+visit(s: Scroll): void
+visit(r: Ring): void
+visit(a: Armor): void
}

abstract class Artifact {
#name: String
#value: int
#weight: int
+{abstract} accept(v: ArtifactVisitor): void
}

class Weapon { +attackBonus: int }
class Potion  { +healing: int }
class Scroll  { +spellName: String }
class Ring    { +magicBonus: int }
class Armor   { +defenseBonus: int }

Artifact <|-- Weapon
Artifact <|-- Potion
Artifact <|-- Scroll
Artifact <|-- Ring
Artifact <|-- Armor

class GoldAppraiser    { +totalValue: int }
class EnchantmentScanner
class CurseDetector    { +cursedCount: int }
class WeightCalculator { +totalWeight: int }

ArtifactVisitor <|.. GoldAppraiser
ArtifactVisitor <|.. EnchantmentScanner
ArtifactVisitor <|.. CurseDetector
ArtifactVisitor <|.. WeightCalculator

class Inventory {
-artifacts: List<Artifact>
+addArtifact(a: Artifact): void
+accept(v: ArtifactVisitor): void
}

Inventory o-- Artifact

class Hero <<originator>> {
-hp, mana, gold: int
+createMemento(): HeroMemento
+restoreFromMemento(m: HeroMemento): void
}

class HeroMemento <<memento>> {
~hp, mana, gold: int
~inventorySnapshot: List<Artifact>
}

class Caretaker <<caretaker>> {
-history: Deque<HeroMemento>
+save(m: HeroMemento): void
+undo(): HeroMemento
+peek(): HeroMemento
}

Hero ..> HeroMemento : creates
Caretaker --> HeroMemento : stores

class ChronomancerEngine {
+runVault(party: List<Hero>): VaultRunResult
}

class VaultRunResult {
+artifactsAppraised: int
+mementosCreated: int
+restoredCount: int
}

ChronomancerEngine ..> Hero
ChronomancerEngine ..> Inventory
ChronomancerEngine ..> Caretaker
ChronomancerEngine ..> VaultRunResult : returns

@enduml