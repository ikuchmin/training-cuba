update TRAINING_CAR_SERVICE_CENTER set OWNER_ID = '21669d46-1c67-00ae-2c23-3b34dfa4254e' where OWNER_ID is null ;
alter table TRAINING_CAR_SERVICE_CENTER alter column OWNER_ID set not null ;
