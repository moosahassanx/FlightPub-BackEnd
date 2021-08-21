  USE FlightPub;

UPDATE Availability
SET departure_time = DATEADD(YEAR, 1, departure_time)

UPDATE Price
SET start_date=DATEADD(YEAR, 1, start_date),
    end_date=DATEADD(YEAR, 1, end_date)

UPDATE Flights
SET departure_time=DATEADD(YEAR, 1, departure_time),
    arrival_time=DATEADD(YEAR, 1, arrival_time)


UPDATE Flights
SET arrival_time_stop_over=DATEADD(YEAR, 1, arrival_time_stop_over),
    departure_time_stop_over=DATEADD(YEAR, 1, departure_time_stop_over)
WHERE arrival_time_stop_over IS NOT NULL;
