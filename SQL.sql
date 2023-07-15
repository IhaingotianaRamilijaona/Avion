create user avion identified by avion;
grant dba to avion;

CREATE SEQUENCE seq_lieu START WITH 1 INCREMENT BY 1 NOCACHE;

create table Lieu(
    idLieu int not null primary key,
    nomLieu varchar(100)
);
insert into Lieu values(seq_lieu.nextval,'Madagascar');
insert into Lieu values(seq_lieu.nextval,'France');
insert into Lieu values(seq_lieu.nextval,'Allemagne');
insert into Lieu values(seq_lieu.nextval,'USA');

create table type_Avion(
    idType_avion int not null primary key,
    Nom_type_Avion varchar(100),
    distance DECIMAL(38,2)
);

insert into type_Avion values(1,'Moyen courrier',100);
insert into type_Avion values(2,'Long courrier',200);

CREATE SEQUENCE seq_avion START WITH 1 INCREMENT BY 1 NOCACHE;

create table Avion(
    idAvion int not null primary key,
    capacite int,
    nbEconom int,
    nbBusiness int,
    modele varchar(100),
    typeAvion int,
    foreign key (typeAvion) references type_Avion(idType_avion)
);
insert into avion values(seq_avion.nextval,30,15,15,'boeing',2);
insert into avion values(seq_avion.nextval,25,15,10,'airbus',2);
insert into avion values(seq_avion.nextval,25,15,10,'aircraft',1);

create view get_Avion as
select     
a.idAvion,
a.capacite,
a.nbEconom,
a.nbBusiness,
a.modele,
t.idType_avion,
t.Nom_type_Avion,
t.distance
from Avion a 
join type_Avion t 
on a.typeAvion  = t.idType_avion;  

CREATE SEQUENCE seq_Place START WITH 1 INCREMENT BY 1 NOCACHE;

create table type_place(
    idType_place int not null primary key,
    Nom_place varchar(100)
);

insert into type_place values(seq_Place.nextval,'Economique');
insert into type_place values(seq_Place.nextval,'Business');

CREATE SEQUENCE seq_vol START WITH 1 INCREMENT BY 1 NOCACHE;

create table Vol(
    idVol int not null primary key,
    idAvion int,
    LieuDepart int,
    LieuArrive int,
    DateHeureDepart TIMESTAMP,
    DateValidation TIMESTAMP,
    DateAnnulation TIMESTAMP,
    foreign key (idAvion) references Avion(idAvion),
    foreign key (LieuDepart) references Lieu(idLieu),
    foreign key (LieuArrive) references Lieu(idLieu)
);
insert into vol values(seq_vol.nextval,2,2,3,TO_TIMESTAMP('2023-07-27 13:00:00', 'YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2023-07-01 5:00:00', 'YYYY-MM-DD HH24:MI:SS'),null);
insert into vol values(seq_vol.nextval,3,2,4,TO_TIMESTAMP('2023-07-12 16:00:00', 'YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2023-06-20 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),null);
insert into vol values(seq_vol.nextval,3,2,4,TO_TIMESTAMP('2023-06-04 12:00:00', 'YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2023-05-02 22:00:00', 'YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2023-05-26 13:00:00', 'YYYY-MM-DD HH24:MI:SS'));

create table tarif_vol(
    idVol int,
    idType_place int,
    montant int,
    foreign key (idVol) references Vol(idVol),
    foreign key (idType_place) references type_place(idType_place)
);

insert into tarif_vol values(2,2,1000);
insert into tarif_vol values(2,3,2000);
insert into tarif_vol values(3,2,500);
insert into tarif_vol values(3,3,2500);
insert into tarif_vol values(4,2,600);
insert into tarif_vol values(4,3,800);

create view get_Vol_and_Tarif as 
select 
v.idVol,
v.idAvion,
v.LieuDepart,
v.LieuArrive,
v.DateHeureDepart,
v.DateValidation,
v.DateAnnulation,
tp.idType_place,
tp.Nom_place,
tv.montant
from Vol v
JOIN tarif_vol tv
ON v.idVol = tv.idVol 
JOIN type_place tp
ON tp.idType_place = tv.idType_place;

CREATE SEQUENCE seq_remise START WITH 1 INCREMENT BY 1 NOCACHE;

create table Remise(
    idRemise int not null primary key,
    idVol int,
    pourcentage_place int,
    pourcentage_remise int,
    type_place int,
    DateLimite TIMESTAMP,
    foreign key (idVol) references Vol(idVol),
    foreign key (type_place) references type_place(idType_place)
);

CREATE SEQUENCE seq_reservation START WITH 1 INCREMENT BY 1 NOCACHE;

create table Reservation(
    idReservation int not null primary key,
    idVol int,
    typePlace int,
    token varchar(100),
    foreign key (idVol) references Vol(idVol),
    foreign key (typePlace) references type_place(idType_place)
);

insert into Reservation values(seq_reservation.nextval,2,2,'blablabla');
insert into Reservation values(seq_reservation.nextval,2,2,'blablabla');
insert into Reservation values(seq_reservation.nextval,2,2,'blablabla');
insert into Reservation values(seq_reservation.nextval,2,3,'yououo');
insert into Reservation values(seq_reservation.nextval,2,3,'yououo');
insert into Reservation values(seq_reservation.nextval,2,2,'kiki');
insert into Reservation values(seq_reservation.nextval,2,3,'dedede');

insert into Reservation values(seq_reservation.nextval,3,3,'kouskous');
insert into Reservation values(seq_reservation.nextval,3,3,'kouskous');
insert into Reservation values(seq_reservation.nextval,3,3,'kouskous');
insert into Reservation values(seq_reservation.nextval,3,2,'masmas');
insert into Reservation values(seq_reservation.nextval,3,3,'lakas');
insert into Reservation values(seq_reservation.nextval,3,2,'jeanjean');
insert into Reservation values(seq_reservation.nextval,3,3,'glouglou');


create view vol_nb_place_reservee as 
select 
v.idvol,
a.idAvion,
a.capacite,
a.nbEconom,
a.nbBusiness,
count(r.idReservation) as nb_reservation,
a.capacite-count(r.idReservation) as nb_place_libre
from Reservation r
join vol v
on r.idVol = v.idvol
join avion a
on v.idAvion = a.idAvion
group by v.idvol,a.idAvion,a.capacite,a.nbEconom,a.nbBusiness;

-- create view vol_dispo as 
-- select vt.idvol
-- from get_Vol_and_Tarif vt 
-- join vol_nb_place_reservee nb_p
-- on nb_p.idvol = vt.idvol
-- where vt.DateAnnulation IS NULL 
-- AND vt.DateValidation is not null
-- AND nb_p.idvol > 0 
-- AND vt.DateHeureDepart > SYSTIMESTAMP
-- group by vt.idvol;

create view vol_dispo as 
select vt.idvol
from get_Vol_and_Tarif vt 
join vol_nb_place_reservee nb_p
on nb_p.idvol = vt.idvol
where vt.DateAnnulation IS NULL 
AND vt.DateValidation is not null
AND nb_p.idvol > 0 
group by vt.idvol;

create view vol_Non_valide as 
select vt.idvol
from get_Vol_and_Tarif vt 
join vol_nb_place_reservee nb_p
on nb_p.idvol = vt.idvol
WHERE vt.DateValidation is NULL
group by vt.idvol;

create view vol_Annule as 
select vt.idvol
from get_Vol_and_Tarif vt 
join vol_nb_place_reservee nb_p
on nb_p.idvol = vt.idvol
WHERE vt.DateAnnulation is NOT NULL
group by vt.idvol;

-- search vol --
SELECT *
FROM your_table
WHERE TRUNC(your_timestamp_column) = TO_DATE('yyyy-mm-dd', 'YYYY-MM-DD');


-- SELECT TO_CHAR(SYSTIMESTAMP, 'YYYY-MM-DD HH24:MI:SS') FROM dual;