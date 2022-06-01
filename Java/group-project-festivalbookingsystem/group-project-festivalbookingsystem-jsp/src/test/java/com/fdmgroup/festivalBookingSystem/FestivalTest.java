package com.fdmgroup.festivalBookingSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.festivalBookingSystem.model.Festival;
import com.fdmgroup.festivalBookingSystem.model.Genre;
import com.fdmgroup.festivalBookingSystem.model.User;
import com.fdmgroup.festivalBookingSystem.model.UserType;
import com.fdmgroup.festivalBookingSystem.service.FestivalService;
import com.fdmgroup.festivalBookingSystem.service.UserService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class FestivalTest {
	
	@Autowired
	private FestivalService festivalService;
	
	@Autowired 
	UserService userService;

	@Test
	void test_ThatYouCanAddAFestival() {
		int numberOfFestivalsBefore = festivalService.findAll().size();
		Festival festival = new Festival();
		LocalDateTime startDate = LocalDateTime.parse("2020-07-30T11:00:00");
		festival.setStartDate(startDate);
		LocalDateTime endDate = LocalDateTime.parse("2020-08-02T11:00:00");
		festival.setEndDate(endDate);
		festival.setFestivalBands(null);
		festival.setFestivalName("Download");
		festival.setLocation("Dorington Park");
		festival.setGenre(Genre.COUNTRY);
		festival.setBaseTotalTickets(500);
		festival.setCurrentTickets(500);
		festival.setTicketPrice(new BigDecimal("10000.00"));
		festival.setOriginalPrice(new BigDecimal("100000.00"));
		festival.setEarlyBird(false);
		festival.setOnSiteAccommodation("Camping");
		festivalService.save(festival);
		int numberOfFestivalsAfter = festivalService.findAll().size();
		assertNotEquals(numberOfFestivalsBefore, numberOfFestivalsAfter);
	}
	
	@Test
	void test_ThatYouCanUpdateTheFestival() {
		Festival newFestival = new Festival("New Festival", "Jan house", LocalDateTime.parse("2020-04-01T11:00:00"), LocalDateTime.parse("2020-04-01T11:00:00"), Genre.POP, 5000, new BigDecimal("200.00"), false, false, "camping");
		festivalService.save(newFestival);
		newFestival.setFestivalName("Bepis");
		assertEquals("Bepis", newFestival.getFestivalName());
	}
	
	@Test 
	void test_ThatFestivalCanBeFoundById() {
		Optional<Festival> foundFestival = festivalService.findById(1);
		assertFalse(foundFestival.isEmpty());
	}
	
	@Test
	void test_ThatTheNumberOfTicketsDecreasedByOneWhenBought() {
		Festival festival = new Festival("Swag", "Pen Island", LocalDateTime.parse("2069-04-20T11:00:00"), LocalDateTime.parse("2069-04-21T11:00:00"), Genre.INSTRUMENTAL, 100000, new BigDecimal("200.00"), false, false, "camping");
		festivalService.save(festival);
		User user = new User("vanceb@gmail.com", "bob", "vance", "newuserpassword", UserType.ATTENDEE, new BigDecimal("400.00"));
		userService.save(user);
		Optional<User> foundUser = userService.findById(user.getUser_id());
		int ticketsBefore = festival.getCurrentTickets();
		festivalService.buyTicket(festival, foundUser);
		int ticketsAfter = festival.getCurrentTickets();
		assertNotEquals(ticketsAfter, ticketsBefore);
	}
	
	@Test
	void test_ThatATicketIsNotEarlyBirdAfter10PercentOfTicketsAreBought() {
		Festival festival = new Festival("Swag", "Pen Island", LocalDateTime.parse("2069-04-20T11:00:00"), LocalDateTime.parse("2069-04-21T11:00:00"), Genre.INSTRUMENTAL, 100, new BigDecimal("100.00"), true, false, "Camping");
		festivalService.save(festival);
		User user = new User("vanceb@gmail.com", "bob", "vance", "newuserpassword", UserType.ATTENDEE, new BigDecimal("10000.00"));
		userService.save(user);
		Optional<User> foundUser = userService.findById(user.getUser_id());

	    for (int i=0;i<10;i++) {
	    	
	    	festivalService.buyTicket(festival, foundUser);
	    }
	    
		assertFalse(festival.isEarlyBird());
		
	}
	
	
	@Test
	void testThatDaysUntilFestivalReturnsTheExpectedInteger() {
		Festival festival = new Festival("Swag", "Pen Island", LocalDateTime.now().plusDays(17), LocalDateTime.parse("2020-04-21T11:00:00"), Genre.INSTRUMENTAL, 100, new BigDecimal("100.00"), true, false, "Camping");
		festivalService.save(festival);
		long actual = festivalService.daysUntilFestival(festival);
		long expected = 16;
		assertEquals(expected,actual);
	}
	
	@Test
	void testThatEarlyBirdIsTrue() {
		Festival festival = new Festival("Swag", "Pen Island", LocalDateTime.parse("2021-04-20T11:00:00"), LocalDateTime.parse("2020-04-21T11:00:00"), Genre.INSTRUMENTAL, 100, new BigDecimal("100.00"), true, false, "Camping");
		festivalService.save(festival);
		festivalService.updateTicketPrice(festival);
		assertEquals(new BigDecimal("60.00"), festival.getTicketPrice());
	}
	@Test
	void testThatEarlyBirdIsFalse() {
		Festival festival = new Festival("Swag", "Pen Island", LocalDateTime.parse("2020-04-20T11:00:00"), LocalDateTime.parse("2020-04-21T11:00:00"), Genre.INSTRUMENTAL, 100, new BigDecimal("100.00"), true, false, "Camping");
		festivalService.save(festival);
		festivalService.updateTicketPrice(festival);
		assertEquals(new BigDecimal("100.00"), festival.getTicketPrice());
	}
	
	@Test
	void testThatEarlyBirdIsFalseBecauseNoMoreEarlyBirdTicketsAvailable() {
		Festival festival = new Festival("Swag", "Pen Island", LocalDateTime.parse("2020-04-20T11:00:00"), LocalDateTime.parse("2020-04-21T11:00:00"), Genre.INSTRUMENTAL, 100, new BigDecimal("100.00"), true, false, "Camping");
		festival.setCurrentTickets(50);
		festivalService.save(festival);
		festivalService.updateTicketPrice(festival);
		assertEquals(new BigDecimal("100.00"), festival.getTicketPrice());
	}
	
	@Test
	void testThatSecondReleaseAvailableWithin90DaysBeforeFestivalWhenEarlyBirdNotAvailable() {
		Festival festival = new Festival("Swag vol 2", "Pen Island", LocalDateTime.now().plusDays(91), LocalDateTime.parse("2020-08-05T11:00:00"), Genre.INSTRUMENTAL, 100, new BigDecimal("100.00"), true, false, "Camping");
		festivalService.save(festival);
		User user = new User("vanceb@gmail.com", "bob", "vance", "newuserpassword", UserType.ATTENDEE, new BigDecimal("10000.00"));
		userService.save(user);
		Optional<User> foundUser = userService.findById(user.getUser_id());
		festival.setCurrentTickets(festival.getCurrentTickets()-5);
		festivalService.buyTicket(festival, foundUser);
		assertTrue(festival.isSecondRelease());
	}
	
	@Test
	void testThatPriceIsUpdatedAccordinglyForSecondRelease() {
		Festival festival = new Festival("Swag vol 3", "Pen Island", LocalDateTime.now().plusDays(91), LocalDateTime.parse("2020-08-05T11:00:00"), Genre.INSTRUMENTAL, 100, new BigDecimal("100.00"), true, false, "Camping");
		festival.setCurrentTickets(86);
		festivalService.updateTicketPrice(festival);
		assertEquals(new BigDecimal("80.00"), festival.getTicketPrice());
	}
	
	@Test
	void testThatATicketCanBeBoughtAndAddedToAUser() {
		Festival festival = new Festival("Swag vol 4", "Pen Island", LocalDateTime.now().plusDays(91), LocalDateTime.parse("2020-08-05T11:00:00"), Genre.INSTRUMENTAL, 100, new BigDecimal("100.00"), false, false, "Camping");
		festivalService.save(festival);
		User user = new User("vanceb@gmail.com", "bob", "vance", "newuserpassword", UserType.ATTENDEE, new BigDecimal("200.00"));
		userService.save(user);
		Optional<User> foundUser = userService.findById(user.getUser_id());
		festivalService.buyTicket(festival, foundUser);
		assertNotNull(foundUser.get().getUserTickets());
	}
	
	@Test
	void testThatMoneyGetsTakenAwayFromUserWhenTicketBought() {
		Festival festival = new Festival("Swag vol 5", "Pen Island", LocalDateTime.now().plusDays(91), LocalDateTime.parse("2020-08-05T11:00:00"), Genre.INSTRUMENTAL, 100, new BigDecimal("100.00"), false, false, "Camping");
		festivalService.save(festival);
		User user = new User("vanceb@gmail.com", "bob", "vance", "newuserpassword", UserType.ATTENDEE, new BigDecimal("200.00"));
		userService.save(user);
		Optional<User> foundUser = userService.findById(user.getUser_id());
		BigDecimal moneyBefore = foundUser.get().getUserFunds();
		festivalService.buyTicket(festival, foundUser);
		BigDecimal moneyAfter = foundUser.get().getUserFunds();
		assertNotEquals(moneyBefore, moneyAfter);
		
	}
	
	@Test
	void testThatThePriceChangesWhenMultipleTicketsAreBoughtAfterEarlyBird() {
		Festival festival = new Festival("Swag vol 6", "Pen Island", LocalDateTime.now().plusDays(200), LocalDateTime.parse("2020-08-05T11:00:00"), Genre.INSTRUMENTAL, 100, new BigDecimal("100.00"), true, false, "Camping");
		festivalService.save(festival);
		User user = new User("vanceb@gmail.com", "bob", "vance", "newuserpassword", UserType.ATTENDEE, new BigDecimal("200.00"));
		userService.save(user);
		festival.setCurrentTickets(95);
		festivalService.save(festival);
		Optional<User> foundUser = userService.findById(user.getUser_id());
		festivalService.buyTicket(festival, foundUser);
		festivalService.save(festival);
		festivalService.buyTicket(festival, foundUser);
		assertEquals(new BigDecimal("60.00"),foundUser.get().getUserFunds());
	}
	
	@Test
	void testThatNoTicketsCanBeBoughtIfFestivalAlreadyStarted() {
		Festival festival = new Festival("Swag vol 7", "Pen Island", LocalDateTime.now().minusDays(1), LocalDateTime.parse("2020-04-07T09:00:00"), Genre.INSTRUMENTAL, 100, new BigDecimal("100.00"), true, false, "Camping");
		festivalService.save(festival);
		User user = new User("vanceb@gmail.com", "bob", "vance", "newuserpassword", UserType.ATTENDEE, new BigDecimal("200.00"));
		userService.save(user);
		Optional<User> foundUser = userService.findById(user.getUser_id());
		int ticketsBefore = festival.getCurrentTickets();
		festivalService.buyTicket(festival, foundUser);
		int ticketsAfter = festival.getCurrentTickets();
		assertEquals(ticketsBefore, ticketsAfter);
	}
	
	@Test
	void testThatNoTicketsCanBeBoughtIfTicketsSoldOut() {
		Festival festival = new Festival("Swag vol 8", "Pen Island", LocalDateTime.now().plusDays(1000), LocalDateTime.parse("2025-01-01T06:59:35"), Genre.INSTRUMENTAL, 1, new BigDecimal("100.00"), true, false, "Camping");
		festivalService.save(festival);
		User user = new User("vanceb@gmail.com", "bob", "vance", "newuserpassword", UserType.ATTENDEE, new BigDecimal("200.00"));
		userService.save(user);
		Optional<User> foundUser = userService.findById(user.getUser_id());
		festivalService.buyTicket(festival, foundUser);
		int ticketsBefore = festival.getCurrentTickets();
		festivalService.buyTicket(festival, foundUser);
		int ticketsAfter = festival.getCurrentTickets();
		assertEquals(ticketsBefore, ticketsAfter);
	}
	
	@Test
	void testThatTicketsCanBeBoughtIfFestivalStartsInOneMinute() {
		Festival festival = new Festival("Swag vol 9", "Pen Island", LocalDateTime.now().plusMinutes(1), LocalDateTime.parse("2025-01-01T06:59:35"), Genre.INSTRUMENTAL, 100, new BigDecimal("100.00"), true, false, "Camping");
		festivalService.save(festival);
		User user = new User("vanceb@gmail.com", "bob", "vance", "newuserpassword", UserType.ATTENDEE, new BigDecimal("200.00"));
		userService.save(user);
		Optional<User> foundUser = userService.findById(user.getUser_id());
		int ticketsBefore = festival.getCurrentTickets();
		festivalService.buyTicket(festival, foundUser);
		int ticketsAfter = festival.getCurrentTickets();
		assertEquals(ticketsBefore - 1, ticketsAfter);
	}
	
	@Test
	void testThatNoTicketsCanBeBoughtIfInsufficientFunds() {
		Festival festival = new Festival("Swag vol 10", "Pen Island", LocalDateTime.now().plusDays(1000), LocalDateTime.parse("2025-01-01T06:59:35"), Genre.INSTRUMENTAL, 1, new BigDecimal("100000000.00"), true, false, "Camping");
		festivalService.save(festival);
		User user = new User("vanceb@gmail.com", "bob", "vance", "newuserpassword", UserType.ATTENDEE, new BigDecimal("5.00"));
		userService.save(user);
		Optional<User> foundUser = userService.findById(user.getUser_id());
		int ticketsBefore = festival.getCurrentTickets();
		festivalService.buyTicket(festival, foundUser);
		int ticketsAfter = festival.getCurrentTickets();
		assertEquals(ticketsBefore, ticketsAfter);
	}
	
	
}
