drop table if it exists ers_reimbursement_types;
drop table if it exists ers_reimbursement_statuses;
drop table if it exists ers_reimbursements;
drop table if it exists ers_users;
drop table if it exists ers_user_roles;

Create table ers_user_roles(
Role_id		VARCHAR,
Role			VARCHAR
);
Create table ers_users(
User_id		VARCHAR,
Username		VARCHAR unique not null,
Email			VARCHAR unique not null,
Password		VARCHAR not null,
Given_name		VARCHAR not null,
Surname		VARCHAR not null,
Is¬_active		Boolean,
Role_id		VARCHAR,

Constraint ers_users_pk
Primary key (User_id)
);
Alter table ers_users
Add column Password VARCHAR not null;
Alter table er_users
Add foreign key(Role_id)
References ers_user_roles(Role_id);

Create table ers_reimbursements(
Reimb_id		VARCHAR primary key,
Amount		NUMBERS(6,2) not null,
Submitted		TIMESTAMP not null,
Resolved		TIMESTAMP,
Description		VARCHAR not null,
Receipt		BLOB,
Payment_id		VARCHAR,
Author_id		VARCHAR not null,
Resolver_id		VARCHAR,
Status_id		VARCHAR not null
Type_id		VARCHAR not null,

Constraint Author_fk
Foreign key (Author_id)
References ers_users(user_id),
Constraint Resolver_fk
Foreign key (Resolver_id)
References ers_users(user_id)
);
Create table ers_reimbursement_statuses(
Status_id		VARCHAR primary key,
Status		VARCHAR unique
);

Create table ers_reimbursement_types(
Type_id		VARCHAR primary key,
Type			VARCHAR unique
);
