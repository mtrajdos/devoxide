package com.fdmgroup.festivalBookingSystem.controller.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatWithLocale {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm", Locale.UK);

	String strLocalDate = "23-10-2015T03:34";

	LocalDateTime localDate = LocalDateTime.parse(strLocalDate, formatter);
}
