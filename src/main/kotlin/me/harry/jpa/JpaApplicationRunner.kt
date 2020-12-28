package me.harry.jpa

import me.harry.jpa.shop.domain.Album
import me.harry.jpa.shop.domain.Book
import me.harry.jpa.shop.domain.Order
import me.harry.jpa.shop.domain.OrderItem
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.lang.Exception
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

@Component
class JpaApplicationRunner(val emf: EntityManagerFactory) : ApplicationRunner {
    // EntityManagerFactory는 DB당 하나!

    override fun run(args: ApplicationArguments?) {
        val em = emf.createEntityManager()

        val tx = em.transaction
        tx.begin()

        val item = Book(name = "JPA", price = 30000, stockQuantity = 1000, author = "김영한", isbn = "1234")

        em.persist(item)

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