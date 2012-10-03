update NW_CS_PARAMETER set value='/tmp/' where key_pk='Fund_Deposit_FilePath';
alter table NW_SVCS_ACES_LOG modify(RTQ_PRDR VARCHAR2(40));