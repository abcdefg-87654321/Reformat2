/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eonerate.reformat2.entity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

import ElcRate.logging.ILogger;
import ElcRate.logging.LogUtil;
import ElcRate.record.ChargePacket;
import ElcRate.record.ErrorType;
import ElcRate.record.IError;
import ElcRate.record.RUMInfo;
import ElcRate.record.RatingRecord;
import ElcRate.record.RecordError;

public class RateRecord extends RatingRecord {

	ILogger LOG_PROCESSING = LogUtil.getLogUtil().getLogger("Processing");

	//	static long startSequenceCDR = 1;
	//	static boolean isOpened = false;

	// These are the mappings to the fields for input CDR
	public static final int IDX_A_NUMBER = 0;
	public static final int IDX_CDR_TYPE = 1;
	public static final int IDX_CREATED_TIME = 2;
	public static final int IDX_CDR_START_TIME = 3;
	public static final int IDX_DURATION = 4;
	public static final int IDX_TOTAL_USAGE = 5;
	public static final int IDX_B_NUMBER = 6;
	public static final int IDX_B_ZONE = 7;
	public static final int IDX_NW_GROUP = 8;
	public static final int IDX_SERVICE_FEE = 9;
	public static final int IDX_SERVICE_FEE_ID = 10;
	public static final int IDX_CHARGE_FEE = 11;
	public static final int IDX_CHARGE_FEE_ID = 12;
	public static final int IDX_LAC = 13;
	public static final int IDX_CELL_ID = 14;
	public static final int IDX_SUBSCRIBER_UNBILL = 15;
	public static final int IDX_BU_ID = 16;
	public static final int IDX_OLD_BU_ID = 17;
	public static final int IDX_OFFER_COST = 18;
	public static final int IDX_OFFER_FREE_BLOCK = 19;
	public static final int IDX_INTERNAL_COST = 20;
	public static final int IDX_INTERNAL_FREE_BLOCK = 21;
	public static final int IDX_DIAL_DIGIT = 22;
	public static final int IDX_CDR_RECORD_HEADER_ID = 23;
	public static final int IDX_CDR_SEQUENCE_NUMBER = 24;
	public static final int IDX_LOCATION_NO = 25;
	public static final int IDX_RERATE_FLAG = 26;
	public static final int IDX_AUT_FINAL_ID = 27;
	//    public static final int IDX_PAYMENT_ITEM_ID = 28;
	public static final int IDX_MSC_ID = 28;
	public static final int IDX_UNIT_TYPE_ID = 29;
	public static final int IDX_TARIFF_PLAN_ID = 30;
	public static final int IDX_MAP_ID = 31;
	public static final int IDX_DATA_PART = 32;

	//    public static final Random seqRandom = new Random(8668);
	public static int seqNextVal = 0;

	/**
	 * TEMP_SERVICE For Regex
	 *
	 * Add by: manucian86
	 */
	public static final String SERVICE_TEMP = "TEMP_SERVICE";
	//
	// CDR_TYPE
	public static final String CDR_TYPE_VOICE = "1"; // VOICE
	public static final String CDR_TYPE_SMS = "4"; // SMS
	public static final String CDR_TYPE_DATA = "7"; // OSC
	public static final String CDR_TYPE_MMS = "5"; // GPRS
	//
	// OFFER_TYPE
	public static final String OFFER_TYPE_PO = "PO"; // VOICE
	public static final String OFFER_TYPE_SO = "SO"; // SMS
	public static final String OFFER_TYPE_AO = "AO"; // OSC
	//
	// RUM_ID
	//    @Deprecated
	//    public static final String RUM_ID_EVT = "EVT";
	public static final String RUM_ID_MONEY = "MONEY";
	public static final String RUM_ID_VOL = "VOL";
	public static final String RUM_ID_DUR = "DUR";
	public static final String RUM_ID_SMS = "SMS";
	public static final String RUM_ID_MMS = "MMS";

	//    @Deprecated
	//    public static final String RUM_ID_MF = "MF";
	//
	//Default product for postpaid subsrciber
	public static final String PROD_DEFAULT = "POSTPAID";
	// 
	public Integer Seq = 0;
	// Variables for store hot rated CDR
	public String NumberA = null;
	public String ZoneA = null;

	public String CDR_Type = null;
	public Date CreateTime;
	//    public Date CDRStartTime;
	public double Duration = 0;
	public double TotalUsage;

	public String NumberB = null;
	public String ZoneB = null;

	public String NWGroup = null;

	public double ServiceFee = 0;
	public int ServiceFeeId;
	public double ChargeFee = 0;
	public int ChargeFeeId;
	public String Lac = null;
	public String CellId = null;
	public String SubscriberUnbill = null;
	public int BuId;
	public int OldBuId;
	public double OfferCost = 0;
	public double OfferFreeBlock = 0;
	public double InternalCost = 0;
	public double InternalFreeBlock = 0;
	public String DialDigit = null;
	public long CdrHeaderRecordId;
	public long CdrSeqNum;
	public String LocationNo = null;
	public int ReRateFlag = 0;

	//    public int PaymentItemId;
	public String MscId = null;

	public Integer UnitTypeId;

	public String ErrorMessage = null;

	// B Location
	//    @Deprecated
	//    public String ZoneInfo = null;
	//
	// {{{ REGION: FINAL AUT
	//    @Deprecated
	//    public int AutFinalId;
	public String UsageActivityId;
	public String TariffPlanId;

	public String CalendarId;

	public String UAInitialId;

	//    public Integer ApplicationId;
	//    public Integer SubTypeId;
	//    public Integer UnitTypeId;
	public String ZoneGroupId;

	public String AccountGroupId;
	public String SubsGroupId;
	public String MarketGroupId;
	public String AccessMethodGroupId;
	public String SpecialFeatureGroupId;
	public String OfflineGroupId;
	// }}} REGION: FINAL AUT
	//
	// {{{ REGION: OFFER PRIORITY
	public List<Integer> OfferIdBs;
	public List<Integer> OfferIdTs;
	public List<Integer> OfferIdDs;
	// }}} REGION: OFFER PRIORITY

	public Integer ResellerVersionId = null;

	public String IMSI = null;
	public double RatedAmount = 0;
	public String StreamName = null;
	public double Discount = 0;
	public String DiscountRule = StringUtils.EMPTY;
	//    @Deprecated
	//    public long CDRDate;
	//    @Deprecated
	//    public Date CDRNativeDate;

	public long CdrStartTimeInSecond;

	public long CDRCycleEnd;
	public double totalBalance = 0;
	public double ALOBalance = 0;
	public double ALODiscount = 0;
	public double ITouchDiscount = 0;

	//    public double ITouchPPP = 0;
	// Added field for output (+ UsedProduct)
	public String PriceUnit = null;
	public long validityPeriodStart;
	public long validityPeriodEnd;
	// Internal Management Fields
	public String GuidingKey = null;
	public Integer CustIDA = null;
	public Integer CustIDB = null;

	// AccountVersionId
	public int BalanceGroup = 0;

	public String UsedProduct = null;

	public String SubscriptionID = null;

	// BONUS & DISCOUNT
	// OLD
	//    @Deprecated
	//    public String UsedDiscount = null;
	/**
	 * Co ap dung ActivityEnd Discount hay khong?
	 */
	public boolean HasActivityEndDiscount = false;
	public boolean HasOtherDiscount = false;

	// Tách riêng thành 2 chuỗi DiscountModel
	// trước và sau khi trừ cước
	//    public String UsedPrePromo = null;
	//    public String UsedPostPromo = null;
	public String TimeZone = null;
	public String PriceModel = null;
	public boolean CUGFlag = false;
	public String CUGDiscount = null;
	public int CUGBalanceGroup = 0;
	public double CUGDiscAmt = 0;

	//    @Deprecated
	//    public String ProductList = null;
	public String MSN = null;
	//For ALO+ITOUCH promotion
	// the promotions in force at the time of the partial

	//    @Deprecated
	//    public ProductList Products = new ProductList();
	// This holds the priority order of the products
	//    @Deprecated
	//    public Vector<Integer> ProductPriority = new Vector<Integer>();
	//This holds discounts name
	//    @Deprecated
	//    public Vector<String> Options = new Vector<String>();
	// Add for test 06Nov
	public String RecordType;
	public String CDRType;

	public String Direction;

	public int MapId;

	public int DataPart;

	//Default constructor
	public RateRecord() {
		super();
	}

	/**
	 * Overloaded Constructor for RateRecord.
	 *
	 * @param OrginalData
	 */
	public RateRecord(String[] OrginalData) {
		this.fields = OrginalData;
	}

	private String getStringByFields() {

		String result = null;

		StringBuilder sb = new StringBuilder();

		for (String item : this.fields) {
			sb.append(item);
			sb.append("|");
		}

		result = sb.toString();

		result = result.substring(0, result.length() - 1);

		return result;

	}

	/**
	 * Utilities functions to map Main record
	 *
	 * @param recordType The OrginalData to map
	 */
	public void mapVNPRecord(String recordType) {

		LOG_PROCESSING.info("TODO: Map InputRecord to object: " + getStringByFields());

		RecordError tmpError;
		SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		//seqNextVal= Integer.parseInt(getField(IDX_MAP_ID));
		if (seqNextVal < Integer.MAX_VALUE) {
			++seqNextVal;
		} else {
			seqNextVal = 0;
		}
		this.Seq = seqNextVal;

		//Set Orginal Data for Dump
		this.OriginalData = recordType;

		//Category the CDR type for processing , 1 is VoiceCall, 4 is SMS, ..
		this.RecordType = getField(IDX_CDR_TYPE);

		//Pull A_number, B_number form input record
		NumberA = getField(IDX_A_NUMBER);
		NumberB = getField(IDX_B_NUMBER);

		//        //Reformat B_number
		//        if (B_Number != null) {
		//            if (B_Number.startsWith("1") || B_Number.startsWith("9")) {
		//                B_Number = '0' + B_Number;
		//            } else if (B_Number.startsWith("84")) {
		//                B_Number = '0' + B_Number.substring(2);
		//            }
		//        }
		//Parse duration
		try {
			String tmpDur = getField(IDX_DURATION);

			if (tmpDur == null) {
				Duration = 0;
			} else {
				Duration = Double.parseDouble(tmpDur);
			}

		} catch (NumberFormatException nfe) {
			Duration = 0;
			tmpError = new RecordError("ERR_DURATION_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		//Parse Total Usage
		try {
			String tmpTotalUsage = getField(IDX_TOTAL_USAGE);

			if (tmpTotalUsage == null) {
				TotalUsage = 0;
			} else {
				TotalUsage = Double.parseDouble(tmpTotalUsage);
			}
		} catch (NumberFormatException nfe) {
			TotalUsage = 0;
			tmpError = new RecordError("ERR_TOTAL_USAGE_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		//Parse CDR date
		try {

			//            this.CDRNativeDate = sdfInput.parse(getField(IDX_CDR_START_TIME));
			//            this.CDRDate = CDRNativeDate.getTime() / 1000; // Miliseconds -> Seconds
			//            super.EventStartDate = this.CDRNativeDate;            
			super.EventStartDate = sdfInput.parse(getField(IDX_CDR_START_TIME)); // this.CDRNativeDate;

			this.CdrStartTimeInSecond = super.EventStartDate.getTime() / 1000;

			//            super.EventEndDate = new Date((long) (this.CDRDate + this.Duration) * 1000);            
			super.EventEndDate = new Date((long) (this.CdrStartTimeInSecond + this.Duration) * 1000);

			//            super.UTCEventDate = this.CDRDate; // Seconds
			super.UTCEventDate = this.CdrStartTimeInSecond;

		} catch (ParseException pe) {
			tmpError = new RecordError("ERR_DATE_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		// Set the guiding key
		this.GuidingKey = NumberA;

		//Mapping specific values
		if (RecordType.startsWith(CDR_TYPE_VOICE)) {

			// set the RUM values
			CDRType = "MOB";
			super.setRUMValue(RUM_ID_DUR, Duration);

			//For direction worker
			this.Service = "2"; // "MOB";

			// add by manucian86
			this.UnitTypeId = 2; // SECONDS

		} else if (RecordType.startsWith(CDR_TYPE_SMS)) {

			// set the RUM values
			CDRType = "SMS";
			super.setRUMValue(RUM_ID_SMS, 1);

			//For direction worker
			this.Service = "1"; // "SMS";

			// add by manucian86
			this.UnitTypeId = 4; // SMS

		} else if (RecordType.startsWith(CDR_TYPE_DATA)) {

			// set the RUM values
			CDRType = "DATA";
			super.setRUMValue(RUM_ID_VOL, TotalUsage);

			//            //Set time, zone to NONE
			//            this.ZoneInfo = "NONE";
			//For direction worker
			this.Service = "3"; // "DATA";

			// add by manucian86
			this.UnitTypeId = 3; // OCTET

		} else if (RecordType.startsWith(CDR_TYPE_MMS)) {

			// set the RUM values
			CDRType = "MMS";
			super.setRUMValue(RUM_ID_MMS, 1);

			//For direction worker
			this.Service = "4"; // "SMS";

			// add by manucian86
			this.UnitTypeId = 5; // MMS

		}

		// Create a charge packet
		ChargePacket tmpCP = new ChargePacket();
		tmpCP.service = Service;
		tmpCP.packetType = "R";
		tmpCP.ratePlanName = UsedProduct;
		tmpCP.zoneModel = Service;
		tmpCP.timeModel = UsedProduct;

		this.addChargePacket(tmpCP);

		//Mapping others data
		//Parse Create time
		try {
			String tmpStr = getField(IDX_CREATED_TIME);
			if (tmpStr != null && !tmpStr.equals(StringUtils.EMPTY)) {
				this.CreateTime = sdfInput.parse(getField(IDX_CREATED_TIME));
			}

		} catch (ParseException pe) {
			tmpError = new RecordError("ERR_CREATE_TIME_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		this.ZoneB = getField(IDX_B_ZONE);
		this.NWGroup = getField(IDX_NW_GROUP);

		//Parse Service Fee
		try {
			String tmpStr = getField(IDX_SERVICE_FEE);

			if (tmpStr == null) {
				ServiceFee = 0;
			} else {
				ServiceFee = Double.parseDouble(tmpStr);
			}
		} catch (NumberFormatException nfe) {
			ServiceFee = 0;
			tmpError = new RecordError("ERR_SERVICE_FEE_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		//Parse Service Fee ID
		try {
			String tmpStr = getField(IDX_SERVICE_FEE_ID);

			if (tmpStr != null) {
				ServiceFeeId = Integer.parseInt(tmpStr);
			}

		} catch (NumberFormatException nfe) {
			tmpError = new RecordError("ERR_SERVICE_FEE_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		//Parse Charge Fee
		try {
			String tmpStr = getField(IDX_CHARGE_FEE);

			if (tmpStr == null) {
				ChargeFee = 0;
			} else {
				ChargeFee = Double.parseDouble(tmpStr);
			}

		} catch (NumberFormatException nfe) {
			ChargeFee = 0;
			tmpError = new RecordError("ERR_CHARGE_FEE_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		//Parse Charge Fee Id
		try {
			String tmpStr = getField(IDX_CHARGE_FEE_ID);

			if (tmpStr != null) {
				ChargeFeeId = Integer.parseInt(tmpStr);
			}

		} catch (NumberFormatException nfe) {
			tmpError = new RecordError("ERR_CHARGE_FEE_ID_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		this.Lac = getField(IDX_LAC);
		this.CellId = getField(IDX_CELL_ID);
		this.SubscriberUnbill = getField(IDX_SUBSCRIBER_UNBILL);

		//Parse Bu_ID
		try {
			String tmpStr = getField(IDX_BU_ID);

			if (tmpStr != null) {
				this.BuId = Integer.parseInt(tmpStr);
			}
		} catch (NumberFormatException nfe) {
			tmpError = new RecordError("ERR_BU_ID_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		//Parse Old_Bu_ID
		try {
			String tmpStr = getField(IDX_OLD_BU_ID);

			if (tmpStr != null) {
				this.OldBuId = Integer.parseInt(tmpStr);
			}
		} catch (NumberFormatException nfe) {
			tmpError = new RecordError("ERR_OLD_BU_ID_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		//Parse Offer Cost
		try {
			String tmpStr = getField(IDX_OFFER_COST);

			if (tmpStr != null) {
				this.OfferCost = Integer.parseInt(tmpStr);
			} else {
				this.OfferCost = 0;
			}
		} catch (NumberFormatException nfe) {
			this.OfferCost = 0;
			tmpError = new RecordError("ERR_OFFER_COST_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		//Parse Offer free block
		try {
			String tmpStr = getField(IDX_OFFER_FREE_BLOCK);

			if (tmpStr != null) {
				this.OfferFreeBlock = Integer.parseInt(tmpStr);
			} else {
				this.OfferFreeBlock = 0;
			}
		} catch (NumberFormatException nfe) {
			this.OfferFreeBlock = 0;
			tmpError = new RecordError("ERR_OFFER_FREE_BLOCK_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		//Parse Internal Cost
		try {
			String tmpStr = getField(IDX_INTERNAL_COST);

			if (tmpStr != null) {
				this.InternalCost = Integer.parseInt(tmpStr);
			} else {
				this.InternalCost = 0;
			}
		} catch (NumberFormatException nfe) {
			this.InternalCost = 0;
			tmpError = new RecordError("ERR_INTERNAL_COST_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		//Parse Internal Free Block
		try {
			String tmpStr = getField(IDX_INTERNAL_FREE_BLOCK);

			if (tmpStr != null) {
				this.InternalFreeBlock = Integer.parseInt(tmpStr);
			} else {
				this.InternalFreeBlock = 0;
			}
		} catch (NumberFormatException nfe) {
			this.InternalFreeBlock = 0;
			tmpError = new RecordError("ERR_INTERNAL_FREE_BLOCK_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		this.DialDigit = getField(IDX_DIAL_DIGIT);

		//Parse Record header
		try {
			String tmpStr = getField(IDX_CDR_RECORD_HEADER_ID);

			if (tmpStr != null) {
				this.CdrHeaderRecordId = Integer.parseInt(tmpStr);
			}
		} catch (NumberFormatException nfe) {
			tmpError = new RecordError("ERR_CDR_HEADER_RECORD_ID_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		//Parse CDR sequence
		try {
			String tmpStr = getField(IDX_CDR_SEQUENCE_NUMBER);

			if (tmpStr != null) {
				this.CdrSeqNum = Integer.parseInt(tmpStr);
			}
		} catch (NumberFormatException nfe) {
			tmpError = new RecordError("ERR_CDR_SEQUENCE_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		this.LocationNo = getField(IDX_LOCATION_NO);

		//Parse Call type id
		try {
			String tmpStr = getField(IDX_AUT_FINAL_ID);

			if (tmpStr != null) {
				//                this.CallTypeId = Integer.parseInt(tmpStr);
				this.UsageActivityId = tmpStr;
			}
		} catch (NumberFormatException nfe) {
			tmpError = new RecordError("ERR_CALL_TYPE_ID_INVALID", ErrorType.DATA_VALIDATION);
			this.addError(tmpError);
		}

		//        //Payment item id
		//        try {
		//            String tmpStr = getField(IDX_PAYMENT_ITEM_ID);
		//
		//            if (tmpStr != null) {
		//                this.PaymentItemId = Integer.parseInt(tmpStr);
		//            }
		//        } catch (NumberFormatException nfe) {
		//            tmpError = new RecordError("ERR_PAYMENT_ITEM_ID_INVALID", ErrorType.DATA_VALIDATION);
		//            this.addError(tmpError);
		//        }
		this.MscId = getField(IDX_MSC_ID);

		this.UnitTypeId = Integer.valueOf(getField(IDX_UNIT_TYPE_ID));

		this.TariffPlanId = getField(IDX_TARIFF_PLAN_ID);

		this.MapId = Integer.parseInt(getField(IDX_MAP_ID));

		this.DataPart = Integer.parseInt(getField(IDX_DATA_PART));

	}

	public String getActivityType(String s)
	{
		String result = null;
		if (s.equals("1")) {
			result = "0";
		}
		if (s.equals("4")) {
			result = "1";
		}
		if (s.equals("5")) {
			result = "2";
		}
		if (s.equals("7")) {
			result = "6";
		}

		return result;

	}

	public String getRecordType(String s)
	{
		String result = null;
		if (s.equals("1")) {
			result = "VOI";
		}
		if (s.equals("4")) {
			result = "CMS";
		}
		if (s.equals("5")) {
			result = "GPR";
		}
		if (s.equals("7")) {
			result = "OCS";
		}

		return result;

	}

	public String unmapOriginalData(long startSequenceCDR) {

		String outFields[] = new String[138];
		int NumberOfFields;
		String errMessage = null;
		int i;
		StringBuilder tmpReassemble;
		SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");

		//Get error message
		Collection<IError> errors = this.getErrors();
		Iterator<IError> iError = errors.iterator();
		while (iError.hasNext()) {
			RecordError tmpErr = (RecordError) iError.next();
			errMessage = tmpErr.getType() + " -> " + tmpErr.getMessage();
		}

		//		// open file sequence to take CDR sequence
		//		if (!isOpened) {
		//			try {
		//				File source = new File("config/sequence.txt");
		//				if (!source.exists()) {
		//					PrintWriter out = new PrintWriter(new FileOutputStream(
		//							"config/sequence.txt"));
		//					out.println(1);
		//					out.println(1);
		//					out.flush();
		//					out.close();
		//				}
		//				isOpened = true;
		//				Scanner scanner = new Scanner(source);
		//				Long fileSequence = scanner.nextLong();
		//				startSequenceCDR = scanner.nextLong();
		//				scanner.close();
		//
		//			} catch (Exception e) {
		//				e.printStackTrace();
		//			}
		//		}
		DecimalFormat df = new DecimalFormat("0000000000");

		outFields[OrpRecord.Field.RecordType.ordinal()] = getRecordType(RecordType);
		outFields[OrpRecord.Field.RecordSequenceNumber.ordinal()] = df.format(startSequenceCDR);
		//		if (isOpened) {
		//			startSequenceCDR++;
		//		}

		outFields[OrpRecord.Field.ActivityType.ordinal()] = getActivityType(RecordType);
		outFields[OrpRecord.Field.ResultCode.ordinal()] = "0";
		outFields[OrpRecord.Field.ResultText.ordinal()] = "OR_RSLT_UNPROCESSED(0)";
		outFields[OrpRecord.Field.Reserved1.ordinal()] = "";
		outFields[OrpRecord.Field.Reserved2.ordinal()] = "";

		if (RecordType.equals("1"))
		{
			outFields[OrpRecord.Field.RecordOrigin.ordinal()] = "slu999";
			outFields[OrpRecord.Field.ActivityOfferedDateTime.ordinal()] = "1410393600";
			outFields[OrpRecord.Field.ActivityAnsweredDateTime.ordinal()] = "1410393601";
			outFields[OrpRecord.Field.ActivityDisconnectDateTime.ordinal()] = "1410393620";
			outFields[OrpRecord.Field.ANumber.ordinal()] = NumberA;
			outFields[OrpRecord.Field.BNumber.ordinal()] = NumberB;
			outFields[OrpRecord.Field.ExternalId.ordinal()] = NumberA;
			outFields[OrpRecord.Field.ExternalIdtype.ordinal()] = "1";
			outFields[OrpRecord.Field.MSCID.ordinal()] = "84102";
			outFields[OrpRecord.Field.MSRN.ordinal()] = "";
			outFields[OrpRecord.Field.ApplicationType.ordinal()] = "1";
			outFields[OrpRecord.Field.Subtype.ordinal()] = "0";
			outFields[OrpRecord.Field.UnitType.ordinal()] = "2";
			outFields[OrpRecord.Field.ReferenceNumber.ordinal()] = "10477";
			outFields[OrpRecord.Field.InitialAUT.ordinal()] = "30012";
			outFields[OrpRecord.Field.ChargeType.ordinal()] = "";
			outFields[OrpRecord.Field.SGSN.ordinal()] = "";
			outFields[OrpRecord.Field.ClearCause.ordinal()] = "16";
			outFields[OrpRecord.Field.CellID.ordinal()] = "4520200003500100";
			outFields[OrpRecord.Field.NetworkCalltype.ordinal()] = "0";
			outFields[OrpRecord.Field.ConsumedAmount.ordinal()] = "0.000000";
			outFields[OrpRecord.Field.UTCOffset.ordinal()] = "420";
			outFields[OrpRecord.Field.Origin.ordinal()] = "2";
			outFields[OrpRecord.Field.PortedNumber.ordinal()] = "";
			outFields[OrpRecord.Field.OriginalChargeAmount.ordinal()] = "0.000000";
			outFields[OrpRecord.Field.OriginalChargeCurrency.ordinal()] = "";
			outFields[OrpRecord.Field.GSMProviderID.ordinal()] = "";
			outFields[OrpRecord.Field.APN.ordinal()] = "";
			outFields[OrpRecord.Field.QOS.ordinal()] = "";
			outFields[OrpRecord.Field.ReservationType.ordinal()] = "";
			outFields[OrpRecord.Field.PDPInitType.ordinal()] = "";
			outFields[OrpRecord.Field.ServiceIDCellIDLAI.ordinal()] = "";
			outFields[OrpRecord.Field.ECIMessageType.ordinal()] = "";
			outFields[OrpRecord.Field.ECIAssociatedNumber.ordinal()] = "";
			outFields[OrpRecord.Field.ECIMISSIDN.ordinal()] = "";
			outFields[OrpRecord.Field.ECIAltMISSIDN.ordinal()] = "";
			outFields[OrpRecord.Field.ECISubscriberType.ordinal()] = "";
			outFields[OrpRecord.Field.ECIBearerCapability.ordinal()] = "";
			outFields[OrpRecord.Field.ECIApplicationID.ordinal()] = "";
			outFields[OrpRecord.Field.ECITransactionID1.ordinal()] = "";
			outFields[OrpRecord.Field.ECITransactionID2.ordinal()] = "";
			outFields[OrpRecord.Field.ECIAccessMT.ordinal()] = "";
			outFields[OrpRecord.Field.ECIMINtranslation.ordinal()] = "";
			outFields[OrpRecord.Field.ECIChargeAmount.ordinal()] = "";
			outFields[OrpRecord.Field.ECIProrate.ordinal()] = "";
			outFields[OrpRecord.Field.ECISDPIDOrigin.ordinal()] = "";
			outFields[OrpRecord.Field.ECIInforParam1.ordinal()] = "";
			outFields[OrpRecord.Field.ECIInforParam2.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorCellIDLAI.ordinal()] = "4520200003500100";
			outFields[OrpRecord.Field.CallProcessorPrePostIndicator.ordinal()] = "-1";
			outFields[OrpRecord.Field.CallProcessorPOSTPAIDType.ordinal()] = "-1";
			outFields[OrpRecord.Field.CallProcessorCallType.ordinal()] = "52";
			outFields[OrpRecord.Field.CallProcessorNetworkNoCharge.ordinal()] = "0";
			outFields[OrpRecord.Field.CallProcessorRedirectingNumber.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorMINIMSI.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorTranslatedDestinationNumber.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorApartyMSRN.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorNCFLeg.ordinal()] = "-";
			outFields[OrpRecord.Field.CallProcessorCallDirection.ordinal()] = "O";
			outFields[OrpRecord.Field.CallProcessorAnumberanswertime.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorBnumberanswertime.ordinal()] = "";
			outFields[OrpRecord.Field.Billable.ordinal()] = "1";
			outFields[OrpRecord.Field.ExternalSystemSequenceNumber.ordinal()] = "";
			outFields[OrpRecord.Field.OsaReservationStartTime.ordinal()] = "";
			outFields[OrpRecord.Field.OsaReservationType.ordinal()] = "";
			outFields[OrpRecord.Field.OsaSubscriberId.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamItem.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamSubtype.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamConfirmationId.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamContract.ordinal()] = "";
			outFields[OrpRecord.Field.OsaTimezoneOffset.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamQos.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamService1.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamService2.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamService3.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamService4.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamInformational.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamSubLocation.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamSubLocationType.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamOtherLocation.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamOtherLocationType.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamImsiMin.ordinal()] = "";
			outFields[OrpRecord.Field.OsaMerchantId.ordinal()] = "";
			outFields[OrpRecord.Field.OsaSessionDescription.ordinal()] = "";
			outFields[OrpRecord.Field.OsaSessionID.ordinal()] = "";
			outFields[OrpRecord.Field.OsaCorrelationId.ordinal()] = "";
			outFields[OrpRecord.Field.OsaCorrelationType.ordinal()] = "";
			outFields[OrpRecord.Field.OsaMerAccount.ordinal()] = "";
			outFields[OrpRecord.Field.OsaApplDescText.ordinal()] = "";
			outFields[OrpRecord.Field.OsaExtUnitTypeId.ordinal()] = "";
			outFields[OrpRecord.Field.OsaCurrency.ordinal()] = "";
			outFields[OrpRecord.Field.OsaReasonCode.ordinal()] = "";
			outFields[OrpRecord.Field.OsaRequestType.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsapplication.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsapplicationdescription.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsspecialfeaturedigits.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsactivitytime.ordinal()] = "";
			outFields[OrpRecord.Field.OcsRequesttype.ordinal()] = "";
			outFields[OrpRecord.Field.OcsTBit.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsconsumedunits.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsconsumedunittype.ordinal()] = "";
			outFields[OrpRecord.Field.Ocscurrencytype.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsimsinum.ordinal()] = "";
			outFields[OrpRecord.Field.Ocschargeitemid.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssessiongid.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssubsessionid.ordinal()] = "";
			outFields[OrpRecord.Field.Ocstransactionid.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssubscriberid.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssessiondesc.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssublocation.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssublocationtype.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssubotherlocation.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssubotherlocationtype.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsteleservicetype.ordinal()] = "";
			outFields[OrpRecord.Field.CallprocessorTimeZone.ordinal()] = "234";
			outFields[OrpRecord.Field.Offereddtmsec.ordinal()] = "156";
			outFields[OrpRecord.Field.Answereddtmsec.ordinal()] = "161";
			outFields[OrpRecord.Field.Disconnectdtmsec.ordinal()] = "164";
			outFields[OrpRecord.Field.PointTargetExternalIdType.ordinal()] = "1";
			outFields[OrpRecord.Field.NetworkPortingPrefix.ordinal()] = "";
			outFields[OrpRecord.Field.IMSIA.ordinal()] = "";
			outFields[OrpRecord.Field.IMSIB.ordinal()] = "";
			outFields[OrpRecord.Field.type1NormalizedNumber.ordinal()] = NumberB;
			outFields[OrpRecord.Field.type2NormalizedNumber.ordinal()] = NumberB;
			outFields[OrpRecord.Field.CallingNumberPresentation.ordinal()] = "0";
			outFields[OrpRecord.Field.networkaddressplan.ordinal()] = "1";
		}
		if (RecordType.equals("4"))
		{
			outFields[OrpRecord.Field.RecordOrigin.ordinal()] = "slu999";
			outFields[OrpRecord.Field.ActivityOfferedDateTime.ordinal()] = "1413947207";
			outFields[OrpRecord.Field.ActivityAnsweredDateTime.ordinal()] = "1413947207";
			outFields[OrpRecord.Field.ActivityDisconnectDateTime.ordinal()] = "1413947207";
			outFields[OrpRecord.Field.ANumber.ordinal()] = NumberA;
			outFields[OrpRecord.Field.BNumber.ordinal()] = NumberB;
			outFields[OrpRecord.Field.ExternalId.ordinal()] = NumberA;
			outFields[OrpRecord.Field.ExternalIdtype.ordinal()] = "1";
			outFields[OrpRecord.Field.MSCID.ordinal()] = "8491020467";
			outFields[OrpRecord.Field.MSRN.ordinal()] = "";
			outFields[OrpRecord.Field.ApplicationType.ordinal()] = "2";
			outFields[OrpRecord.Field.Subtype.ordinal()] = "0";
			outFields[OrpRecord.Field.UnitType.ordinal()] = "4";
			outFields[OrpRecord.Field.ReferenceNumber.ordinal()] = "155";
			outFields[OrpRecord.Field.InitialAUT.ordinal()] = "30016";
			outFields[OrpRecord.Field.ChargeType.ordinal()] = "";
			outFields[OrpRecord.Field.SGSN.ordinal()] = "";
			outFields[OrpRecord.Field.ClearCause.ordinal()] = "16";
			outFields[OrpRecord.Field.CellID.ordinal()] = "84910100401999";
			outFields[OrpRecord.Field.NetworkCalltype.ordinal()] = "0";
			outFields[OrpRecord.Field.ConsumedAmount.ordinal()] = "";
			outFields[OrpRecord.Field.UTCOffset.ordinal()] = "420";
			outFields[OrpRecord.Field.Origin.ordinal()] = "0";
			outFields[OrpRecord.Field.PortedNumber.ordinal()] = "";
			outFields[OrpRecord.Field.OriginalChargeAmount.ordinal()] = "";
			outFields[OrpRecord.Field.OriginalChargeCurrency.ordinal()] = "";
			outFields[OrpRecord.Field.GSMProviderID.ordinal()] = "";
			outFields[OrpRecord.Field.APN.ordinal()] = "";
			outFields[OrpRecord.Field.QOS.ordinal()] = "";
			outFields[OrpRecord.Field.ReservationType.ordinal()] = "";
			outFields[OrpRecord.Field.PDPInitType.ordinal()] = "";
			outFields[OrpRecord.Field.ServiceIDCellIDLAI.ordinal()] = "";
			outFields[OrpRecord.Field.ECIMessageType.ordinal()] = "";
			outFields[OrpRecord.Field.ECIAssociatedNumber.ordinal()] = "";
			outFields[OrpRecord.Field.ECIMISSIDN.ordinal()] = "";
			outFields[OrpRecord.Field.ECIAltMISSIDN.ordinal()] = "";
			outFields[OrpRecord.Field.ECISubscriberType.ordinal()] = "";
			outFields[OrpRecord.Field.ECIBearerCapability.ordinal()] = "";
			outFields[OrpRecord.Field.ECIApplicationID.ordinal()] = "";
			outFields[OrpRecord.Field.ECITransactionID1.ordinal()] = "";
			outFields[OrpRecord.Field.ECITransactionID2.ordinal()] = "";
			outFields[OrpRecord.Field.ECIAccessMT.ordinal()] = "";
			outFields[OrpRecord.Field.ECIMINtranslation.ordinal()] = "";
			outFields[OrpRecord.Field.ECIChargeAmount.ordinal()] = "";
			outFields[OrpRecord.Field.ECIProrate.ordinal()] = "";
			outFields[OrpRecord.Field.ECISDPIDOrigin.ordinal()] = "";
			outFields[OrpRecord.Field.ECIInforParam1.ordinal()] = "";
			outFields[OrpRecord.Field.ECIInforParam2.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorCellIDLAI.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorPrePostIndicator.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorPOSTPAIDType.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorCallType.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorNetworkNoCharge.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorRedirectingNumber.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorMINIMSI.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorTranslatedDestinationNumber.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorApartyMSRN.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorNCFLeg.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorCallDirection.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorAnumberanswertime.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorBnumberanswertime.ordinal()] = "";
			outFields[OrpRecord.Field.Billable.ordinal()] = "1";
			outFields[OrpRecord.Field.ExternalSystemSequenceNumber.ordinal()] = "55";
			outFields[OrpRecord.Field.OsaReservationStartTime.ordinal()] = "";
			outFields[OrpRecord.Field.OsaReservationType.ordinal()] = "";
			outFields[OrpRecord.Field.OsaSubscriberId.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamItem.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamSubtype.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamConfirmationId.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamContract.ordinal()] = "";
			outFields[OrpRecord.Field.OsaTimezoneOffset.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamQos.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamService1.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamService2.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamService3.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamService4.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamInformational.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamSubLocation.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamSubLocationType.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamOtherLocation.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamOtherLocationType.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamImsiMin.ordinal()] = "";
			outFields[OrpRecord.Field.OsaMerchantId.ordinal()] = "";
			outFields[OrpRecord.Field.OsaSessionDescription.ordinal()] = "";
			outFields[OrpRecord.Field.OsaSessionID.ordinal()] = "";
			outFields[OrpRecord.Field.OsaCorrelationId.ordinal()] = "";
			outFields[OrpRecord.Field.OsaCorrelationType.ordinal()] = "";
			outFields[OrpRecord.Field.OsaMerAccount.ordinal()] = "";
			outFields[OrpRecord.Field.OsaApplDescText.ordinal()] = "";
			outFields[OrpRecord.Field.OsaExtUnitTypeId.ordinal()] = "";
			outFields[OrpRecord.Field.OsaCurrency.ordinal()] = "";
			outFields[OrpRecord.Field.OsaReasonCode.ordinal()] = "";
			outFields[OrpRecord.Field.OsaRequestType.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsapplication.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsapplicationdescription.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsspecialfeaturedigits.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsactivitytime.ordinal()] = "";
			outFields[OrpRecord.Field.OcsRequesttype.ordinal()] = "";
			outFields[OrpRecord.Field.OcsTBit.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsconsumedunits.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsconsumedunittype.ordinal()] = "";
			outFields[OrpRecord.Field.Ocscurrencytype.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsimsinum.ordinal()] = "";
			outFields[OrpRecord.Field.Ocschargeitemid.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssessiongid.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssubsessionid.ordinal()] = "";
			outFields[OrpRecord.Field.Ocstransactionid.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssubscriberid.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssessiondesc.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssublocation.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssublocationtype.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssubotherlocation.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssubotherlocationtype.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsteleservicetype.ordinal()] = "";
			outFields[OrpRecord.Field.CallprocessorTimeZone.ordinal()] = "420";
			outFields[OrpRecord.Field.Offereddtmsec.ordinal()] = "";
			outFields[OrpRecord.Field.Answereddtmsec.ordinal()] = "";
			outFields[OrpRecord.Field.Disconnectdtmsec.ordinal()] = "";
			outFields[OrpRecord.Field.PointTargetExternalIdType.ordinal()] = "1";
			outFields[OrpRecord.Field.NetworkPortingPrefix.ordinal()] = "";
			outFields[OrpRecord.Field.IMSIA.ordinal()] = "";
			outFields[OrpRecord.Field.IMSIB.ordinal()] = "";
			outFields[OrpRecord.Field.type1NormalizedNumber.ordinal()] = "";
			outFields[OrpRecord.Field.type2NormalizedNumber.ordinal()] = "";
			outFields[OrpRecord.Field.CallingNumberPresentation.ordinal()] = "0";
			outFields[OrpRecord.Field.networkaddressplan.ordinal()] = "1";

		}

		if (RecordType.equals("5"))
		{
			outFields[OrpRecord.Field.RecordOrigin.ordinal()] = "slu999";
			outFields[OrpRecord.Field.ActivityOfferedDateTime.ordinal()] = "1398290056";
			outFields[OrpRecord.Field.ActivityAnsweredDateTime.ordinal()] = "1398290057";
			outFields[OrpRecord.Field.ActivityDisconnectDateTime.ordinal()] = "1398290157";
			outFields[OrpRecord.Field.ANumber.ordinal()] = NumberA;
			outFields[OrpRecord.Field.BNumber.ordinal()] = "GPRS_LOCATION";
			outFields[OrpRecord.Field.ExternalId.ordinal()] = NumberA;
			outFields[OrpRecord.Field.ExternalIdtype.ordinal()] = "0";
			outFields[OrpRecord.Field.MSCID.ordinal()] = "";
			outFields[OrpRecord.Field.MSRN.ordinal()] = "";
			outFields[OrpRecord.Field.ApplicationType.ordinal()] = "10";
			outFields[OrpRecord.Field.Subtype.ordinal()] = "30025";
			outFields[OrpRecord.Field.UnitType.ordinal()] = "3";
			outFields[OrpRecord.Field.ReferenceNumber.ordinal()] = "90000";
			outFields[OrpRecord.Field.InitialAUT.ordinal()] = "30040";
			outFields[OrpRecord.Field.ChargeType.ordinal()] = "";
			outFields[OrpRecord.Field.SGSN.ordinal()] = "84910299999";
			outFields[OrpRecord.Field.ClearCause.ordinal()] = "16";
			outFields[OrpRecord.Field.CellID.ordinal()] = "";
			outFields[OrpRecord.Field.NetworkCalltype.ordinal()] = "";
			outFields[OrpRecord.Field.ConsumedAmount.ordinal()] = "123450";
			outFields[OrpRecord.Field.UTCOffset.ordinal()] = "420";
			outFields[OrpRecord.Field.Origin.ordinal()] = "2";
			outFields[OrpRecord.Field.PortedNumber.ordinal()] = "";
			outFields[OrpRecord.Field.OriginalChargeAmount.ordinal()] = "0";
			outFields[OrpRecord.Field.OriginalChargeCurrency.ordinal()] = "";
			outFields[OrpRecord.Field.GSMProviderID.ordinal()] = "";
			outFields[OrpRecord.Field.APN.ordinal()] = "m3-card";
			outFields[OrpRecord.Field.QOS.ordinal()] = "4001";
			outFields[OrpRecord.Field.ReservationType.ordinal()] = "1";
			outFields[OrpRecord.Field.PDPInitType.ordinal()] = "0";
			outFields[OrpRecord.Field.ServiceIDCellIDLAI.ordinal()] = "4520200003500100";
			outFields[OrpRecord.Field.ECIMessageType.ordinal()] = "";
			outFields[OrpRecord.Field.ECIAssociatedNumber.ordinal()] = "";
			outFields[OrpRecord.Field.ECIMISSIDN.ordinal()] = "";
			outFields[OrpRecord.Field.ECIAltMISSIDN.ordinal()] = "";
			outFields[OrpRecord.Field.ECISubscriberType.ordinal()] = "";
			outFields[OrpRecord.Field.ECIBearerCapability.ordinal()] = "";
			outFields[OrpRecord.Field.ECIApplicationID.ordinal()] = "";
			outFields[OrpRecord.Field.ECITransactionID1.ordinal()] = "";
			outFields[OrpRecord.Field.ECITransactionID2.ordinal()] = "";
			outFields[OrpRecord.Field.ECIAccessMT.ordinal()] = "";
			outFields[OrpRecord.Field.ECIMINtranslation.ordinal()] = "";
			outFields[OrpRecord.Field.ECIChargeAmount.ordinal()] = "";
			outFields[OrpRecord.Field.ECIProrate.ordinal()] = "";
			outFields[OrpRecord.Field.ECISDPIDOrigin.ordinal()] = "";
			outFields[OrpRecord.Field.ECIInforParam1.ordinal()] = "";
			outFields[OrpRecord.Field.ECIInforParam2.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorCellIDLAI.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorPrePostIndicator.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorPOSTPAIDType.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorCallType.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorNetworkNoCharge.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorRedirectingNumber.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorMINIMSI.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorTranslatedDestinationNumber.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorApartyMSRN.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorNCFLeg.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorCallDirection.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorAnumberanswertime.ordinal()] = "";
			outFields[OrpRecord.Field.CallProcessorBnumberanswertime.ordinal()] = "";
			outFields[OrpRecord.Field.Billable.ordinal()] = "";
			outFields[OrpRecord.Field.ExternalSystemSequenceNumber.ordinal()] = "";
			outFields[OrpRecord.Field.OsaReservationStartTime.ordinal()] = "";
			outFields[OrpRecord.Field.OsaReservationType.ordinal()] = "";
			outFields[OrpRecord.Field.OsaSubscriberId.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamItem.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamSubtype.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamConfirmationId.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamContract.ordinal()] = "";
			outFields[OrpRecord.Field.OsaTimezoneOffset.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamQos.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamService1.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamService2.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamService3.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamService4.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamInformational.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamSubLocation.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamSubLocationType.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamOtherLocation.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamOtherLocationType.ordinal()] = "";
			outFields[OrpRecord.Field.OsaParamImsiMin.ordinal()] = "";
			outFields[OrpRecord.Field.OsaMerchantId.ordinal()] = "";
			outFields[OrpRecord.Field.OsaSessionDescription.ordinal()] = "";
			outFields[OrpRecord.Field.OsaSessionID.ordinal()] = "";
			outFields[OrpRecord.Field.OsaCorrelationId.ordinal()] = "";
			outFields[OrpRecord.Field.OsaCorrelationType.ordinal()] = "";
			outFields[OrpRecord.Field.OsaMerAccount.ordinal()] = "";
			outFields[OrpRecord.Field.OsaApplDescText.ordinal()] = "";
			outFields[OrpRecord.Field.OsaExtUnitTypeId.ordinal()] = "";
			outFields[OrpRecord.Field.OsaCurrency.ordinal()] = "";
			outFields[OrpRecord.Field.OsaReasonCode.ordinal()] = "";
			outFields[OrpRecord.Field.OsaRequestType.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsapplication.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsapplicationdescription.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsspecialfeaturedigits.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsactivitytime.ordinal()] = "";
			outFields[OrpRecord.Field.OcsRequesttype.ordinal()] = "";
			outFields[OrpRecord.Field.OcsTBit.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsconsumedunits.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsconsumedunittype.ordinal()] = "";
			outFields[OrpRecord.Field.Ocscurrencytype.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsimsinum.ordinal()] = "";
			outFields[OrpRecord.Field.Ocschargeitemid.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssessiongid.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssubsessionid.ordinal()] = "";
			outFields[OrpRecord.Field.Ocstransactionid.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssubscriberid.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssessiondesc.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssublocation.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssublocationtype.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssubotherlocation.ordinal()] = "";
			outFields[OrpRecord.Field.Ocssubotherlocationtype.ordinal()] = "";
			outFields[OrpRecord.Field.Ocsteleservicetype.ordinal()] = "";
			outFields[OrpRecord.Field.CallprocessorTimeZone.ordinal()] = "";
			outFields[OrpRecord.Field.Offereddtmsec.ordinal()] = "156";
			outFields[OrpRecord.Field.Answereddtmsec.ordinal()] = "161";
			outFields[OrpRecord.Field.Disconnectdtmsec.ordinal()] = "164";
			outFields[OrpRecord.Field.PointTargetExternalIdType.ordinal()] = "";
			outFields[OrpRecord.Field.NetworkPortingPrefix.ordinal()] = "";
			outFields[OrpRecord.Field.IMSIA.ordinal()] = "";
			outFields[OrpRecord.Field.IMSIB.ordinal()] = "";
			outFields[OrpRecord.Field.type1NormalizedNumber.ordinal()] = NumberA;
			outFields[OrpRecord.Field.type2NormalizedNumber.ordinal()] = NumberA;
			outFields[OrpRecord.Field.CallingNumberPresentation.ordinal()] = "0";
			outFields[OrpRecord.Field.networkaddressplan.ordinal()] = "1";
		}

		//		outFields[OrpRecord.Field.ActivityOfferedDateTime.ordinal()]=df.format(super.EventStartDate.getTime()/1000);
		//		outFields[OrpRecord.Field.ActivityAnsweredDateTime.ordinal()]=df.format((super.EventStartDate.getTime()+1000)/1000);
		//		outFields[OrpRecord.Field.ActivityDisconnectDateTime.ordinal()]=df.format((super.EventStartDate.getTime()+10000)/1000);
		//		outFields[OrpRecord.Field.ANumber.ordinal()]=NumberA;
		//		outFields[OrpRecord.Field.BNumber.ordinal()]=NumberB;
		//		outFields[OrpRecord.Field.ExternalId.ordinal()]=NumberA;

		outFields[OrpRecord.Field.Ocssegmentid.ordinal()] = "";
		outFields[OrpRecord.Field.CpIncomingCallid.ordinal()] = "";
		outFields[OrpRecord.Field.CpOutgoingcallid.ordinal()] = "";
		outFields[OrpRecord.Field.OcsStartCallDatTimeType.ordinal()] = "";
		outFields[OrpRecord.Field.OcsEndCallDatTimeType.ordinal()] = "";

		//		//Set output data
		//		
		//		outFields[OrpRecord.Field.ActivityOfferedDateTime.ordinal()] = sdfInput.format(CreateTime);
		//		outFields[3] = sdfInput.format(CDRNativeDate);
		//		outFields[OrpRecord.Field.ActivityAnsweredDateTime.ordinal()] = sdfInput.format(super.EventStartDate);
		////		outFields[4] = String.valueOf(Duration);
		////		outFields[5] = String.valueOf(TotalUsage);
		////		outFields[7] = ZoneB;
		////		outFields[8] = NWGroup;
		//		//outFields[OrpRecord.Field.ServiceIDCellIDLAI.ordinal()] = String.valueOf(ServiceFee);
		//		//outFields[OrpRecord.Field.ServiceIDCellIDLAI.ordinal()] = String.valueOf(ServiceFeeId);
		//		outFields[OrpRecord.Field.OriginalChargeAmount.ordinal()] = String.valueOf(ChargeFee);
		//		outFields[12] = String.valueOf(ChargeFeeId);
		//		outFields[13] = Lac;
		//		outFields[OrpRecord.Field.CellID.ordinal()] = CellId;
		//		outFields[15] = SubscriberUnbill;
		//		//outFields[16] = String.valueOf(BuId);
		//		//outFields[17] = String.valueOf(OldBuId);
		//		outFields[18] = String.valueOf(OfferCost);
		//		outFields[19] = String.valueOf(OfferFreeBlock);
		//		outFields[20] = String.valueOf(InternalCost);
		//		outFields[21] = String.valueOf(InternalFreeBlock);
		//		//outFields[OrpRecord.Field.a] = DialDigit;
		//		//outFields[23] = String.valueOf(CdrHeaderRecordId);
		//outFields[24] = String.valueOf(CdrSeqNum);
		//		outFields[25] = LocationNo;
		//		outFields[26] = "2"; // Rerated
		//		outFields[27] = UsageActivityId;
		//		//        outFields[28] = String.valueOf(PaymentItemId);
		//		outFields[OrpRecord.Field.MSCID.ordinal()] = MscId;
		//		outFields[OrpRecord.Field.UnitType.ordinal()] = UnitTypeId.toString();
		////		outFields[30] = TariffPlanId;
		//		outFields[31] = errMessage;

		tmpReassemble = new StringBuilder(1024);

		NumberOfFields = outFields.length;

		//for (i = 0; i < NumberOfFields; i++) 
		for (i = 0; i < NumberOfFields; i++)
		{

			tmpReassemble.append(outFields[i]);
			tmpReassemble.append("|");

		}

		String result = tmpReassemble.toString();

		LOG_PROCESSING.info("DONE: Create Output record: " + result);

		return result;

	}

	@Override
	public ArrayList<String> getDumpInfo() {
		ArrayList<String> tmpDumpList = null;
		tmpDumpList = new ArrayList<String>();

		// Format the fields
		tmpDumpList.add("============ DETAIL RECORD ============");
		tmpDumpList.add("  Record Number   = <" + this.RecordNumber + ">");
		tmpDumpList.add("  original record = <" + this.OriginalData + ">");
		tmpDumpList.add("  Service         = <" + this.Service + ">");
		tmpDumpList.add("  A Number        = <" + this.NumberA + ">");
		tmpDumpList.add("  B Number        = <" + this.NumberB + ">");
		//        tmpDumpList.add("  CDR Start Date  = <" + this.CDRDate + ">");
		tmpDumpList.add("  CDR Start Date  = <" + this.CdrStartTimeInSecond + ">");
		tmpDumpList.add("  Duration        = <" + this.Duration + ">");
		tmpDumpList.add("  IMSI            = <" + this.IMSI + ">");
		tmpDumpList.add("       --- Customer Attributes ---");
		tmpDumpList.add("  Guiding Key     = <" + this.GuidingKey + ">");
		tmpDumpList.add("  Customer ID A   = <" + this.CustIDA + ">");
		tmpDumpList.add("  Customer ID B   = <" + this.CustIDB + ">");
		tmpDumpList.add("  CUG Flag        = <" + this.CUGFlag + ">");
		tmpDumpList.add("  CUG Discount    = <" + this.CUGDiscount + ">");
		tmpDumpList.add("  CUG Balance Grp = <" + this.CUGBalanceGroup + ">");
		tmpDumpList.add("  CUG Disc Amount = <" + this.CUGDiscAmt + ">");
		tmpDumpList.add("       --- Rating Attributes ---");
		tmpDumpList.add("  Product Used    = <" + this.UsedProduct + ">");
		tmpDumpList.add("  SubscriptionID  = <" + this.SubscriptionID + ">");
		//        tmpDumpList.add("  Zone            = <" + this.ZoneInfo + ">");
		tmpDumpList.add("  Time Zone       = <" + this.TimeZone + ">");
		tmpDumpList.add("  Price Model     = <" + this.PriceModel + ">");
		tmpDumpList.add("  Rated Amount    = <" + this.RatedAmount + ">");
		//        tmpDumpList.add("  Discount Used   = <" + this.UsedDiscount + ">");
		tmpDumpList.add("  Discount Amount = <" + this.Discount + ">");
		tmpDumpList.add("  Discount Rule   = <" + this.DiscountRule + ">");
		tmpDumpList.add("  Output Stream   = <" + this.StreamName + ">");

		// Add Charge Packets
		tmpDumpList.addAll(getChargePacketsDump());

		// Add Balance Impacts
		tmpDumpList.addAll(getBalanceImpactsDump());

		tmpDumpList.add("       --- Extra Attributes ---");
		tmpDumpList.add("  Subscription id = <" + this.SubscriptionID + ">");
		tmpDumpList.add("  price unit = <" + this.PriceUnit + ">");
		tmpDumpList.add("  validity period start = <" + this.validityPeriodStart + ">");
		tmpDumpList.add("  validity period end = <" + this.validityPeriodEnd + ">");

		// Add Errors
		tmpDumpList.addAll(getErrorDump());

		return tmpDumpList;
	}

	@Override
	public String toString() {
		return concatHeader(0,
				String.format("[B=%s;CDR=%s;FAUT=%s;TP=%s]",
						this.NumberB,
						this.CDRType,
						this.UsageActivityId,
						this.TariffPlanId));
	}

	public String concatHeader(int tabNum, String text) {
		String result = null;

		if (tabNum == 0) {
			result = String.format("[SEQ:%d;A:%s] > %s", Seq, NumberA, text);
		} else if (tabNum == 1) {
			result = String.format("[SEQ:%d;A:%s] > \t%s", Seq, NumberA, text);
		} else if (tabNum == 2) {
			result = String.format("[SEQ:%d;A:%s] > \t\t%s", Seq, NumberA, text);
		} else if (tabNum == 3) {
			result = String.format("[SEQ:%d;A:%s] > \t\t\t%s", Seq, NumberA, text);
		}

		return result;
	}

	public boolean assignRUMValue(String RUM, double NewValue) {
		RUMInfo tmpRUM;
		int Index;

		for (Index = 0; Index < RUMs.size(); Index++) {
			tmpRUM = RUMs.get(Index);

			if (tmpRUM.RUMName.equals(RUM)) {
				tmpRUM.RUMQuantity = NewValue;
				return true;
			}
		}

		return false;
	}

	public void renewChargePacket(String rumName, double newRUMValue) {
		for (ChargePacket chargePacket : getChargePackets()) {
			if (chargePacket.rumName.equals(rumName)) {
				chargePacket.chargedValue = 0;
			}
		}

		assignRUMValue(rumName, newRUMValue);
	}

}
