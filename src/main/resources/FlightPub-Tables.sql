create table airlines
(
    airline_code  nvarchar(100) not null
        primary key,
    sponsored     bit          not null default 0,
    airline_name  varchar(255) not null,
    country_code3 varchar(255) not null
)
go

create table guest_user_account
(
    id            bigint identity
        primary key nonclustered,
    email_address     varchar(255)                 not null,
    first_name    varchar(255)                     not null,
    last_name     varchar(255)                     not null,
    phone_number  varchar(50)                      not null
)
go

create table country
(
    country_code3          varchar(255) not null
        primary key,
    alternate_name1        varchar(255) not null,
    alternate_name2        varchar(255) not null,
    country_code2          varchar(255) not null,
    country_name           varchar(255) not null,
    mother_country_code3   varchar(255) not null,
    mother_country_comment varchar(255) not null
)
go

create table destinations
(
    destination_code varchar(255)  not null
        primary key,
    airport          varchar(255)  not null,
    country_code3    varchar(255)  not null
        constraint [DestinationCountryCode_FK ]
            references country,
    times_booked     int default 0 not null,
    Tags varchar(264),
    COVID     bit          not null default 0

)
go

create table distances
(
    destination_code1 varchar(255) not null
        constraint [DestinationCode1_FK ]
            references destinations,
    destination_code2 varchar(255) not null
        constraint [DestinationCode2_FK ]
            references destinations,
    distance_in_kms   int          not null,
    primary key (destination_code1, destination_code2)
)
go

create table payment
(
    payment_id bigint  identity       not null
        constraint payment_pk
            primary key nonclustered,
    guest_user_id               bigint
        references guest_user_account,
    price      decimal(10, 2) not null,
    user_id    bigint
)
go

create unique index payment_payment_id_uindex
    on payment (payment_id)
go

create table plane_type
(
    plane_code          varchar(255) not null
        primary key,
    details             varchar(255) not null,
    economy             int          not null,
    num_business        int          not null,
    num_first_class     int          not null,
    num_premium_economy int          not null
)
go

create table flights
(
    airline_code             nvarchar(100) not null
        constraint [FlightsAirlineCode_FK ]
            references airlines,
    flight_number            varchar(255) not null,
    departure_code           varchar(255) not null
        constraint [FlightsDepartureCode_FK ]
            references destinations,
    stop_over_code           varchar(255)
        constraint [FlightsStopOverCode_FK ]
            references destinations,
    destination_code         varchar(255) not null
        constraint [FlightsDestinationCode_FK ]
            references destinations,
    departure_time           datetime2    not null,
    arrival_time_stop_over   datetime2,
    departure_time_stop_over datetime2,
    arrival_time             datetime2    not null,
    plane_code               varchar(255) not null
        constraint [FlightsPlane_Code_FK ]
            references plane_type,
    duration                 int          not null,
    duration_second_leg      int,
        primary key (airline_code, departure_time, flight_number)
)
go

create table ticket_class
(
    class_code varchar(255) not null
        primary key,
    details    varchar(255) not null
)
go

create table ticket_type
(
    ticket_code           varchar(255) not null
        primary key,
    exchangeable          bit          not null,
    frequent_flyer_points bit          not null,
    name                  varchar(255) not null,
    refundable            bit          not null,
    transferable          bit          not null
)
go

create table availability
(

    airline_code                nvarchar(100) not null
            references airlines,
    flight_number               varchar(255) not null,
    departure_time              DATETIME2 not null,
    class_code                  varchar(255) not null,
    ticket_code                 varchar(255) not null
            references ticket_type,
    number_available_seats_leg1 int          not null,
    number_available_seats_leg2 int,
    primary key (airline_code, class_code, departure_time, flight_number, ticket_code)
)
go

create table price
(
    airline_code  nvarchar(100)   not null,
    flight_number varchar(15)    not null,
    class_code    varchar(255)   not null,
    ticket_code   varchar(255)   not null,
    start_date    DATETIME2       not null,
    end_date      DATETIME2       not null,
    total_price   decimal(10, 2) not null,
    price_leg1    decimal(10, 2) default NULL,
    price_leg2    decimal(10, 2) default NULL,
    primary key (airline_code, flight_number, class_code, ticket_code, start_date)
)
go

create table user_account
(
    id            bigint identity
        constraint User_pk
            primary key nonclustered,
    user_name     varchar(255)                 not null,
    first_name    varchar(255)                 not null,
    last_name     varchar(255)                 not null,
    account_type  varchar(255) default 'basic' not null,
    salt          nvarchar(50),
    password_hash varchar(255)                 not null,
    phone_number  varchar(50)                      not null,
    address       varchar(255)                 not null,
    last_location varchar(255),
    requesting    BIT                           DEFAULT 0,
    why           VARCHAR(255),
    referencing   VARCHAR(255),
    experience    VARCHAR(255),
    requesting_for VARCHAR(255),
)
go


create table booking
(
    book_id               bigint identity,
    flight_number         varchar(255)   not null,
    payment_complete      varchar(255)   not null,
    payment_id            bigint
        constraint FK70t92vvx289ayx2hq2v4hdcjl
            references payment,
    user_id               bigint
        constraint FK9i7s2ny2t9tifoy03c0a6c42d
            references user_account,
    guest_user_id               bigint
            references guest_user_account,
    airline_code          nvarchar(100) not null,
    flight_departure_time datetime2      not null,
    primary key (book_id, flight_number),
    constraint FK6b3c9jl9kem8r91to5p1r54p8
        foreign key (airline_code, flight_departure_time, flight_number) references flights
)
go

create table holiday_package
(
    holiday_package_id      int             not null,
    airline_code            nvarchar(100)    not null,
    airline_name            varchar(255)    not null,
    destination_code        varchar(255)    not null,
    airport                 varchar(255)    not null,
    discount_percentage     int             not null,
    hotel_name              varchar(255),
    hotel_star_rating       int,
    hotel_description       varchar(255),
    package_start_datetime  datetime2       not null,
    package_end_datetime    datetime2       not null,
    target_user             varchar(255)    not null,
    primary key (holiday_package_id),
    CONSTRAINT holiday_package_airline_code_FK FOREIGN KEY (airline_code) REFERENCES airlines (airline_code),
    CONSTRAINT holiday_package_destination_code_FK FOREIGN KEY (destination_code) REFERENCES destinations (destination_code)
)
go

create table wishlist
(
	wishlist_id			bigint identity,
	user_id				bigint
		constraint wishlist_user_id_FK
			references user_account,
	country_code3          varchar(255)
		constraint wishlist_country_code3_FK
            references destinations,
	primary key (wishlist_id, user_id, country_code3)
)
go