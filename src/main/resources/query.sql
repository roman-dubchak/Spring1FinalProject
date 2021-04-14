alter table customer_liq rename to person_liq;

create table public.role (id serial primary key , name varchar(50) not null);
insert into public.role (name) values ('ROLE_CUSTOMER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

create table public.person_role (person_id uuid references  public.person_liq (id),
                                 role_id bigint references public.role (id));

insert into person_role (person_id, role_id) values
                                ('7849c2a4-9cf9-44e3-8500-b400d3dd6292', 1),
                                ('6d56466a-b5eb-4f6d-b773-3b7b3a7a5adb', 1),
                                ('7f284533-4bac-4876-a429-f62f2807b753', 1),
                                ('ab499b2c-c198-4663-8598-101f9b8c8857', 1),
                                ('017bc911-5bfc-48dc-858d-6688d7553c97', 1),
                                ('6bed778b-5709-4770-991e-b38e8eb91389', 1),
                                ('3edd5a7b-68ed-47cd-afa4-60147c8fa80a', 1);


alter table person_liq
    add column login varchar (50),
add column password varchar(50);

ALTER TABLE person_liq
    ADD CONSTRAINT uniq_login_person UNIQUE(login);

ALTER TABLE person_liq
    ALTER COLUMN login SET NOT NULL,
ALTER COLUMN password SET NOT NULL;

alter table public.person_liq
    add column role_id bigint references public.role (id);

ALTER TABLE public.person_liq
    ALTER COLUMN role_id SET NOT NULL;

// меняем UUID -> Long

ALTER TABLE public.person_liq
DROP COLUMN id;

Alter table public.person_role
DROP constraint person_role_person_id_fkey;

ALTER TABLE public.person_role
DROP COLUMN person_id;

create table public.person_role (person_id bigint references  public.person_liq (id),
                                 role_id bigint references public.role (id));


insert into public.person_role (person_id, role_id)
values (1, 1), (2, 2), (3, 3);