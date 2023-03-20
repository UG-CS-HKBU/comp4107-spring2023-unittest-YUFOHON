import org.testng.AssertJUnit.assertTrue
import org.testng.annotations.Test

class CaoCaoUnitTest {

    @Test
    fun testCaoDodgeAttack() {
        monarchHero = UnitTest.FakeMonarchFactory.createRandomHero() as MonarchHero
        heroes.add(monarchHero)
        monarchHero.setCommand(Abandon(monarchHero))
        for (i in 0..2) {
            var hero = UnitTest.FakeNonmonarchFactory.createRandomHero()
            hero.index = heroes.size;
            heroes.add(hero)
        }

        for (hero in heroes) {
            hero.beingAttacked()
            hero.templateMethod()
            if (hero.name == "Cao Cao") {

                assertTrue(hero.dodgeAttack())

            }
        }
    }
}