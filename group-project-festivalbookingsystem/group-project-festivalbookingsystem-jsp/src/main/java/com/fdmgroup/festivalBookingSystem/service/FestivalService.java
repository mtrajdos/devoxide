package com.fdmgroup.festivalBookingSystem.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.festivalBookingSystem.model.Festival;
import com.fdmgroup.festivalBookingSystem.model.Stage;
import com.fdmgroup.festivalBookingSystem.model.Ticket;
import com.fdmgroup.festivalBookingSystem.model.User;
import com.fdmgroup.festivalBookingSystem.repository.FestivalDao;
import com.fdmgroup.festivalBookingSystem.repository.FestivalServiceRepository;

@Service
public class FestivalService implements FestivalServiceRepository<Festival> {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(FestivalService.class);

	@Autowired
	private FestivalDao festivalDao;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private StageService stageService;

	@Override
	public void save(Festival festival) {
		festivalDao.save(festival);
	}

	public List<Festival> findAll() {
		return festivalDao.findAll();
	}

	public Optional<Festival> findById(long festival_id) {
		return festivalDao.findById(festival_id);
	}

	public long daysUntilFestival(Festival festival) {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime festivalStart = festival.getStartDate();
		long noOfDays = ChronoUnit.DAYS.between(today, festivalStart);
		return noOfDays;
	}
	
	public long minutesUntilFestival(Festival festival) {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime festivalStart = festival.getStartDate();
		long noOfMinutes = ChronoUnit.MINUTES.between(today, festivalStart);
		return noOfMinutes;
	}

	public void buyTicket(Festival festival, Optional<User> user) {

		if (minutesUntilFestival(festival) >= 0 && festival.getCurrentTickets() > 0) {
			
			if (user.get().getUserFunds().compareTo(festival.getTicketPrice()) >= 0) {
				updateTicketPrice(festival);
				festival.setCurrentTickets(festival.getCurrentTickets() - 1);
				festivalDao.save(festival);
				Ticket ticket = new Ticket();
				ticket.setTicketName(festival.getFestivalName());
				ticket.setTicketStartDate(festival.getStartDate());
				ticket.setTicketEndDate(festival.getEndDate());
				ticketService.save(ticket);
				user.get().getUserTickets().add(ticket);
				user.get().setUserFunds(user.get().getUserFunds().subtract(festival.getTicketPrice()));
				userService.save(user.get());
			} else {
				LOGGER.info("Unsuccesful attempt to buy a ticket (insufficient funds) at {}", LocalDateTime.now());
			}
			
		} else {
			LOGGER.info("Unsuccesful attempt to buy a ticket (festival started or tickets sold out) at {}", LocalDateTime.now());
		}
		
	}

	public void updateTicketPrice(Festival festival) {

		BigDecimal originalPrice = festival.getOriginalPrice();

		if ((festival.getCurrentTickets() < (festival.getBaseTotalTickets() * 0.95)) && (festival.isEarlyBird())) {
			festival.setEarlyBird(false);
			festival.setSecondRelease(true);
		}

		if ((daysUntilFestival(festival) < 183) && (festival.isEarlyBird())) {
			festival.setEarlyBird(false);
			festival.setSecondRelease(true);
		}

		if (festival.getCurrentTickets() < (festival.getBaseTotalTickets() * 0.85))  {
			festival.setSecondRelease(false);
		}

		if (daysUntilFestival(festival) < 90) {
			festival.setSecondRelease(false);
		}

		if (festival.isEarlyBird()) {
			festival.setTicketPrice(originalPrice);
			festival.setTicketPrice(
					festival.getTicketPrice().multiply(new BigDecimal("0.60")).round(new MathContext(4)));
		} else if (festival.isSecondRelease()) {
			festival.setTicketPrice(originalPrice);
			festival.setTicketPrice(
					festival.getTicketPrice().multiply(new BigDecimal("0.80")).round(new MathContext(4)));
		} else {
			festival.setTicketPrice(originalPrice);
		}

		festivalDao.save(festival);
	}
	
	public void createStageAddToFestival(Long festivalId) {
		Festival festival = findById(festivalId).get();
		if (festival.getFestivalStages().size() < 5) {
			Stage stage = new Stage(festival.getFestivalName().concat(" Stage") );
			stageService.save(stage);
			festival.getFestivalStages().add(stage);
		}
		save(festival);
	}

}
