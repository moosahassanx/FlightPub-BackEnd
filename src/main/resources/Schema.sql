
USE FlightPub;

CREATE TABLE Country (
  country_code2 char(2) NOT NULL,
  country_code3 char(3) NOT NULL,
  country_Name varchar(80) NOT NULL DEFAULT '',
  alternate_Name1 varchar(80) NOT NULL DEFAULT '',
  alternate_Name2 varchar(80) NOT NULL DEFAULT '',
  mother_Country_Code3 char(3) NOT NULL DEFAULT '',
  mother_Country_Comment varchar(80) NOT NULL DEFAULT '',
  PRIMARY KEY (country_Code3)
)

CREATE TABLE Airlines (
  Airline_Code char(10) NOT NULL,
  Airline_Name varchar(30) NOT NULL,
  Country_Code3 char(3) NOT NULL,
  PRIMARY KEY (Airline_Code),
  CONSTRAINT AirlinesCountryCode3_FK FOREIGN KEY (Country_Code3) REFERENCES Country (country_Code3)
)

CREATE TABLE Plane_Type (
  Plane_Code varchar(20) NOT NULL,
  Details varchar(50) NOT NULL,
  Num_First_Class int NOT NULL,
  Num_Business int NOT NULL,
  Num_Premium_Economy int NOT NULL,
  Economy int NOT NULL,
  PRIMARY KEY (Plane_Code)
)

CREATE TABLE Destinations (
  Destination_Code char(3) NOT NULL,
  Airport varchar(30) NOT NULL,
  Country_Code3 char(3) NOT NULL,
  Tags varchar(264),
  PRIMARY KEY (Destination_Code),
  CONSTRAINT DestinationCountryCode_FK FOREIGN KEY (Country_Code3) REFERENCES Country (country_Code3)
)

CREATE TABLE Ticket_Class (
  Class_Code char(10) NOT NULL,
  Details varchar(20) NOT NULL,
  PRIMARY KEY (Class_Code)
)

CREATE TABLE Ticket_Type (
  Ticket_Code char(1) NOT NULL,
  Name varchar(50) NOT NULL,
  Transferable bit NOT NULL,
  Refundable bit NOT NULL,
  Exchangeable bit NOT NULL,
  Frequent_Flyer_Points bit NOT NULL,
  PRIMARY KEY (Ticket_Code)
)

CREATE TABLE Availability (
  Airline_Code char(10) NOT NULL,
  Flight_Number varchar(15) NOT NULL,
  Departure_Time datetime NOT NULL,
  Class_Code char(10) NOT NULL,
  Ticket_Code char(1) NOT NULL,
  Number_Available_Seats_Leg1 int NOT NULL,
  Number_Available_Seats_Leg2 int DEFAULT NULL,
  PRIMARY KEY (Airline_Code,Flight_Number,Departure_Time,Class_Code,Ticket_Code),
  CONSTRAINT AvailabilityTicketCode_FK FOREIGN KEY (Ticket_Code) REFERENCES Ticket_Type (Ticket_Code),
  CONSTRAINT AvailabilityAirlineCode_FK FOREIGN KEY (Airline_Code) REFERENCES Airlines (Airline_Code),
  CONSTRAINT AvailabilityClassCode_FK FOREIGN KEY (Class_Code) REFERENCES Ticket_Class (Class_Code)
)

CREATE TABLE Distances (
  Destination_Code1 char(3) NOT NULL,
  Destination_Code2 char(3) NOT NULL,
  Distances_In_Kms int NOT NULL,
  PRIMARY KEY (Destination_Code1,Destination_Code2),
  CONSTRAINT DestinationCode2_FK FOREIGN KEY (Destination_Code2) REFERENCES Destinations (Destination_Code),
  CONSTRAINT DestinationCode1_FK FOREIGN KEY (Destination_Code1) REFERENCES Destinations (Destination_Code)
)

CREATE TABLE Price (
  Airline_Code char(10) NOT NULL,
  Flight_Number varchar(15) NOT NULL,
  Class_Code char(10) NOT NULL,
  Ticket_Code char(1) NOT NULL,
  Start_Date datetime NOT NULL,
  End_Date datetime NOT NULL,
  Price decimal(10,2) NOT NULL,
  Price_Leg1 decimal(10,2) DEFAULT NULL,
  Price_Leg2 decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (Airline_Code,Flight_Number,Class_Code,Ticket_Code,Start_Date),
  CONSTRAINT PriceAirlineCode_FK FOREIGN KEY (Airline_Code) REFERENCES Airlines (Airline_Code),
  CONSTRAINT PriceClassCode_FK FOREIGN KEY (Class_Code) REFERENCES Ticket_Class (Class_Code),
  CONSTRAINT PriceTicketCode_FK FOREIGN KEY (Ticket_Code) REFERENCES Ticket_Type (Ticket_Code)
)

CREATE TABLE Flights (
  Airline_Code char(10) NOT NULL,
  Flight_Number varchar(15) NOT NULL,
  Departure_Code char(3) NOT NULL,
  Stop_Over_Code char(3) DEFAULT NULL,
  Destination_Code char(3) NOT NULL,
  Departure_Time datetime NOT NULL,
  Arrival_Time_Stop_Over datetime DEFAULT NULL,
  Departure_Time_Stop_Over datetime DEFAULT NULL,
  Arrival_Time datetime NOT NULL,
  Plane_Code varchar(20) NOT NULL,
  Duration int NOT NULL,
  Duration_Second_Leg int DEFAULT NULL,
  PRIMARY KEY (Airline_Code,Flight_Number,Departure_Time),
  CONSTRAINT FlightsPlaneCode_FK FOREIGN KEY (Plane_Code) REFERENCES Plane_Type (Plane_Code),
  CONSTRAINT FlightsAirlineCode_FK FOREIGN KEY (Airline_Code) REFERENCES Airlines (Airline_Code),
  CONSTRAINT FlightsDepartureCode_FK FOREIGN KEY (Departure_Code) REFERENCES Destinations (Destination_Code),
  CONSTRAINT FlightsDestinationCode_FK FOREIGN KEY (Destination_Code) REFERENCES Destinations (Destination_Code),
  CONSTRAINT FlightsStopOverCode_FK FOREIGN KEY (Stop_Over_Code) REFERENCES Destinations (Destination_Code)
)


