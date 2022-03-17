insert into ers_user_roles
values
	('7c3521f5-ff75-4e8a-9913-01d15ee4dc96', 'ADMIN'),
	('7c3521f5-ff75-4e8a-9913-01d15ee4dc97', 'FINANCIAL_MANAGER'),
	('7c3521f5-ff75-4e8a-9913-01d15ee4dc98', 'EMPLOYEE');

insert into ers_users 
values
      ('1','aiza123','aiza@gmail.com','p4$$W0RD','aiza','bob',true,'7c3521f5-ff75-4e8a-9913-01d15ee4dc98'),
      ('2','ron123','ron@gmail.com','p4$$W0RD','aiza','bob',true,'7c3521f5-ff75-4e8a-9913-01d15ee4dc98'),
      ('3','ray123','ray@gmail.com','p4$$W0RD','aiza','bob',false,'7c3521f5-ff75-4e8a-9913-01d15ee4dc98'),
      ('4','billy123','billy@gmail.com','p4$$W0RD','aiza','bob',true,'7c3521f5-ff75-4e8a-9913-01d15ee4dc98'),
      ('5','admin123','admin@gmail.com','p4$$W0RD','aiza','bob',true,'7c3521f5-ff75-4e8a-9913-01d15ee4dc96');
   select * from ers_users;
  select * from ers_user_roles;
  
 insert into ers_reimbursements
 values
 	('10',100.0,'2012-03-12','2012-03-15','','1','11','111','1111','11111'),
 	('20',200.0,'2012-04-12','2012-04-15','','2','22','222','2222','22222'),
 	('30',300.0,'2012-05-12','2012-05-15','','3','33','333','3333','33333');
 	select * from ers_reimbursements;