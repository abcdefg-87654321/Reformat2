/* Formatted on 10/25/2014 10:27:31 (QP5 v5.227.12220.39754) */
INSERT INTO VNP_DATA.HOT_RATED_CDR_DEV (MAP_ID,
                                        A_NUMBER,
                                        CDR_TYPE,
                                        CREATED_TIME,
                                        CDR_START_TIME,
                                        DATA_PART,
                                        DURATION,
                                        TOTAL_USAGE,
                                        B_NUMBER,
                                        B_ZONE,
                                        NW_GROUP,
                                        SERVICE_FEE,
                                        SERVICE_FEE_ID,
                                        CHARGE_FEE,
                                        CHARGE_FEE_ID,
                                        LAC,
                                        CELL_ID,
                                        SUBSCRIBER_UNBILL,
                                        BU_ID,
                                        OLD_BU_ID,
                                        OFFER_COST,
                                        OFFER_FREE_BLOCK,
                                        INTERNAL_COST,
                                        INTERNAL_FREE_BLOCK,
                                        DIAL_DIGIT,
                                        CDR_RECORD_HEADER_ID,
                                        CDR_SEQUENCE_NUMBER,
                                        LOCATION_NO,
                                        MSC_ID,
                                        UNIT_TYPE_ID,
                                        PRIMARY_OFFER_ID,
                                        DISCOUNT_ITEM_ID,
                                        BALANCE_CHANGE,
                                        RERATE_FLAG,
                                        AUT_FINAL_ID,
                                        TARIFF_PLAN_ID)
   SELECT MAP_ID,
          '84943841472',
          CDR_TYPE,
          CREATED_TIME,
          CDR_START_TIME,
          DATA_PART,
          DURATION,
          TOTAL_USAGE,
          B_NUMBER,
          B_ZONE,
          NW_GROUP,
          SERVICE_FEE,
          SERVICE_FEE_ID,
          CHARGE_FEE,
          CHARGE_FEE_ID,
          LAC,
          CELL_ID,
          SUBSCRIBER_UNBILL,
          BU_ID,
          OLD_BU_ID,
          OFFER_COST,
          OFFER_FREE_BLOCK,
          INTERNAL_COST,
          INTERNAL_FREE_BLOCK,
          DIAL_DIGIT,
          CDR_RECORD_HEADER_ID,
          CDR_SEQUENCE_NUMBER,
          LOCATION_NO,
          MSC_ID,
          UNIT_TYPE_ID,
          PRIMARY_OFFER_ID,
          DISCOUNT_ITEM_ID,
          BALANCE_CHANGE,
          RERATE_FLAG,
          AUT_FINAL_ID,
          TARIFF_PLAN_ID
     FROM VNP_DATA.HOT_RATED_CDR;


--TRUNCATE TABLE hot_rated_cdr_dev;

SELECT * FROM hot_rated_cdr_dev;

SELECT a_number, rerate_flag FROM hot_rated_cdr_DEV;

--INSERT INTO hot_rated_cdr
--   SELECT * FROM hot_rated_cdr_dev;

UPDATE hot_rated_cdr_dev
   SET rerate_flag = 0;

UPDATE hot_rated_cdr_dev
   SET rerate_flag = 0
 WHERE data_part = 0;

COMMIT;