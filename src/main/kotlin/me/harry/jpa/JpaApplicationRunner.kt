package me.harry.jpa

import me.harry.jpa.domain.Member
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import javax.persistence.EntityManagerFactory

@Component
class JpaApplicationRunner(val emf: EntityManagerFactory) : ApplicationRunner {
    // EntityManagerFactory는 DB당 하나!

    override fun run(args: ApplicationArguments?) {
        // 요청이 올 때 마다 entityManager는 생성되고 버려진다.
        // entityManager는 쓰레드간에 공유해선 안된다. (사용하고 버려야한다.)
        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행해야한다.
        val em = emf.createEntityManager()

        val tx = em.transaction
        tx.begin()

        try {
            // JPA를 통해 가져온 데이터는 EntityManager가 관리해주기 때문에 update 쿼리를 날린다.
            val findMember = em.find(Member::class.java, 1L)
            findMember.name = "바보"

            // 트랜잭션이 커밋되기 전에 update 쿼리가 발생한다.
            tx.commit()
        } catch (e: Exception) {
            tx.rollback()
        } finally {
            em.close()
        }

        emf.close()
    }
}