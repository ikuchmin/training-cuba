-- update TRAINING_CAR_SERVICE_CENTER set OWNER_ID = <default_value> where OWNER_ID is null ;
alter table TRAINING_CAR_SERVICE_CENTER alter column OWNER_ID set not null ;
