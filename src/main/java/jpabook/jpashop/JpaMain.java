package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team team = new Team();
            team.setName("TeamA");
            //team.getMembers().add(member);
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            // em.flush();
            // em.clear();

            /* 영속성 컨텍스트 1차 캐시에는 Team안에 멤버가 없음 */
            Team findTeam = em.find(Team.class, team.getId());  // 1차 캐시
            for (Member m : findTeam.getMembers()){
                m.getUsername();
            }

            /* 따라서 team.getMembers().add(member); 로 셋팅 해줘야함. 그리고 조금 더 객체지향적임 */
            team.getMembers().add(member);

            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
