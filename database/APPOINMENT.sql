/*
 Navicat Premium Data Transfer

 Source Server         : 10.10.103.61
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 10.10.103.61:33008
 Source Schema         : APPOINMENT

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 01/03/2019 11:08:16
*/


-- ----------------------------
-- Table structure for appoinment
-- ----------------------------

create user APPOINMENT identified by APPOINMENT
ALTER USER APPOINMENT quota unlimited on SYSTEM

DROP TABLE "APPOINMENT"."appoinment";
CREATE TABLE "APPOINMENT"."appoinment" (
  "appointment_id" NUMBER NOT NULL ,
  "patient_id" NUMBER ,
  "clinic_id" NUMBER ,
  "encounter_type_id" NUMBER ,
  "room_id" NUMBER ,
  "appointment_date" VARCHAR2(100 BYTE) ,
  "status" VARCHAR2(255 BYTE) ,
  "attendance_status" VARCHAR2(255 BYTE) ,
  "attendance_time" VARCHAR2(100 BYTE) ,
  "patient_doc" VARCHAR2(100 BYTE) ,
  "patient_name" VARCHAR2(100 BYTE) ,
  "encounter_type_name" VARCHAR2(100 BYTE) ,
  "room_name" VARCHAR2(100 BYTE) ,
  "clinic_name" VARCHAR2(100 BYTE) 
)
TABLESPACE "SYSTEM"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  FREELISTS 1
  FREELIST GROUPS 1
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "APPOINMENT"."appoinment"."appointment_id" IS 'Appointment id';
COMMENT ON COLUMN "APPOINMENT"."appoinment"."patient_id" IS 'Patient id（foreign key）';
COMMENT ON COLUMN "APPOINMENT"."appoinment"."clinic_id" IS 'Clinic id';
COMMENT ON COLUMN "APPOINMENT"."appoinment"."encounter_type_id" IS 'Encounter type id（foreign key）';
COMMENT ON COLUMN "APPOINMENT"."appoinment"."room_id" IS 'Room id';
COMMENT ON COLUMN "APPOINMENT"."appoinment"."appointment_date" IS 'Appointment date';
COMMENT ON COLUMN "APPOINMENT"."appoinment"."status" IS 'status';
COMMENT ON COLUMN "APPOINMENT"."appoinment"."attendance_status" IS 'Attendance or not attendance';
COMMENT ON COLUMN "APPOINMENT"."appoinment"."attendance_time" IS 'Attendance time';

-- ----------------------------
-- Table structure for appoinment_quota
-- ----------------------------
DROP TABLE "APPOINMENT"."appoinment_quota";
CREATE TABLE "APPOINMENT"."appoinment_quota" (
  "appointment_quota_id" NUMBER NOT NULL ,
  "clinic_id" NUMBER ,
  "encounter_type_id" NUMBER ,
  "room_id" NUMBER ,
  "appointment_date" VARCHAR2(100 BYTE) ,
  "quota" NUMBER ,
  "quota_booked" NUMBER 
)
TABLESPACE "SYSTEM"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  FREELISTS 1
  FREELIST GROUPS 1
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for encounter
-- ----------------------------
DROP TABLE "APPOINMENT"."encounter";
CREATE TABLE "APPOINMENT"."encounter" (
  "encounter_id" NUMBER NOT NULL ,
  "patient_id" NUMBER ,
  "encounter_type_id" NUMBER ,
  "clinic_id" NUMBER ,
  "room_id" NUMBER ,
  "date_time" VARCHAR2(100 BYTE) ,
  "appointment_id" NUMBER 
)
TABLESPACE "SYSTEM"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  FREELISTS 1
  FREELIST GROUPS 1
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "APPOINMENT"."encounter"."patient_id" IS 'Patient id（foreign key）';
COMMENT ON COLUMN "APPOINMENT"."encounter"."encounter_type_id" IS 'Encounter type id（foreign key）';
COMMENT ON COLUMN "APPOINMENT"."encounter"."clinic_id" IS 'Clinic id（foreign key）';
COMMENT ON COLUMN "APPOINMENT"."encounter"."room_id" IS 'Room id（foreign key）';
COMMENT ON COLUMN "APPOINMENT"."encounter"."date_time" IS 'Date time';

-- ----------------------------
-- Table structure for holiday
-- ----------------------------
DROP TABLE "APPOINMENT"."holiday";
CREATE TABLE "APPOINMENT"."holiday" (
  "holiday_id" NUMBER NOT NULL ,
  "holiday_date" VARCHAR2(100 BYTE) ,
  "holiday_description" VARCHAR2(255 BYTE) 
)
TABLESPACE "SYSTEM"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  FREELISTS 1
  FREELIST GROUPS 1
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "APPOINMENT"."holiday"."holiday_date" IS 'Holiday date';
COMMENT ON COLUMN "APPOINMENT"."holiday"."holiday_description" IS 'Holiday description';

-- ----------------------------
-- Primary Key structure for table appoinment
-- ----------------------------
ALTER TABLE "APPOINMENT"."appoinment" ADD CONSTRAINT "SYS_C007011" PRIMARY KEY ("appointment_id");

-- ----------------------------
-- Checks structure for table appoinment
-- ----------------------------
ALTER TABLE "APPOINMENT"."appoinment" ADD CONSTRAINT "SYS_C007010" CHECK ("appointment_id" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table appoinment_quota
-- ----------------------------
ALTER TABLE "APPOINMENT"."appoinment_quota" ADD CONSTRAINT "SYS_C007009" PRIMARY KEY ("appointment_quota_id");

-- ----------------------------
-- Checks structure for table appoinment_quota
-- ----------------------------
ALTER TABLE "APPOINMENT"."appoinment_quota" ADD CONSTRAINT "SYS_C007008" CHECK ("appointment_quota_id" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table encounter
-- ----------------------------
ALTER TABLE "APPOINMENT"."encounter" ADD CONSTRAINT "SYS_C007015" PRIMARY KEY ("encounter_id");

-- ----------------------------
-- Checks structure for table encounter
-- ----------------------------
ALTER TABLE "APPOINMENT"."encounter" ADD CONSTRAINT "SYS_C007014" CHECK ("encounter_id" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table holiday
-- ----------------------------
ALTER TABLE "APPOINMENT"."holiday" ADD CONSTRAINT "SYS_C007013" PRIMARY KEY ("holiday_id");

-- ----------------------------
-- Checks structure for table holiday
-- ----------------------------
ALTER TABLE "APPOINMENT"."holiday" ADD CONSTRAINT "SYS_C007012" CHECK ("holiday_id" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Foreign Keys structure for table encounter
-- ----------------------------
ALTER TABLE "APPOINMENT"."encounter" ADD CONSTRAINT "ENCOUNTER_APPINT_ID__FK" FOREIGN KEY ("appointment_id") REFERENCES "APPOINMENT"."appoinment" ("appointment_id") NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
