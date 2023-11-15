package tn.esprit.spring;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(properties = "spring.config.name=application-test")
public class GestionStationSkiApplicationTestsMockito {


    @Mock
    ISubscriptionRepository subscriptionRepository;

    @InjectMocks
    SubscriptionServicesImpl SubscriptionService;

    Subscription s = Subscription.builder().price(5F).typeSub(TypeSubscription.ANNUAL).endDate(LocalDate.of(2024, Month.JANUARY, 1)).startDate(LocalDate.of(2014, Month.JANUARY, 1)).build();

    List<Subscription> list= new ArrayList<Subscription>() {
        {
            add(Subscription.builder().price(6F).typeSub(TypeSubscription.MONTHLY).endDate(LocalDate.of(2025, Month.JANUARY, 1)).startDate(LocalDate.of(2015, Month.JANUARY, 1)).build());
            add(Subscription.builder().price(7F).typeSub(TypeSubscription.SEMESTRIEL).endDate(LocalDate.of(2026, Month.JANUARY, 1)).startDate(LocalDate.of(2016, Month.JANUARY, 1)).build());
        }
    };

    @Test
    public void addSubscriptionTest() {
        Mockito.when(subscriptionRepository.save(Mockito.any(Subscription.class))).then(invocation -> {
            Subscription model = invocation.getArgument(0, Subscription.class);
            model.setNumSub(12345l);
            return model;
        });
        log.info("before :" + s.toString());
        Subscription magasin = SubscriptionService.addSubscription(s);
        assertSame(magasin, s);
        log.info("after :" + s.toString());
    }

    @Test
    public void retreiveSubscriptionTest() {
        Mockito.when(subscriptionRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(s));
        Subscription magasin = SubscriptionService.retrieveSubscriptionById((long) 1566);
        assertNotNull(magasin);
        log.info("get ==> " + magasin.toString());
        verify(subscriptionRepository).findById(Mockito.anyLong());

    }

    @Test
    public void retreiveAllMagasinTest() {
        Mockito.when(subscriptionRepository.findAll()).thenReturn(list);
        List<Subscription> subscriptions = SubscriptionService.findAllSubscriptions();
        assertNotNull(subscriptions);
    }
}
