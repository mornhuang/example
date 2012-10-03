package cn.itsz.newsim.common;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import cn.itsz.newsim.dto.response.entity.EnquireRTQResponseEntity;
import cn.itsz.newsim.dto.response.entity.EnquireShortRTQResponseEntity;
import cn.itsz.newsim.exception.ITSZException;

import com.itsz.rtq.provider.quot.DetailQuot;
import com.itsz.rtq.provider.quot.QuotRecord;

public class ActionUtil {
	public static Locale lang2Locale(String lang) {
		Locale locale = Locale.US;
		if (Constants.Lang.LANGS.indexOf(lang) != -1) {
			String[] langs = StringUtils.split(lang, '_');
			if (langs.length > 1)
				locale = new Locale(langs[0], langs[1]);
		}
		return locale;
	}

	public static String locale2QSlang(Locale locale) {
		String lang = Constants.Lang.ENG;
		if (Locale.TAIWAN.equals(locale)) {
			lang = Constants.Lang.BIG5;
		} else if (Locale.SIMPLIFIED_CHINESE.equals(locale)) {
			lang = Constants.Lang.GB;
		}
		return lang;
	}
	
	public static void SetCookieLang(HttpServletResponse response, Locale locale) {
		Cookie cookie = new Cookie(Constants.Lang.COOKIE_LANG, locale.toString());
		cookie.setMaxAge(60 * 60 * 24 * 10); //10 day
		response.addCookie(cookie);
	}

	public static String Obj2JSONString(Object obj) {
		return JSONObject.fromObject(obj).toString();
	}

	public static String Array2JSONString(Object obj) {
		return JSONArray.fromObject(obj).toString();
	}

	private static String transValue(String price) {
		if (StringUtils.isBlank(price)) {
			return Constants.QS.RTQ_NULL_PRICE;
		}
		return price;
	}
	
	public static String getSync(String clv) {
		String sync = Constants.Sync.SYNC;
		if (StringUtils.isNotBlank(clv) && clv.length() > 4) {
			sync = clv.substring(4, 5);
		}
		return sync;
	}
	
	public static String fmtCode(String code, int size) {
		return StringUtils.leftPad(code, size, '0');
	}
	
	public static void packagingAndThrowException(String errorCode) {
		ITSZException itszException = new ITSZException();
		itszException.setErrorCode(errorCode);
		throw itszException;
	}
	
	public static List<EnquireRTQResponseEntity> assembleEnquireRtqResponse(DetailQuot[] detailQuots){
		List<EnquireRTQResponseEntity> enquireRtqResponses = new ArrayList<EnquireRTQResponseEntity>();
		for (int i = 0; i < detailQuots.length; i++) {
			DetailQuot detailQuot = detailQuots[i];
			if(detailQuot != null){
				EnquireRTQResponseEntity enquireRtqResponse = new EnquireRTQResponseEntity();
				if(detailQuot.Quot.Status == 0 ||detailQuot.Quot.Status == 2){
				enquireRtqResponse.setCode(detailQuot.Quot.Code);
				enquireRtqResponse.setName(detailQuot.Quot.Name);
				enquireRtqResponse.setTime(detailQuot.Quot.Time);
				enquireRtqResponse.setPClose(transValue(detailQuot.Quot.PClose));
				enquireRtqResponse.setPrice(transValue(detailQuot.Quot.Price));
				enquireRtqResponse.setOpen(transValue(detailQuot.Quot.Open));
				enquireRtqResponse.setHigh(transValue(detailQuot.Quot.High));
				enquireRtqResponse.setLow(transValue(detailQuot.Quot.Low));
				enquireRtqResponse.setClose(transValue(detailQuot.Quot.Close));
				enquireRtqResponse.setTurnover(transValue(detailQuot.Quot.Turnover));
				enquireRtqResponse.setChange(transValue(detailQuot.Quot.Change));
				enquireRtqResponse.setPctChange(transValue(detailQuot.Quot.PctChange));
				enquireRtqResponse.setVolume(transValue(detailQuot.Quot.Volume));
				enquireRtqResponse.setYearHigh(transValue(detailQuot.Quot.YearHigh));
				enquireRtqResponse.setYearLow(transValue(detailQuot.Quot.YearLow));
				enquireRtqResponse.setPe(transValue(detailQuot.Quot.Pe));
				enquireRtqResponse.setMarketCap(transValue(detailQuot.Quot.MarketCap));
				enquireRtqResponse.setLowSpread(transValue(detailQuot.Quot.LowSpread));
				enquireRtqResponse.setHighSpread(transValue(detailQuot.Quot.HighSpread));
				enquireRtqResponse.setLotSize(transValue(detailQuot.Quot.LotSize));
				enquireRtqResponse.setGearing(transValue(detailQuot.Quot.Gearing));
				enquireRtqResponse.setExpDate(transValue(detailQuot.Quot.ExpDate));
				enquireRtqResponse.setExePrice(transValue(detailQuot.Quot.ExePrice));
				enquireRtqResponse.setConvRatio(transValue(detailQuot.Quot.ConvRatio));
				enquireRtqResponse.setPreminum(transValue(detailQuot.Quot.Preminum));
				enquireRtqResponse.setNote(transValue(detailQuot.Quot.Note));
				enquireRtqResponse.setYield(transValue(detailQuot.Quot.Yield));
				enquireRtqResponse.setCurrency(transValue(detailQuot.Quot.Currency));
				enquireRtqResponse.setBestBid(transValue(detailQuot.Quot.bestBid));
				enquireRtqResponse.setBestAsk(transValue(detailQuot.Quot.bestAsk));
//				enquireRtqResponse.setCanBidBPrice(getFlag(detailQuot.Quot.bestBid));
//				enquireRtqResponse.setCanAskBPrice(getFlag(detailQuot.Quot.bestAsk));
				enquireRtqResponse.setLatest_traded_time(detailQuot.Quot.latest_traded_time);
				enquireRtqResponse.setLatest_traded_vol(transValue(detailQuot.Quot.latest_traded_vol));
				enquireRtqResponse.setLatest_traded_price(transValue(detailQuot.Quot.latest_traded_price));
				enquireRtqResponse.setLogTime(detailQuot.Quot.LogTime);
//				enquireRtqResponse.setLast_update_time(formatTime(detailQuot.Quot.Time));
				}
				enquireRtqResponse.setBody_type(transValue(detailQuot.Quot.body_type));
				enquireRtqResponse.setStatus(detailQuot.Quot.Status);
				enquireRtqResponses.add(enquireRtqResponse);
			}
		}
		return enquireRtqResponses;
	}

	public static List<EnquireRTQResponseEntity> assembleSimpleRtqResponse(QuotRecord[] quotRecords){
		List<EnquireRTQResponseEntity> enquireRtqResponses = new ArrayList<EnquireRTQResponseEntity>();
		for (int i = 0; i < quotRecords.length; i++) {
			QuotRecord quotRecord = quotRecords[i];
			if(quotRecord != null){
				EnquireRTQResponseEntity enquireRtqResponse = new EnquireRTQResponseEntity();
				if(quotRecord.Status == 0 ||quotRecord.Status == 2){
				enquireRtqResponse.setCode(quotRecord.Code);
				enquireRtqResponse.setName(quotRecord.Name);
				enquireRtqResponse.setTime(formatDateTime(quotRecord.Time));
				enquireRtqResponse.setPClose(transValue(quotRecord.PClose));
				enquireRtqResponse.setPrice(transValue(quotRecord.Price));
				enquireRtqResponse.setOpen(transValue(quotRecord.Open));
				enquireRtqResponse.setHigh(transValue(quotRecord.High));
				enquireRtqResponse.setLow(transValue(quotRecord.Low));
				enquireRtqResponse.setClose(transValue(quotRecord.Close));
				enquireRtqResponse.setTurnover(transValue(quotRecord.Turnover));
				enquireRtqResponse.setChange(transValue(quotRecord.Change));
				enquireRtqResponse.setPctChange(transValue(quotRecord.PctChange));
				enquireRtqResponse.setVolume(transValue(quotRecord.Volume));
				enquireRtqResponse.setYearHigh(transValue(quotRecord.YearHigh));
				enquireRtqResponse.setYearLow(transValue(quotRecord.YearLow));
				enquireRtqResponse.setPe(transValue(quotRecord.Pe));
				enquireRtqResponse.setMarketCap(transValue(quotRecord.MarketCap));
				enquireRtqResponse.setLowSpread(transValue(quotRecord.LowSpread));
				enquireRtqResponse.setHighSpread(transValue(quotRecord.HighSpread));
				enquireRtqResponse.setLotSize(transValue(quotRecord.LotSize));
				enquireRtqResponse.setGearing(transValue(quotRecord.Gearing));
				enquireRtqResponse.setExpDate(transValue(quotRecord.ExpDate));
				enquireRtqResponse.setExePrice(transValue(quotRecord.ExePrice));
				enquireRtqResponse.setConvRatio(transValue(quotRecord.ConvRatio));
				enquireRtqResponse.setPreminum(transValue(quotRecord.Preminum));
				enquireRtqResponse.setNote(transValue(quotRecord.Note));
				enquireRtqResponse.setYield(transValue(quotRecord.Yield));
				enquireRtqResponse.setCurrency(transValue(quotRecord.Currency));
				enquireRtqResponse.setBestBid(transValue(quotRecord.bestBid));
				enquireRtqResponse.setBestAsk(transValue(quotRecord.bestAsk));
				enquireRtqResponse.setLatest_traded_time(quotRecord.latest_traded_time);
				enquireRtqResponse.setLatest_traded_vol(transValue(quotRecord.latest_traded_vol));
				enquireRtqResponse.setLatest_traded_price(transValue(quotRecord.latest_traded_price));
				enquireRtqResponse.setLogTime(quotRecord.LogTime);
				//xwquan 2011-04-13
				enquireRtqResponse.setLast_update_time(quotRecord.last_update_time);
				}
				enquireRtqResponse.setBody_type(transValue(quotRecord.body_type));
				enquireRtqResponse.setStatus(quotRecord.Status);
				enquireRtqResponses.add(enquireRtqResponse);
			}
		}
		return enquireRtqResponses;
	}
	
	public static List<EnquireShortRTQResponseEntity> assembleShortRtqResponse(QuotRecord[] quotRecords) {
		List<EnquireShortRTQResponseEntity> enquireRtqResponses = new ArrayList<EnquireShortRTQResponseEntity>();
		for (int i = 0; i < quotRecords.length; i++) {
			QuotRecord quotRecord = quotRecords[i];
			if (quotRecord != null) {
				EnquireShortRTQResponseEntity enquireRtqResponse = new EnquireShortRTQResponseEntity();
				if (quotRecord.Status == 0 || quotRecord.Status == 2) {
					enquireRtqResponse.setCode(quotRecord.Code);
					enquireRtqResponse.setName(quotRecord.Name);
					enquireRtqResponse.setPrice(transValue(quotRecord.Price));
					enquireRtqResponse.setLotSize(transValue(quotRecord.LotSize));
					enquireRtqResponse.setBestBid(transValue(quotRecord.bestBid));
					enquireRtqResponse.setBestAsk(transValue(quotRecord.bestAsk));
					enquireRtqResponses.add(enquireRtqResponse);
				}
			}
		}
		return enquireRtqResponses;
	}
	
	public static BigDecimal calSpread(BigDecimal price) {
		String spread = "0";
		if (price.compareTo(B_001) > 0 && price.compareTo(B_025) <= 0)
			spread = "0.001";
		else if (price.compareTo(B_025) > 0 && price.compareTo(B_05) <= 0)
			spread = "0.005";
		else if (price.compareTo(B_05) > 0 && price.compareTo(B_10) <= 0)
			spread = "0.010";
		else if (price.compareTo(B_10) > 0 && price.compareTo(B_20) <=0)
			spread = "0.020";
		else if (price.compareTo(B_20) > 0 && price.compareTo(B_100) <=0)
			spread = "0.050";
		else if (price.compareTo(B_100) > 0 && price.compareTo(B_200) <=0)
			spread = "0.100";
		else if (price.compareTo(B_200) > 0 && price.compareTo(B_500) <=0)
			spread = "0.200";
		else if (price.compareTo(B_500) > 0 && price.compareTo(B_1000) <=0)
			spread = "0.500";
		else if (price.compareTo(B_1000) > 0 && price.compareTo(B_2000) <=0)
			spread = "1.000";
		else if (price.compareTo(B_2000) > 0 && price.compareTo(B_5000) <=0)
			spread = "2.000";
		else
			spread = "5.000";

		return new BigDecimal(spread);
	}
	
	public static String formatDateTime(String time) {
		long   mili=Long.parseLong(time); 
		Date   d=new   Date(mili); 
		SimpleDateFormat   format=new   SimpleDateFormat( "yyyy-MM-dd HH:mm"); 
		String   dateTime=format.format(d);
		
		return dateTime;
	}
	
	private static final BigDecimal B_001 = new BigDecimal("0.01");
	private static final BigDecimal B_025 = new BigDecimal("0.25");
	private static final BigDecimal B_05 = new BigDecimal("0.05");
	private static final BigDecimal B_10 = new BigDecimal("10");
	private static final BigDecimal B_20 = new BigDecimal("20");
	private static final BigDecimal B_100 = new BigDecimal("100");
	private static final BigDecimal B_200 = new BigDecimal("200");
	private static final BigDecimal B_500 = new BigDecimal("500");
	private static final BigDecimal B_1000 = new BigDecimal("1000");
	private static final BigDecimal B_2000 = new BigDecimal("2000");
	private static final BigDecimal B_5000 = new BigDecimal("5000");
	
}
