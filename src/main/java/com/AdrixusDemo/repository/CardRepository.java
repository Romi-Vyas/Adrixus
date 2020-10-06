package com.AdrixusDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.AdrixusDemo.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

	@Modifying
	@Query("Update Card c set c.cardType=?1, c.cvc=?2, c.password=?3, c.account.accountId=?4 where c.cardId=?5")
	void setCardToAccount(@Param("cardType") String cardType, @Param("cvc") String cvc,
			@Param("password") String password, @Param("accountId") long accountId, @Param("cardId") long cardId);

}
