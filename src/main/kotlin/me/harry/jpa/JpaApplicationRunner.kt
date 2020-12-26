package me.harry.jpa

import me.harry.jpa.shop.domain.Order
import me.harry.jpa.shop.domain.OrderItem
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import java.lang.Exception
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

class JpaApplicationRunner(val emf: EntityManagerFactory) : ApplicationRunner {
    // EntityManagerFactory는 DB당 하나!

    override fun run(args: ApplicationArguments?) {
        val emf = Persistence.createEntityManagerFactory("test")
        val em = emf.createEntityManager()

        val tx = em.transaction
        tx.begin()

        try {
            tx.commit()
        } catch (e: Exception) {
            tx.rollback()
        } finally {
            em.close()
        }
        emf.close()
    }
}