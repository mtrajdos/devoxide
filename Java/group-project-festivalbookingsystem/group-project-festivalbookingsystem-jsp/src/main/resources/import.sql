insert into users (user_id, username, firstname, lastname, password, usertype, userfunds) values (USER_SEQ.nextval, 'something@gmail.com', 'Stephen', 'Ripley', '1234', 'ADMIN', '1000000.00');
insert into users (user_id, username, firstname, lastname, password, usertype, userfunds) values (USER_SEQ.nextval, 'somethign@gamil.com', 'StraightStephen', 'Ripley', '1234', 'ATTENDEE', '1000000.00');

insert into band (band_id, bandName, genre) values (BAND_SEQ.nextval, 'Queen', 'ROCK');
insert into band (band_id, bandName, genre) values (BAND_SEQ.nextval, 'Carly Rae Jepson', 'POP');
insert into band (band_id, bandName, genre) values (BAND_SEQ.nextval, 'The Who', 'EDM');
insert into band (band_id, bandName, genre) values (BAND_SEQ.nextval, 'Robbie Rotten', 'POP');
insert into band (band_id, bandName, genre) values (BAND_SEQ.nextval, 'Doja Cat', 'POP');
insert into band (band_id, bandName, genre) values (BAND_SEQ.nextval, 'Kazoo Kid', 'EDM');
insert into band (band_id, bandName, genre) values (BAND_SEQ.nextval, 'Alestorm', 'METAL');
insert into band (band_id, bandName, genre) values (BAND_SEQ.nextval, 'Halestorm', 'ROCK');
insert into band (band_id, bandName, genre) values (BAND_SEQ.nextval, 'Billy Ray Cyrus', 'COUNTRY');
insert into band (band_id, bandName, genre) values (BAND_SEQ.nextval, 'Bach', 'INSTRUMENTAL');

insert into festival(festival_id, festivalname, location, startdate, enddate, genre, baseTotalTickets, currenttickets, originalPrice, ticketPrice, earlyBird, secondRelease, onSiteAccommodation) values (FESTIVAL_SEQ.nextval, 'T In The Park', 'Aberdeen', parsedatetime('27 Jul 2021 11:09', 'dd MMM yyyy hh:mm'), parsedatetime('30 Jul 2021 11:00', 'dd MMM yyyy hh:mm'), 'ROCK', 4000, 4000, '20.00', '20.00', true, false, 'Camping');
insert into festival(festival_id, festivalname, location, startdate, enddate, genre, baseTotalTickets, currenttickets, originalPrice, ticketPrice, earlyBird, secondRelease, onSiteAccommodation) values (FESTIVAL_SEQ.nextval, 'Glastonbury', 'Somerset', parsedatetime('19 Jun 2021 11:00', 'dd MMM yyyy hh:mm'), parsedatetime('30 Jun 2021 11:00', 'dd MMM yyyy hh:mm'), 'MIXED', 7000, 7000, '24.00', '24.00', true, false, 'Camping and Cabins');
insert into festival(festival_id, festivalname, location, startdate, enddate, genre, baseTotalTickets, currenttickets, originalPrice, ticketPrice, earlyBird, secondRelease, onSiteAccommodation) values (FESTIVAL_SEQ.nextval, 'Reading', 'Reading', parsedatetime('05 Aug 2020 02:00', 'dd MMM yyyy hh:mm'), parsedatetime('25 Nov 2021 20:00', 'dd MMM yyyy hh:mm'), 'ROCK', 4000, 4000, '20.00', '20.00', false, false, 'Camping and Chalet');

insert into business(business_id, businessname, businesstype, location, address, phonenumber, email) values (BUSINESS_SEQ.nextval, 'KFC', 'RESTAURANT', 'Glasgow', '1 Imaginery Street', '0193759439', 'kfc@bad.com');
insert into business(business_id, businessname, businesstype, location, address, phonenumber, email) values (BUSINESS_SEQ.nextval, 'HOLIDAY INN', 'ACCOMMODATION', 'Glasgow', '69 Imaginery Street', '4343343434', 'HI@bad.com');
insert into business(business_id, businessname, businesstype, location, address, phonenumber, email) values (BUSINESS_SEQ.nextval, 'CEX', 'MERCHANDISE', 'Edinburgh', '4 Imaginery Street', '0123456787', 'cexhub@bad.com');

insert into stage(stage_id, stagename, festival_id) values (STAGE_SEQ.nextval, 'Michals Crib',1)
insert into stage(stage_id, stagename, festival_id) values (STAGE_SEQ.nextval, 'Davids Crib',1)
insert into stage(stage_id, stagename, festival_id) values (STAGE_SEQ.nextval, 'Jans Crib',1)
insert into stage(stage_id, stagename, festival_id) values (STAGE_SEQ.nextval, 'Scotts Crib',1)
insert into stage(stage_id, stagename, festival_id) values (STAGE_SEQ.nextval, 'Stephens Crib',1)

insert into FESTIVAL_FESTIVALBANDS (FESTIVAL_FESTIVAL_ID, FESTIVALBANDS_BAND_ID) values (1,1);
insert into FESTIVAL_FESTIVALBANDS (FESTIVAL_FESTIVAL_ID, FESTIVALBANDS_BAND_ID) values (1,2);
insert into FESTIVAL_FESTIVALBANDS (FESTIVAL_FESTIVAL_ID, FESTIVALBANDS_BAND_ID) values (1,3);
insert into FESTIVAL_FESTIVALBANDS (FESTIVAL_FESTIVAL_ID, FESTIVALBANDS_BAND_ID) values (1,4);
insert into FESTIVAL_FESTIVALBANDS (FESTIVAL_FESTIVAL_ID, FESTIVALBANDS_BAND_ID) values (1,5);
insert into FESTIVAL_FESTIVALBANDS (FESTIVAL_FESTIVAL_ID, FESTIVALBANDS_BAND_ID) values (1,6);
insert into FESTIVAL_FESTIVALBANDS (FESTIVAL_FESTIVAL_ID, FESTIVALBANDS_BAND_ID) values (1,7);
insert into FESTIVAL_FESTIVALBANDS (FESTIVAL_FESTIVAL_ID, FESTIVALBANDS_BAND_ID) values (1,8);
insert into FESTIVAL_FESTIVALBANDS (FESTIVAL_FESTIVAL_ID, FESTIVALBANDS_BAND_ID) values (2,2);
insert into FESTIVAL_FESTIVALBANDS (FESTIVAL_FESTIVAL_ID, FESTIVALBANDS_BAND_ID) values (2,4);
insert into FESTIVAL_FESTIVALBANDS (FESTIVAL_FESTIVAL_ID, FESTIVALBANDS_BAND_ID) values (3,1);
insert into FESTIVAL_FESTIVALBANDS (FESTIVAL_FESTIVAL_ID, FESTIVALBANDS_BAND_ID) values (3,4);

insert into STAGE_BANDSINSTAGE(STAGE_STAGE_ID, BANDSINSTAGE_BAND_ID) values (1,1);
insert into STAGE_BANDSINSTAGE(STAGE_STAGE_ID, BANDSINSTAGE_BAND_ID) values (5,7);
insert into STAGE_BANDSINSTAGE(STAGE_STAGE_ID, BANDSINSTAGE_BAND_ID) values (3,6);
insert into STAGE_BANDSINSTAGE(STAGE_STAGE_ID, BANDSINSTAGE_BAND_ID) values (4,4);

insert into bus(bus_id, capacity, destination, startinglocation, departuretime, journeytimeinminutes) values (BUS_SEQ.nextval, 100, 'T In The Park', 'Glasgow', parsedatetime('05 Aug 2020 02:00', 'dd MMM yyyy hh:mm'), 60);