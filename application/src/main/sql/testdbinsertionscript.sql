/*
This file contains test data for the junit tests.
*/

/* Area office It belongs to the head office created by master scripts */
INSERT INTO OFFICE(OFFICE_ID, PARENT_OFFICE_ID, GLOBAL_OFFICE_NUM, STATUS_ID, OFFICE_CODE_ID, OFFICE_LEVEL_ID,  SEARCH_ID, OFFICE_SHORT_NAME, MAX_CHILD_COUNT,LOCAL_REMOTE_FLAG, DISPLAY_NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, VERSION_NO)
 		VALUES(2,1,'0002',1,NULL,4,'1.1.1.','MIF2',2,1,'TestAreaOffice',NULL,NULL,NULL,NULL,1);
 		
INSERT INTO OFFICE_ADDRESS (OFFICE_ADDRESS_ID, OFFICE_ID, ADDRESS_1, ADDRESS_2, ADDRESS_3, 
CITY, STATE, COUNTRY, ZIP, TELEPHONE) 
VALUES(2,2,'add2',null,null,null,null,null,null,null);

/* Branch office It belongs to the area office created by test script statements above*/
INSERT INTO OFFICE(OFFICE_ID, PARENT_OFFICE_ID, GLOBAL_OFFICE_NUM, STATUS_ID, OFFICE_CODE_ID, OFFICE_LEVEL_ID,  SEARCH_ID, OFFICE_SHORT_NAME, MAX_CHILD_COUNT,LOCAL_REMOTE_FLAG, DISPLAY_NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, VERSION_NO)
 		VALUES(3,2,'0003',1,NULL,5,'1.1.1.1.','MIF3',2,1,'TestBranchOffice',NULL,NULL,NULL,NULL,1); 		

INSERT INTO OFFICE_ADDRESS (OFFICE_ADDRESS_ID, OFFICE_ID, ADDRESS_1, ADDRESS_2, ADDRESS_3, 
CITY, STATE, COUNTRY, ZIP, TELEPHONE) 
VALUES(3,3,'add3',null,null,null,null,null,null,null);


 
 /*Non loan officer belonging to branch office with office id 3 */		
INSERT INTO PERSONNEL(PERSONNEL_ID,LEVEL_ID,GLOBAL_PERSONNEL_NUM,OFFICE_ID,
	TITLE,PERSONNEL_STATUS,PREFERRED_LOCALE,
	SEARCH_ID,MAX_CHILD_COUNT,PASSWORD,LOGIN_NAME,EMAIL_ID,PASSWORD_CHANGED,
	DISPLAY_NAME,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE,
	NO_OF_TRIES,LAST_LOGIN,LOCKED,VERSION_NO)
VALUES(2,2,'2',3,
	1,1,1,
	null,1,null,'nonloanofficer',NULL,1,
	'non loan officer',1,NULL,1,NULL,
	4,NULL,0,0);

INSERT INTO PERSONNEL_DETAILS VALUES(2,'testnon loan officer',NULL,NULL,'MFI','123','1979-12-12',NULL,50,NULL,NULL,NULL,'Bangalore',NULL,NULL,'Bangalore','Bangalore','Bangalore',null,NULL);	

INSERT INTO PERSONNEL_ROLE (PERSONNEL_ROLE_ID, ROLE_ID, PERSONNEL_ID) 
VALUES(2,1,2);
/*Loan officer belonging to branch*/
INSERT INTO PERSONNEL(PERSONNEL_ID,LEVEL_ID,GLOBAL_PERSONNEL_NUM,OFFICE_ID,
	TITLE,PERSONNEL_STATUS,PREFERRED_LOCALE,
	SEARCH_ID,MAX_CHILD_COUNT,PASSWORD,LOGIN_NAME,EMAIL_ID,PASSWORD_CHANGED,
	DISPLAY_NAME,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE,
	NO_OF_TRIES,LAST_LOGIN,LOCKED,VERSION_NO) 
VALUES(3,1,'3',3,
	1,1,1,
	null,1,null,'loanofficer',NULL,1,
	'loan officer',1,NULL,1,NULL,
	4,NULL,0,0);

INSERT INTO PERSONNEL_DETAILS VALUES(3,'testloan officer',NULL,NULL,'MFI','123','1979-12-12',NULL,50,NULL,NULL,NULL,'Bangalore',NULL,NULL,'Bangalore','Bangalore','Bangalore',null,NULL);

INSERT INTO PERSONNEL_ROLE (PERSONNEL_ROLE_ID, ROLE_ID, PERSONNEL_ID) 
VALUES(3,1,3);

INSERT INTO ROLE(ROLE_ID,ROLE_NAME,VERSION_NO,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE)
VALUES(2,'SavingTestPermisson',1,NULL,NULL,NULL,NULL);
INSERT INTO ROLES_ACTIVITY(ACTIVITY_ID,ROLE_ID)
VALUES(182,2);
INSERT INTO ROLES_ACTIVITY(ACTIVITY_ID,ROLE_ID)
VALUES(183,2);

UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'SecondLastName' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'SecondLastName' AND ENTITY_ID = 17;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'GovernmentId' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'GovernmentId' AND ENTITY_ID = 17;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'ExternalId' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'ExternalId' AND ENTITY_ID = 12;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'ExternalId' AND ENTITY_ID = 20;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'ExternalId' AND ENTITY_ID = 22;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Ethinicity' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Citizenship' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Handicapped' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'BusinessActivities' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'EducationLevel' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Photo' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'SpouseFatherSecondLastName' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Trained' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Trained' AND ENTITY_ID = 12;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'TrainedDate' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'TrainedDate' AND ENTITY_ID = 12;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Address1' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Address1' AND ENTITY_ID = 12;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Address1' AND ENTITY_ID = 20;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Address2' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Address2' AND ENTITY_ID = 12;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Address2' AND ENTITY_ID = 20;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Address3' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Address3' AND ENTITY_ID = 12;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Address3' AND ENTITY_ID = 20;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Address3' AND ENTITY_ID = 15;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Address3' AND ENTITY_ID = 17;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'City' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'City' AND ENTITY_ID = 12;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'City' AND ENTITY_ID = 20;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'State' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'State' AND ENTITY_ID = 12;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'State' AND ENTITY_ID = 20;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Country' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Country' AND ENTITY_ID = 12;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'Country' AND ENTITY_ID = 20;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'PostalCode' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'PostalCode' AND ENTITY_ID = 12;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 0
  WHERE FIELD_NAME = 'PostalCode' AND ENTITY_ID = 20;  
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'PhoneNumber' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'PhoneNumber' AND ENTITY_ID = 12;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'PhoneNumber' AND ENTITY_ID = 20;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'PhoneNumber' AND ENTITY_ID = 17;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 0
  WHERE FIELD_NAME = 'PurposeOfLoan' AND ENTITY_ID = 22;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'CollateralType' AND ENTITY_ID = 22;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'CollateralNotes' AND ENTITY_ID = 22;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'ReceiptId' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'ReceiptId' AND ENTITY_ID = 12;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'ReceiptId' AND ENTITY_ID = 20;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'ReceiptId' AND ENTITY_ID = 21;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'ReceiptId' AND ENTITY_ID = 22;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'ReceiptId' AND ENTITY_ID = 23;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'ReceiptDate' AND ENTITY_ID = 1;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'ReceiptDate' AND ENTITY_ID = 12;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'ReceiptDate' AND ENTITY_ID = 20;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'ReceiptDate' AND ENTITY_ID = 21;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'ReceiptDate' AND ENTITY_ID = 22;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 0, HIDDEN_FLAG = 1
  WHERE FIELD_NAME = 'ReceiptDate' AND ENTITY_ID = 23;
UPDATE FIELD_CONFIGURATION SET MANDATORY_FLAG = 1, HIDDEN_FLAG = 0
  WHERE FIELD_NAME = 'SourceOfFund' AND ENTITY_ID = 22;

/* make a listdatasource for test*/
Insert into report_datasource (DATASOURCE_ID,NAME,DRIVER,URL,USERNAME,PASSWORD) values(1,'test','test','test
','test','test');

/* retain default custom fields for testing purposes */
INSERT INTO LOOKUP_ENTITY(ENTITY_ID,ENTITY_NAME,DESCRIPTION)VALUES
(58,'ReplacementStatus','Custom Field ReplacementStatus for Client'),
(59,'GRTStaffId','Custom Field GRTStaffId for Group'),
(60,'MeetingTime','Custom Field Meeting Time for Center'),
(61,'DistanceFromBoToCenter','Custom Field Distance from BO To Center'),
(63,'NoOfClientsPerGroup','Custom Field  No. of Clients per Group'),
(64,'NoOfClientsPerCenter','Custom Field No. of Clients per Center'),
(65,'DistanceFromHoToBO','Custom Field Distance from HO To BO for office'),
(66,'ExternalLoanId','Custom Field ExternalID for office'),
(67,'ExternalSavingsId','Custom Field ExternalSavingsId');


INSERT INTO LOOKUP_LABEL(LABEL_ID,ENTITY_ID,LOCALE_ID,ENTITY_NAME)VALUES
(108,58,1,'Replacement Status'),
(110,59,1,'GRT Staff Id'),
(112,60,1,'Meeting Time for Center'),
(114,61,1,'Distance from BO to Center'),
(118,63,1,'Number of Clients per Group'),
(120,64,1,'Number of Clients per Center'),
(122,65,1,'Distance from HO to BO for office'),
(124,66,1,'External Loan Id'),
(126,67,1,'External Savings Id');


/* The table Custom Field Definition will contain the additional information fields that an MFI configure Mifosthat will be required to be shown for a client , group etc for the MFI - Configuration */
/* Client*/
INSERT INTO CUSTOM_FIELD_DEFINITION(FIELD_ID,ENTITY_ID,FIELD_TYPE,ENTITY_TYPE,MANDATORY_FLAG,LEVEL_ID,DEFAULT_VALUE) VALUES 
(3,58,2,1,0,1,NULL),
/*Group*/
(4,59,2,12,0,2,NULL),
/*Center*/
(5,60,2,20,0,3,NULL),
(6,61,1,20,0,3,NULL),
/*Personnel*/
(9,62,2,17,0,1,NULL),
/*Office*/
(10,62,2,15,0,1,NULL),
(11,63,1,15,1,2,NULL),
(12,64,1,15,1,2,NULL),
(13,65,1,15,0,2,NULL),
/*Loan*/
(7,66,2,22,0,1,NULL),
/*Savings*/
(8,67,2,21,0,1,NULL);

/* The table Personnelcustom field contains the additional information of the default user  - Configuration */
INSERT INTO PERSONNEL_CUSTOM_FIELD (FIELD_ID,PERSONNEL_ID,FIELD_VALUE)
VALUES(9,1,'');

INSERT INTO OFFICE_CUSTOM_FIELD (OFFICE_ID,FIELD_ID,FIELD_VALUE) VALUES
(1,10,''),
(1,11,''),
(1,12,''),
(1,13,'');


