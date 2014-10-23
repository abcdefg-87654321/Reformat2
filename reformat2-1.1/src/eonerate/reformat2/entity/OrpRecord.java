package eonerate.reformat2.entity;

import java.util.ArrayList;

import ElcRate.record.RatingRecord;

public class OrpRecord extends RatingRecord {

	//	public enum Field {
	//
	//		Record_Type,
	//		Record_Sequence_Number,
	//		Activity_Type,
	//		Result_Code,
	//		Result_Text,
	//		Reserved1,
	//		Reserved2,
	//		Record_Origin,
	//		Activity_Offered_Date_Time,
	//		Activity_Answered_Date_Time,
	//		Activity_Disconnect_Date_Time,
	//		A_Number,
	//		B_Number,
	//		External_Id,
	//		External_Id_type,
	//		MSC_ID,
	//		MSRN,
	//		Application_Type,
	//		Subtype,
	//		Unit_Type,
	//		Reference_Number,
	//		Initial_AUT,
	//		Charge_Type,
	//		SGSN,
	//		Clear_Cause,
	//		Cell_ID,
	//		Network_Calltype,
	//		Consumed_Amount,
	//		UTC_Offset,
	//		Origin,
	//		Ported_Number,
	//		Original_Charge_Amount,
	//		Original_Charge_Currency,
	//		GSM_Provider_ID,
	//		APN,
	//		QOS,
	//		Reservation_Type,
	//		PDP_Init_Type,
	//		Service_ID_Cell_ID_LAI,
	//		ECI_Message_Type,
	//		ECD_Associated_Number,
	//		ECI_MISSIDN,
	//		ECI_Alt_MISSIDN,
	//		ECI_Subscriber_Type,
	//		ECI_Bearer_Capability,
	//		ECI_Application_ID,
	//		ECI_Transaction_ID1,
	//		ECI_Transaction_ID2,
	//		ECI_Access_MT,
	//		ECI_MIN_translation,
	//		ECI_Charge_Amount,
	//		ECI_Prorate,
	//		ECI_SDP_ID_Origin,
	//		ECI_InforParam1,
	//		ECI_InfoParam2,
	//		Call_Processor_Cell_ID_LAI,
	//		Call_Processor_Pre_Post_Indicator,
	//		Call_Processor_POSTPAID_Type,
	//		Call_Processor_Call_Type,
	//		Call_Processor_Network_No_Charge,
	//		Call_Processor_Redirecting_Number,
	//		Call_Processor_MIN_IMSI,
	//		Call_Processor_Translated_Destination_Number,
	//		Call_Processor_A_party_MSRN,
	//		Call_Processor_NCF_Leg,
	//		Call_Processor_Call_Direction,
	//		Call_Processor_A_number_answer_time,
	//		Call_Processor_B_number_answer_time,
	//		Billable,
	//		External_System_Sequence_Number,
	//		Osa_ReservationStartTime,
	//		Osa_Reservation_Type,
	//		Osa_SubscriberId,
	//		Osa_ParamItem,
	//		Osa_ParamSubtype,
	//		Osa_ParamConfirmationId,
	//		Osa_ParamContract,
	//		Osa_TimezoneOffset,
	//		Osa_ParamQos,
	//		Osa_ParamService1,
	//		Osa_ParamService2,
	//		Osa_ParamService3,
	//		Osa_ParamService4,
	//		Osa_ParamInformational,
	//		Osa_ParamSubLocation,
	//		Osa_ParamSubLocationType,
	//		Osa_ParamOtherLocation,
	//		Osa_ParamOtherLocationType,
	//		Osa_ParamImsiMin,
	//		Osa_MerchantId,
	//		Osa_SessionDescription,
	//		Osa_SessionID,
	//		Osa_Correlation_Id,
	//		Osa_Correlation_Type,
	//		Osa_MerAccount,
	//		Osa_ApplDescText,
	//		Osa_ExtUnitTypeId,
	//		Osa_Currency,
	//		Osa_ReasonCode,
	//		Osa_Request_Type,
	//		Ocs_application,
	//		Ocs_application_description,
	//		Ocs_special_feature_digits,
	//		Ocs_activity_time,
	//		Ocs_Request_type,
	//		Ocs_T_Bit,
	//		Ocs_consumed_units,
	//		Ocs_consumed_unit_type,
	//		Ocs_currency_type,
	//		Ocs_imsi_num,
	//		Ocs_charge_item_id,
	//		Ocs_sessiong_id,
	//		Ocs_sub_session_id,
	//		Ocs_transaction_id,
	//		Ocs_subscriber_id,
	//		Ocs_session_desc,
	//		Ocs_sub_location,
	//		Ocs_sub_location_type,
	//		Ocs_sub_other_location,
	//		Ocs_sub_other_location_type,
	//		Ocs_tele_service_type,
	//		Call_processor_TimeZone,
	//		Offered_dt_msec,
	//		Answered_dt_msec,
	//		Disconnect_dt_msec,
	//		Point_Target_External_Id_Type,
	//		Network_Porting_Prefix,
	//		IMSI_A,
	//		IMSI_B,
	//		type1_Normalized_Number,
	//		type2_Normalized_Number,
	//		Calling_Number_Presentation,
	//		network_address_plan,
	//		Ocs_segment_id,
	//		Cp_Incoming_Call_id,
	//		Cp_Outgoing_call_id,
	//		Ocs_Start_Call_Dat_Time_Type,
	//		Ocs_End_Call_Dat_Time_Type
	//	}
	//
	//	public String Record_Type;
	//	public Integer Record_Sequence_Number;
	//	public Integer Activity_Type;
	//	public Integer Result_Code;
	//
	//	public String Result_Text;
	//	public String Reserved1;
	//	public String Reserved2;
	//	public String Record_Origin;
	//	public Long Activity_Offered_Date_Time;
	//	public Long Activity_Answered_Date_Time;
	//	public Long Activity_Disconnect_Date_Time;
	//	public String A_Number;
	//	public String B_Number;
	//	public String External_Id;
	//	public Integer External_Id_type;
	//	public String MSC_ID;
	//	public String MSRN;
	//	public Integer Application_Type;
	//	public Integer Subtype;
	//	public Integer Unit_Type;
	//	public Integer Reference_Number;
	//	public Integer Initial_AUT;
	//	public String Charge_Type;
	//	public String SGSN;
	//	public Integer Clear_Cause;
	//	public String Cell_ID;
	//	public Integer Network_Calltype;
	//	public Double Consumed_Amount;
	//	public Integer UTC_Offset;
	//	public Integer Origin;
	//	public String Ported_Number;
	//	public Float Original_Charge_Amount;
	//	public String Original_Charge_Currency;
	//	public String GSM_Provider_ID;
	//	public String APN;
	//	public String QOS;
	//	public Integer Reservation_Type;
	//	public Integer PDP_Init_Type;
	//	public String Service_ID_Cell_ID_LAI;
	//	public String ECI_Message_Type;
	//	public Integer ECD_Associated_Number;
	//	public String ECI_MISSIDN;
	//	public String ECI_Alt_MISSIDN;
	//	public String ECI_Subscriber_Type;
	//	public String ECI_Bearer_Capability;
	//	public String ECI_Application_ID;
	//	public Integer ECI_Transaction_ID1;
	//	public Integer ECI_Transaction_ID2;
	//	public boolean ECI_Access_MT;
	//	public boolean ECI_MIN_translation;
	//	public Float ECI_Charge_Amount;
	//	public boolean ECI_Prorate;
	//	public Integer ECI_SDP_ID_Origin;
	//	public String ECI_InforParam1;
	//	public String ECI_InfoParam2;
	//	public String Call_Processor_Cell_ID_LAI;
	//	public Integer Call_Processor_Pre_Post_Indicator;
	//	public Integer Call_Processor_POSTPAID_Type;
	//	public Integer Call_Processor_Call_Type;
	//	public String Call_Processor_Network_No_Charge;
	//	public String Call_Processor_Redirecting_Number;
	//	public String Call_Processor_MIN_IMSI;
	//	public String Call_Processor_Translated_Destination_Number;
	//	public String Call_Processor_A_party_MSRN;
	//	public String Call_Processor_NCF_Leg;
	//	public String Call_Processor_Call_Direction;
	//	public Long Call_Processor_A_number_answer_time;
	//	public Long Call_Processor_B_number_answer_time;
	//	public Boolean Billable;
	//	public String External_System_Sequence_Number;
	//	public Long Osa_ReservationStartTime;
	//	public Integer Osa_Reservation_Type;
	//	public String Osa_SubscriberId;
	//	public String Osa_ParamItem;
	//	public String Osa_ParamSubtype;
	//	public String Osa_ParamConfirmationId;
	//	public String Osa_ParamContract;
	//	public String Osa_TimezoneOffset;
	//	public String Osa_ParamQos;
	//	public String Osa_ParamService1;
	//	public String Osa_ParamService2;
	//	public String Osa_ParamService3;
	//	public String Osa_ParamService4;
	//	public String Osa_ParamInformational;
	//	public String Osa_ParamSubLocation;
	//	public String Osa_ParamSubLocationType;
	//	public String Osa_ParamOtherLocation;
	//	public String Osa_ParamOtherLocationType;
	//	public String Osa_ParamImsiMin;
	//	public String Osa_MerchantId;
	//	public String Osa_SessionDescription;
	//	public Integer Osa_SessionID;
	//	public Integer Osa_Correlation_Id;
	//	public Integer Osa_Correlation_Type;
	//	public Integer Osa_MerAccount;
	//	public String Osa_ApplDescText;
	//	public Integer Osa_ExtUnitTypeId;
	//	public String Osa_Currency;
	//	public Integer Osa_ReasonCode;
	//	public Integer Osa_Request_Type;
	//	public String Ocs_application;
	//	public String Ocs_application_description;
	//	public String Ocs_special_feature_digits;
	//	public Long Ocs_activity_time;
	//	public Integer Ocs_Request_type;
	//	public String Ocs_T_Bit;
	//	public Integer Ocs_consumed_units;
	//	public String Ocs_consumed_unit_type;
	//	public String Ocs_currency_type;
	//	public String Ocs_imsi_num;
	//	public String Ocs_charge_item_id;
	//	public String Ocs_sessiong_id;
	//	public String Ocs_sub_session_id;
	//	public Integer Ocs_transaction_id;
	//	public String Ocs_subscriber_id;
	//	public String Ocs_session_desc;
	//	public String Ocs_sub_location;
	//	public String Ocs_sub_location_type;
	//	public String Ocs_sub_other_location;
	//	public String Ocs_sub_other_location_type;
	//	public Integer Ocs_tele_service_type;
	//	public Integer Call_processor_TimeZone;
	//	public Integer Offered_dt_msec;
	//	public Integer Answered_dt_msec;
	//	public Integer Disconnect_dt_msec;
	//	public Integer Point_Target_External_Id_Type;
	//	public String Network_Porting_Prefix;
	//	public String IMSI_A;
	//	public String IMSI_B;
	//	public String type1_Normalized_Number;
	//	public String type2_Normalized_Number;
	//	public Integer Calling_Number_Presentation;
	//	public Integer network_address_plan;
	//	public Integer Ocs_segment_id;
	//	public String Cp_Incoming_Call_id;
	//	public Integer Cp_Outgoing_call_id;
	//	public Integer Ocs_Start_Call_Dat_Time_Type;
	//	public Integer Ocs_End_Call_Dat_Time_Type;

	public enum Field {

		RecordType,
		RecordSequenceNumber,
		ActivityType,
		ResultCode,
		ResultText,
		Reserved1,
		Reserved2,
		RecordOrigin,
		ActivityOfferedDateTime,
		ActivityAnsweredDateTime,
		ActivityDisconnectDateTime,
		ANumber,
		BNumber,
		ExternalId,
		ExternalIdtype,
		MSCID,
		MSRN,
		ApplicationType,
		Subtype,
		UnitType,
		ReferenceNumber,
		InitialAUT,
		ChargeType,
		SGSN,
		ClearCause,
		CellID,
		NetworkCalltype,
		ConsumedAmount,
		UTCOffset,
		Origin,
		PortedNumber,
		OriginalChargeAmount,
		OriginalChargeCurrency,
		GSMProviderID,
		APN,
		QOS,
		ReservationType,
		PDPInitType,
		ServiceIDCellIDLAI,
		ECIMessageType,
		ECIAssociatedNumber,
		ECIMISSIDN,
		ECIAltMISSIDN,
		ECISubscriberType,
		ECIBearerCapability,
		ECIApplicationID,
		ECITransactionID1,
		ECITransactionID2,
		ECIAccessMT,
		ECIMINtranslation,
		ECIChargeAmount,
		ECIProrate,
		ECISDPIDOrigin,
		ECIInforParam1,
		ECIInforParam2,
		CallProcessorCellIDLAI,
		CallProcessorPrePostIndicator,
		CallProcessorPOSTPAIDType,
		CallProcessorCallType,
		CallProcessorNetworkNoCharge,
		CallProcessorRedirectingNumber,
		CallProcessorMINIMSI,
		CallProcessorTranslatedDestinationNumber,
		CallProcessorApartyMSRN,
		CallProcessorNCFLeg,
		CallProcessorCallDirection,
		CallProcessorAnumberanswertime,
		CallProcessorBnumberanswertime,
		Billable,
		ExternalSystemSequenceNumber,
		OsaReservationStartTime,
		OsaReservationType,
		OsaSubscriberId,
		OsaParamItem,
		OsaParamSubtype,
		OsaParamConfirmationId,
		OsaParamContract,
		OsaTimezoneOffset,
		OsaParamQos,
		OsaParamService1,
		OsaParamService2,
		OsaParamService3,
		OsaParamService4,
		OsaParamInformational,
		OsaParamSubLocation,
		OsaParamSubLocationType,
		OsaParamOtherLocation,
		OsaParamOtherLocationType,
		OsaParamImsiMin,
		OsaMerchantId,
		OsaSessionDescription,
		OsaSessionID,
		OsaCorrelationId,
		OsaCorrelationType,
		OsaMerAccount,
		OsaApplDescText,
		OsaExtUnitTypeId,
		OsaCurrency,
		OsaReasonCode,
		OsaRequestType,
		Ocsapplication,
		Ocsapplicationdescription,
		Ocsspecialfeaturedigits,
		Ocsactivitytime,
		OcsRequesttype,
		OcsTBit,
		Ocsconsumedunits,
		Ocsconsumedunittype,
		Ocscurrencytype,
		Ocsimsinum,
		Ocschargeitemid,
		Ocssessiongid,
		Ocssubsessionid,
		Ocstransactionid,
		Ocssubscriberid,
		Ocssessiondesc,
		Ocssublocation,
		Ocssublocationtype,
		Ocssubotherlocation,
		Ocssubotherlocationtype,
		Ocsteleservicetype,
		CallprocessorTimeZone,
		Offereddtmsec,
		Answereddtmsec,
		Disconnectdtmsec,
		PointTargetExternalIdType,
		NetworkPortingPrefix,
		IMSIA,
		IMSIB,
		type1NormalizedNumber,
		type2NormalizedNumber,
		CallingNumberPresentation,
		networkaddressplan,
		Ocssegmentid,
		CpIncomingCallid,
		CpOutgoingcallid,
		OcsStartCallDatTimeType,
		OcsEndCallDatTimeType
	}

	public String RecordType;
	public Integer RecordSequenceNumber;
	public Integer ActivityType;
	public Integer ResultCode;

	public String ResultText;
	public String Reserved1;
	public String Reserved2;
	public String RecordOrigin;
	public Long ActivityOfferedDateTime;
	public Long ActivityAnsweredDateTime;
	public Long ActivityDisconnectDateTime;
	public String ANumber;
	public String BNumber;
	public String ExternalId;
	public Integer ExternalIdtype;
	public String MSCID;
	public String MSRN;
	public Integer ApplicationType;
	public Integer Subtype;
	public Integer UnitType;
	public Integer ReferenceNumber;
	public Integer InitialAUT;
	public String ChargeType;
	public String SGSN;
	public Integer ClearCause;
	public String CellID;
	public Integer NetworkCalltype;
	public Double ConsumedAmount;
	public Integer UTCOffset;
	public Integer Origin;
	public String PortedNumber;
	public Float OriginalChargeAmount;
	public String OriginalChargeCurrency;
	public String GSMProviderID;
	public String APN;
	public String QOS;
	public Integer ReservationType;
	public Integer PDPInitType;
	public String ServiceIDCellIDLAI;
	public String ECIMessageType;
	public Integer ECIAssociatedNumber;
	public String ECIMISSIDN;
	public String ECIAltMISSIDN;
	public String ECISubscriberType;
	public String ECIBearerCapability;
	public String ECIApplicationID;
	public Integer ECITransactionID1;
	public Integer ECITransactionID2;
	public boolean ECIAccessMT;
	public boolean ECIMINtranslation;
	public Float ECIChargeAmount;
	public boolean ECIProrate;
	public Integer ECISDPIDOrigin;
	public String ECIInforParam1;
	public String ECIInforParam2;
	public String CallProcessorCellIDLAI;
	public Integer CallProcessorPrePostIndicator;
	public Integer CallProcessorPOSTPAIDType;
	public Integer CallProcessorCallType;
	public String CallProcessorNetworkNoCharge;
	public String CallProcessorRedirectingNumber;
	public String CallProcessorMINIMSI;
	public String CallProcessorTranslatedDestinationNumber;
	public String CallProcessorApartyMSRN;
	public String CallProcessorNCFLeg;
	public String CallProcessorCallDirection;
	public Long CallProcessorAnumberanswertime;
	public Long CallProcessorBnumberanswertime;
	public Boolean Billable;
	public String ExternalSystemSequenceNumber;
	public Long OsaReservationStartTime;
	public Integer OsaReservationType;
	public String OsaSubscriberId;
	public String OsaParamItem;
	public String OsaParamSubtype;
	public String OsaParamConfirmationId;
	public String OsaParamContract;
	public String OsaTimezoneOffset;
	public String OsaParamQos;
	public String OsaParamService1;
	public String OsaParamService2;
	public String OsaParamService3;
	public String OsaParamService4;
	public String OsaParamInformational;
	public String OsaParamSubLocation;
	public String OsaParamSubLocationType;
	public String OsaParamOtherLocation;
	public String OsaParamOtherLocationType;
	public String OsaParamImsiMin;
	public String OsaMerchantId;
	public String OsaSessionDescription;
	public Integer OsaSessionID;
	public Integer OsaCorrelationId;
	public Integer OsaCorrelationType;
	public Integer OsaMerAccount;
	public String OsaApplDescText;
	public Integer OsaExtUnitTypeId;
	public String OsaCurrency;
	public Integer OsaReasonCode;
	public Integer OsaRequestType;
	public String Ocsapplication;
	public String Ocsapplicationdescription;
	public String Ocsspecialfeaturedigits;
	public Long Ocsactivitytime;
	public Integer OcsRequesttype;
	public String OcsTBit;
	public Integer Ocsconsumedunits;
	public String Ocsconsumedunittype;
	public String Ocscurrencytype;
	public String Ocsimsinum;
	public String Ocschargeitemid;
	public String Ocssessiongid;
	public String Ocssubsessionid;
	public Integer Ocstransactionid;
	public String Ocssubscriberid;
	public String Ocssessiondesc;
	public String Ocssublocation;
	public String Ocssublocationtype;
	public String Ocssubotherlocation;
	public String Ocssubotherlocationtype;
	public Integer Ocsteleservicetype;
	public Integer CallprocessorTimeZone;
	public Integer Offereddtmsec;
	public Integer Answereddtmsec;
	public Integer Disconnectdtmsec;
	public Integer PointTargetExternalIdType;
	public String NetworkPortingPrefix;
	public String IMSIA;
	public String IMSIB;
	public String type1NormalizedNumber;
	public String type2NormalizedNumber;
	public Integer CallingNumberPresentation;
	public Integer networkaddressplan;
	public Integer Ocssegmentid;
	public String CpIncomingCallid;
	public Integer CpOutgoingcallid;
	public Integer OcsStartCallDatTimeType;
	public Integer OcsEndCallDatTimeType;

	@Override
	public ArrayList<String> getDumpInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
