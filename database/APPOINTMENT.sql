/*
 Navicat Premium Data Transfer

 Source Server         : 10.10.103.61
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 10.10.103.61:33008
 Source Schema         : APPOINTMENT

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 25/03/2019 14:27:52
*/


-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE "APPOINTMENT"."appointment";
CREATE TABLE "APPOINTMENT"."appointment" (
  "appointment_id" NUMBER(10) NOT NULL ,
  "appointment_date" TIMESTAMP(6) ,
  "attendance_status" VARCHAR2(255 CHAR) ,
  "attendance_time" TIMESTAMP(6) ,
  "clinic_id" NUMBER(10) ,
  "clinic_name" VARCHAR2(255 CHAR) ,
  "encounter_type_id" NUMBER(10) ,
  "encounter_type_name" VARCHAR2(255 CHAR) ,
  "patient_doc" VARCHAR2(255 CHAR) ,
  "patient_id" NUMBER(10) ,
  "patient_name" VARCHAR2(255 CHAR) ,
  "patient_sex" VARCHAR2(255 CHAR) ,
  "room_id" NUMBER(10) ,
  "room_name" VARCHAR2(255 CHAR) ,
  "status" VARCHAR2(255 CHAR) 
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
-- Table structure for appointment_quota
-- ----------------------------
DROP TABLE "APPOINTMENT"."appointment_quota";
CREATE TABLE "APPOINTMENT"."appointment_quota" (
  "appointment_quota_id" NUMBER(10) NOT NULL ,
  "appointment_date" DATE ,
  "clinic_id" NUMBER(10) ,
  "encounter_type_id" NUMBER(10) ,
  "quota" NUMBER(10) ,
  "quota_booked" NUMBER(10) ,
  "room_id" NUMBER(10) 
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
-- Table structure for holiday
-- ----------------------------
DROP TABLE "APPOINTMENT"."holiday";
CREATE TABLE "APPOINTMENT"."holiday" (
  "holiday_id" NUMBER(10) NOT NULL ,
  "holiday_date" DATE ,
  "holiday_description" VARCHAR2(255 CHAR) 
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
-- Primary Key structure for table appointment
-- ----------------------------
ALTER TABLE "APPOINTMENT"."appointment" ADD CONSTRAINT "SYS_C007874" PRIMARY KEY ("appointment_id");

-- ----------------------------
-- Checks structure for table appointment
-- ----------------------------
ALTER TABLE "APPOINTMENT"."appointment" ADD CONSTRAINT "SYS_C007873" CHECK ("appointment_id" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table appointment_quota
-- ----------------------------
ALTER TABLE "APPOINTMENT"."appointment_quota" ADD CONSTRAINT "SYS_C007872" PRIMARY KEY ("appointment_quota_id");

-- ----------------------------
-- Checks structure for table appointment_quota
-- ----------------------------
ALTER TABLE "APPOINTMENT"."appointment_quota" ADD CONSTRAINT "SYS_C007871" CHECK ("appointment_quota_id" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table holiday
-- ----------------------------
ALTER TABLE "APPOINTMENT"."holiday" ADD CONSTRAINT "SYS_C007876" PRIMARY KEY ("holiday_id");

-- ----------------------------
-- Checks structure for table holiday
-- ----------------------------
ALTER TABLE "APPOINTMENT"."holiday" ADD CONSTRAINT "SYS_C007875" CHECK ("holiday_id" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
