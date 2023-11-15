package tn.esprit.spring;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import lombok.extern.slf4j.Slf4j;

import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.StreamSupport;


@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@Slf4j
@SpringBootTest


@ActiveProfiles("test")
class GestionStationSkiApplicationTests {

	@Autowired
	ISubscriptionRepository subscriptionRepository;

	Subscription s = Subscription.builder().numSub(123456L).price(5F).typeSub(TypeSubscription.ANNUAL).endDate(LocalDate.of(2024, Month.JANUARY, 1)).startDate(LocalDate.of(2014, Month.JANUARY, 1)).build();

    //test pour ajout
	@Test
	@Order(0)
	 void addSubsciption(){
		s = subscriptionRepository.save(s);
		log.info(s.toString());
		Assertions.assertNotNull(s.getNumSub());
	}

	//test pour modification
	@Test
	@Order(1)
	 void editSubscription(){
		s.setPrice(2F);
		s = subscriptionRepository.save(s);
		log.info(s.toString());
		Assertions.assertNotEquals( 5f,s.getPrice());
	}

	//test pour lister
	@Test
	@Order(2)
	 void showSubscription(){
		List<Subscription> list = (List<Subscription>) subscriptionRepository.findAll();
		log.info(list.size()+"");
		Assertions.assertTrue(list.size() > 0);
	}

	//test pour supression
	@Test
	@Order(4)
	 void supprimerMagasin() {
		subscriptionRepository.delete(s);
		Subscription deletedSubscription = subscriptionRepository.findById(s.getNumSub()).orElse(null);
		Assertions.assertNull(deletedSubscription, "The subscription should have been deleted");
	}


	@Test
	@Order(5)
	 void compter() {
		long taille = subscriptionRepository.count();
		long count = StreamSupport.stream(subscriptionRepository.findAll().spliterator(), false)
				.count();
		Assertions.assertEquals(taille,count);
	}

}
