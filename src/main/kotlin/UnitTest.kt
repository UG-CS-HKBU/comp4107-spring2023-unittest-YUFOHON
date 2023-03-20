import org.testng.AssertJUnit.assertTrue
import org.testng.annotations.Test

class UnitTest {
    @Test
    fun test1() {
        heroes.clear()
        monarchHero = CaoCao()
        heroes.add(ZhangFei(MinisterRole()))
        assertTrue(monarchHero.name == "Cao Cao")
    }

    @Test
    fun test2() {
        if (heroes.size < 2)
            test1()
        assertTrue(heroes.size == 2)

    }

    @Test
    fun testCaoDodgeAttack() {
//        In the function, set the value of monarchHero to Cao Cao.
//        Use a for loop to call NonMonarchFactory.createRandomHero() to create 7 heros and store them using the heroes list.
//        (Note that monarchHero and heroes are the variables defined in the production code)
//        Call Cao Cao's dodgeAttack() method and use assertTrue() to test whether it returns true or not.
//        Now, you can click the ▶ button showed on the left-hand-side of the testCaoDodgeAttack() function to run the test.
        monarchHero = CaoCao()
        for (i in 1..7)
            heroes.add(NoneMonarchFactory.createRandomHero())
        assertTrue(monarchHero.dodgeAttack())


    }

    //    Now, we create a new test function named testBeingAttacked() to test the beingAttacked() function of all non-monarch heres.
//    Use a for loop to do the followings:
//    Retrieve a hero from the heroes list.
//    Create a spy object that wrap the hero and has a beingAttacked() function. The spy's beingAttacked() function calls the hero's beingAttacked() function and test the hero's hp whether it is greater than or equal to zero.
//    Call the beingAttacked() function of the spy object 10 times.
//    Click the ▶ button showed on the left-hand-side of the UnitTest class. It will first run testCaoDodgeAttack() function, and then run testBeingAttacked() function.
//    Add statements at the top of the testBeingAttacked() function to call the NonMonarchFactory.createRandomHero() function if the heros list is empty.
//    Now, you can click the ▶ button of the testBeingAttacked() function to run it individually.
    @Test
    fun testBeingAttacked() {
        if (heroes.isEmpty())
            NoneMonarchFactory.createRandomHero()

        for (hero in heroes) {

            val spy = object : WarriorHero(MinisterRole()) {
                override val name = hero.name
                override fun beingAttacked() {
                    hero.beingAttacked()
                    assertTrue(hero.hp >= 0)
                }
            }
            for (i in 1..10)
                spy.beingAttacked()
        }
    }

    //Create a new class in UnitTest.kt named DummyRole that decorates the Role interface, and implements the methods with dummy code.
    class DummyRole : Role {
        override val roleTitle: String
            get() = TODO("Not yet implemented")

        override fun getEnemy(): String {
            TODO("Not yet implemented")
        }
    }
    @Test
    fun testDiscardCards(){
        //In the test function, create a new instance of the ZhangFei class and test its' discardCards()
      var zhangfei = ZhangFei(DummyRole())
        var numCards = zhangfei.numOfCards
        zhangfei.discardCards()
        assertTrue(zhangfei.numOfCards < numCards)
    }
    object FakeNonmonarchFactory : GameObjectFactory {
        var count = 0
        var last: WeiHero? = null

        init {
            monarchHero = CaoCao()
        }

        override fun getRandomRole(): Role =
            MinisterRole()

        override fun createRandomHero(): Hero {
            val hero = when (count++ % 3) {
                0 -> SimaYi(getRandomRole())
                1 -> XiaHouyuan(getRandomRole())
                else -> XuChu(getRandomRole())
            }
            val cao = monarchHero as CaoCao
            if (last == null)
                cao.helper = hero
            else
                last!!.setNext(hero)
            last = hero
            return hero
        }
    }

    object FakeMonarchFactory : GameObjectFactory {
        var count = 0
        var last: WeiHero? = null
        override fun getRandomRole(): Role {
            TODO("Not yet implemented")
        }


        override fun createRandomHero(): Hero {
            return CaoCao()
        }
    }
}