create schema PetShelter1;


--Pet (id_pet, name, gender, species, adoption_status, breed, age)
create table pet(
id_pet serial primary key,
name varchar(20),
gender char(1) not null,
species varchar(20) not null,
adoption_status varchar(20) not null,
breed varchar(20),
age varchar(20) not null
constraint gender_constraint check(gender = 'M' or gender='F'),
constraint adoption_status_constraint check(adoption_status = 'Adopted' or adoption_status='Not adopted')
)
alter table pet
alter column adoption_status set default 'Not adopted';

insert into pet(name,gender,species,adoption_status,breed,age)
values
('Lesi','F','Dog','Not adopted','Corgi','3 months'),
('Bela','F','Cat','Not adopted','Persian cat','1 year'),
('Dzeki','M','Dog','Adopted','Golden retriever','10 months'),
('Lili','F','Cat','Not adopted','','2 years');

insert into pet(name,gender,species,adoption_status,breed,age)
values
('','F','Dog','Adopted','Corgi','3 months'),
('Krimi','F','Cat','Not adopted','Persian cat','5 year'),
('Gile','M','Dog','Adopted','','6 months'),
('Luna','F','Cat','Adopted','British Shorthair.','2 years');


--Shelter (id_shelter, name, location, capacity)
create table shelter(
id_shelter serial primary key,
name varchar(50) not null,
location_shelter varchar(100) not null,
capacity integer
);
insert into shelter(name, location_shelter,capacity)
values
('Paws Rescue','Skopje',60),
('Paws Guardian','Kavadarci',90),
('Furry Friend Rescue','Bitola',100);


--Person (personal_identification_number, name, surname)
create table person(
personal_identification_number varchar(20) primary key,
name varchar(30) not null,
surname varchar(30) not null
);
insert into person(personal_identification_number,name,surname)
values
('1231231231234','Aleksandar','Stojanov'),
('3213213213214','Petko','Petkov'),
('4441112223335','Snezana','Stojanova');

insert into person(personal_identification_number,name,surname)
values
('5555555555551','Aleksandar','Petkov'),
('5555555555552','Trajko','Trajkov'),
('5555555555553','Marija','Ristova');

insert into person(personal_identification_number,name,surname)
values
('5555555555554','Jovan','Pendev'),
('5555555555555','Sofija','Sekulovska'),
('5555555555556','Risto','Ristov');


--Adopter (personal_identification_number*(person), adoption_date)
create table adopter(
personal_identification_number varchar(20) primary key,
adoption_date date,
constraint fk_pin foreign key (personal_identification_number) references person(personal_identification_number)
);

insert into adopter(personal_identification_number,adoption_date)
values
('1231231231234','2023-10-25'),
('5555555555554','2023-8-10'),
('3213213213214','2022-5-01');


--Staff (personal_identification_number*(person))
create table staff(
personal_identification_number varchar(20) primary key,
constraint fk_pin foreign key (personal_identification_number) references person(personal_identification_number)
);
insert into staff(personal_identification_number)
values
('5555555555551'),
('5555555555552'),
('5555555555553');


--Donations (id_donations, personal_identification_number*(person), id_shelter*(shelter))
create table donations(
id_donations serial primary key,
personal_identification_number varchar(20) not null,
id_shelter integer,
constraint fk_pin foreign key (personal_identification_number) references person(personal_identification_number),
constraint fk_id_shelter foreign key (id_shelter) references shelter(id_shelter)
);
insert into donations(personal_identification_number ,id_shelter)
values
('4441112223335',1),
('5555555555556',2),
('5555555555553',3);


--Application (id_application, personal_identification_number_adopter*(adopter),
--personal_identification_number*(staff), id_pet*(pet))
create table application(
id_application serial primary key,
personal_identification_number_adopter varchar(20) not null,
personal_identification_number_staff varchar(20) not null,
id_pet integer,
constraint fk_pin_adopter foreign key (personal_identification_number_adopter) references adopter(personal_identification_number),
constraint fk_pin_staff foreign key (personal_identification_number_staff) references staff(personal_identification_number),
constraint fk_id_pet foreign key (id_pet) references pet(id_pet)
);
insert into application (personal_identification_number_adopter,personal_identification_number_staff,id_pet)
values
('1231231231234','5555555555551',1),
('5555555555554','5555555555552',2),
('3213213213214','5555555555551',4);
alter table application
alter column personal_identification_number_staff drop not null;

--Medical_records (id_records, medical_condition, date_of_examination, vaccinations,
--id_pet*(pet))
create table medical_records(
id_records serial primary key,
medical_condition varchar(100),
date_of_examination date not null,
vaccinations varchar(100) not null,
id_pet integer not null,
constraint fk_id_pet foreign key (id_pet) references pet(id_pet)
);
insert into medical_records (medical_condition, date_of_examination, vaccinations ,id_pet)
values
('','2023-11-26','Rabies and Parvovirus',1),
('Ear Infections','2023-01-23','Rabies and Parvovirus',2),
('Dental Problems','2023-06-05','Rabies',3),
('Dental Problems','2021-06-10','Rabies, Distemper and Parvovirus',4);

--Phone_number_person(personal_identification_number *(person), phone_number)
create table phone_number_person(
personal_identification_number varchar(20) not null,
phone_number varchar(30) not null,
constraint pk_phone_number_person primary key (personal_identification_number,phone_number),
constraint fk_pin foreign key (personal_identification_number) references person(personal_identification_number)
);
alter table phone_number_person drop column phone_number;
alter table phone_number_person add column person_phone_number varchar(30) not null;
alter table phone_number_person add constraint pk_phone_number_person primary key (personal_identification_number,person_phone_number);
insert into phone_number_person (personal_identification_number,person_phone_number)
values
('5555555555551','070777888'),
('5555555555552','070777999'),
('5555555555551','078777888');
select * from phone_number_person;


--Phone_number_shelter(id_shelter*(shelter), phone_number)
create table phone_number_shelter(
id_shelter integer not null,
shelter_phone_number varchar(30) not null,
constraint pk_phone_number_shelter primary key (id_shelter,shelter_phone_number),
constraint fk_id_shelter foreign key (id_shelter) references shelter(id_shelter)
);
insert into phone_number_shelter (id_shelter,shelter_phone_number)
values
(1,'071777321'),
(2,'070111999'),
(3,'078321123');


--Staff_looks_after_pet(personal_identification_number*(staff),id_pet*(pet))
create table staff_looks_after_pet(
personal_identification_number varchar(20) not null,
id_pet integer not null,
constraint pk_s_l_a_p primary key (personal_identification_number, id_pet),
constraint fk_pin foreign key (personal_identification_number) references staff(personal_identification_number),
constraint fk_id_pet foreign key (id_pet) references pet(id_pet)
);
insert into staff_looks_after_pet (personal_identification_number,id_pet)
values
('5555555555551',1),
('5555555555552',2),
('5555555555551',3),
('5555555555552',4);


--Adopter_adopts_pet(personal_identification_number*(adopter),id_pet*(pet)       ,adoption_date)
create table adopter_adopts_pet(
personal_identification_number varchar(20) not null,
id_pet integer not null,
constraint pk_a_a_p primary key (personal_identification_number, id_pet),
constraint fk_pin foreign key (personal_identification_number) references adopter(personal_identification_number),
constraint fk_id_pet foreign key (id_pet) references pet(id_pet)
);
insert into adopter_adopts_pet(personal_identification_number,id_pet)
values
('1231231231234',3),
('5555555555554',5),
('3213213213214',7);


--Pet_lives_at_shelter(id_pet*(pet),id_shelter*(shelter))
create table pet_lives_at_shelter(
id_pet integer not null,
id_shelter integer not null,
date_from date not null,
date_to date,
constraint fk_id_pet foreign key (id_pet) references pet(id_pet),
constraint fk_id_shelter foreign key (id_shelter) references shelter(id_shelter),
constraint pk_p_l_a_s primary key (id_pet,id_shelter)
);
insert into pet_lives_at_shelter (id_pet,id_shelter,date_from,date_to)
values
(1,1,'2023-9-22',null),
(2,2,'2022-10-21',null),
(3,1,'2023-01-02','2023-05-05'),
(4,3,'2021-01-15',null);
select * from pet p;


--Staff_works_at_shelter(personal_identification_number*(staff),id_shelter(shelter),date_from
--,date_to)
create table staff_works_at_shelter(
personal_identification_number varchar(20) not null,
id_shelter integer not null,
date_from date not null,
date_to date,
constraint fk_pin foreign key (personal_identification_number) references staff(personal_identification_number),
constraint fk_id_shelter foreign key (id_shelter) references shelter(id_shelter),
constraint pk_s_w_a_s primary key (personal_identification_number,id_shelter)
);
insert into staff_works_at_shelter (personal_identification_number,id_shelter,date_from,date_to)
values
('5555555555551',1,'2020-09-12',null),
('5555555555552',2,'2019-02-11',null),
('5555555555553',1,'2021-01-15',null);

-- Promena na adoption_date od adopter vo adopter_adopts_pet
alter table adopter_adopts_pet
add column adoption_date date;

update adopter_adopts_pet aap
set adoption_date = (
    select adoption_date
    from adopter a
    where aap.personal_identification_number = a.personal_identification_number
);

alter table adopter
drop column adoption_date;



--Fewer information about all pets
create view pet_less_info as
select pet.id_pet ,pet.name,date_of_birth,gender,species,breed
from pet
left join pet_lives_at_shelter plas on pet.id_pet=plas.id_pet;

--Information about all the pets
create view pet_more_info as
select pet.id_pet ,pet.name as pet_name,gender,species,adoption_status, 
breed, date_of_birth,mr.medical_condition ,mr.vaccinations , s.name as shelter_name,s.location_shelter
from pet
left join pet_lives_at_shelter plas on pet.id_pet=plas.id_pet
left join shelter s on plas.id_shelter = s.id_shelter
left join medical_records mr on pet.id_pet=mr.id_pet;

--Information about all the pets that are not adopted
create view not_adopted_pets as
select pet.id_pet ,pet.name as pet_name,gender,species,adoption_status, breed, date_of_birth,mr.medical_condition ,
mr.vaccinations , s.name as shelter_name,s.location_shelter
from pet
left join pet_lives_at_shelter plas on pet.id_pet=plas.id_pet
left join shelter s on plas.id_shelter = s.id_shelter
left join medical_records mr on pet.id_pet=mr.id_pet
where pet.adoption_status like 'Not adopted';

--Information about all the pets that are adopted
create view adopted_pets_less_info as
select pet.id_pet ,pet.name as pet_name,gender,species,adoption_status, breed, 
date_of_birth,mr.medical_condition ,mr.vaccinations , s.name as shelter_name,s.location_shelter,
		(p.name ||' '||p.surname) as Adopter
from pet
left join pet_lives_at_shelter plas on pet.id_pet=plas.id_pet
left join shelter s on plas.id_shelter = s.id_shelter
left join medical_records mr on pet.id_pet=mr.id_pet
left join adopter_adopts_pet aap on pet.id_pet =aap.id_pet 
left join adopter a on aap.personal_identification_number =a.personal_identification_number 
left join person p on aap.personal_identification_number  = p.personal_identification_number 
where pet.adoption_status like 'Adopted';

-- Information about all the donators and their donations
create view donations_and_donators as
select id_donations , (p."name" ||' '||p.surname) as Donator, s."name" as Shelter_name, s.location_shelter, pns.shelter_phone_number 
from donations d 
left join person p ON d.personal_identification_number  = p.personal_identification_number
left join shelter s on d.id_shelter =s.id_shelter
left join phone_number_shelter pns on s.id_shelter = pns.id_shelter;

-- Information about applications and applicants
create view applications_and_applicants as
select a.id_application, p."name" || ' '||p.surname as Applicants,p2."name" || ' '||p2.surname as Reviewed_by, pet.id_pet , pet."name", pet.adoption_status 
from application a 
left join adopter a2 on a.personal_identification_number_adopter=a2.personal_identification_number
left join person p on a2.personal_identification_number = p.personal_identification_number
left join staff s on a.personal_identification_number_staff =s.personal_identification_number
left join person p2 on s.personal_identification_number = p2.personal_identification_number
left join pet on a.id_pet = pet.id_pet;
