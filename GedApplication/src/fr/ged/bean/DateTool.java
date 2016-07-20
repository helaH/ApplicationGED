/*
 */
package fr.ged.bean;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



/** ********************************************************************************************* */
/*																								*/
/* Liste des params modifiant le format des dates. */
/*																								*/
/* Letter Date or Time Component Presentation Examples */
/* G Era designator Text AD */
/* y Year Year 1996; 96 */
/* M Month in year Month July; Jul; 07 */
/* w Week in year Number 27 */
/* W Week in month Number 2 */
/* D Day in year Number 189 */
/* d Day in month Number 10 */
/* F Day of week in month Number 2 */
/* E Day in week Text Tuesday; Tue */
/* a Am/pm marker Text PM */
/* H Hour in day (0-23) Number 0 */
/* k Hour in day (1-24) Number 24 */
/* K Hour in am/pm (0-11) Number 0 */
/* h Hour in am/pm (1-12) Number 12 */
/* m Minute in hour Number 30 */
/* s Second in minute Number 55 */
/* S Millisecond Number 978 */
/* z Time zone General time zone Pacific Standard Time; PST; GMT-08:00 */
/* Z Time zone RFC 822 time zone -0800 */
/*																								*/
/** ********************************************************************************************* */

public class DateTool {

	/**
	 * Fuseau horaire courant
	 */
	private static Locale currentLocale = new Locale("fr", "FR");

	/**
	 * Pour le format de la date systeme des traces.
	 */
	private static SimpleDateFormat sysFormatter = new SimpleDateFormat("[ yyyy.MM.dd-HH:mm:ss ]", currentLocale);

	/**
	 * Pour le format de la date pour extension de fichier pas defaut.
	 */
	private static SimpleDateFormat sysFormatterExt = new SimpleDateFormat("yyyyMMdd", currentLocale);

	/**
	 * Pour le format de la date dans le fichier de déprovisionnement du Cirpack.
	 */
	private static SimpleDateFormat sysFormatterExtDeprovCPK = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",
			currentLocale);

	/**
	 * Retourne la date courrante dans le format voulu
	 * 
	 * @param formatStr : La format souhaite
	 * @return String : La chaine correspondant a la date actuelle.
	 */
	public static String getDateString(String formatStr) {
		String dateStr;
		SimpleDateFormat formatter;

		formatter = new SimpleDateFormat(formatStr, currentLocale);
		dateStr = formatter.format(new java.util.Date());

		return (dateStr);
	}

	/**
	 * Retourne la date courrante dans le format voulu
	 * 
	 * @param formatStr : La format souhaite
	 * @return String : La chaine correspondant a la date actuelle.
	 */
	public static String getDateString(java.util.Date date, String formatStr) {
		String dateStr;

		if (date != null) {
			SimpleDateFormat formatter;

			formatter = new SimpleDateFormat(formatStr, currentLocale);
			dateStr = formatter.format(date);
		} else {
			dateStr = "";
		}

		return (dateStr);
	}

	/**
	 * Retourne la date de reception d'un fichier sous le format : dd MM yyyy hh mm.
	 * 
	 * @param file : Le fichier dont on veut recuperer la date.
	 * @return String : La chaine correspondant a la date du fichier.
	 */
	public static String getDateReception(File file) {
		String dateStr;
		SimpleDateFormat formatter;
		long fileDateLong;

		formatter = new SimpleDateFormat("dd MM yyyy hh mm", currentLocale);

		fileDateLong = file.lastModified();
		dateStr = formatter.format(new java.util.Date(fileDateLong));

		return (dateStr);
	}

	/**
	 * Pour afficher la date courante du System sous la forme [yyyy.MM.dd-HH:mm:ss]. Cette methode est
	 * utilisee par les traces
	 */
	public static String getCurrentTimeStr() {
		long sysDateValue = System.currentTimeMillis();
		String sysDateStr = sysFormatter.format(new java.util.Date(sysDateValue));

		return (sysDateStr);
	}

	/**
	 * Pour recuperer la date pour extention de fichier. sous la forme yyyyMMdd Cette methode est utilisee par
	 * les traces
	 */
	public static String getDateForFileExtention() {
		long sysDateValue = System.currentTimeMillis();
		String sysDateStr = sysFormatterExt.format(new java.util.Date(sysDateValue));

		return (sysDateStr);
	}

	/**
	 * Pour recuperer la date pour le fichier de déprov du Cirpack. sous la forme dd/MM/yyyy hh:mm:ss
	 */
	public static String getDateForDeprovCPK() {
		long sysDateValue = System.currentTimeMillis();
		String sysDateStr = sysFormatterExtDeprovCPK.format(new java.util.Date(sysDateValue));

		return (sysDateStr);
	}

	/**
	 * Pour recuperer la date a placer en extention de fichier. sous la forme dateformat
	 */
	public static String getDateForFileExtention(String dateformat) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateformat, currentLocale);
		long sysDateValue = System.currentTimeMillis();
		String sysDateStr = formatter.format(new java.util.Date(sysDateValue));

		return (sysDateStr);
	}

	/**
	 * Pour recuperer la date
	 */
	public static java.util.Date parseDate(String dateStr, String dateformat) throws Exception {
		java.util.Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat(dateformat, currentLocale);
		date = formatter.parse(dateStr);

		return (date);
	}

	/**
	 * Pour recuperer la date en utilisant un objet Locale Specifique pour la chaine en entree
	 */
	public static java.util.Date parseDateWithLocale(String dateStr, String dateformat, Locale localeInit)
			throws Exception {
		java.util.Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat(dateformat, localeInit);
		date = formatter.parse(dateStr);
		return (date);
	}

	/**
	 * Pour recuperer la date
	 */
	public static java.sql.Date parseSqlDate(String dateStr, String dateformat) throws Exception {
		java.util.Date date = parseDate(dateStr, dateformat);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		return (sqlDate);
	}

	/**
	 * Pour recuperer la date
	 */
	public static java.sql.Date getSqlDate() throws Exception {
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		date = null;

		return (sqlDate);
	}

	/**
	 * Pour recuperer la date
	 */
	public static java.sql.Date getSqlDate(java.util.Date date) throws Exception {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		return (sqlDate);
	}

	public static String parseDate(String dateStr, String dateformatInit, String dateformatOut) throws Exception {
		return getDateString(parseDate(dateStr, dateformatInit), dateformatOut);
	}

	public static String parseDateWithLocale(String dateStr, String dateformatInit, String dateformatOut,
			Locale localeInit) throws Exception {
		return getDateString(parseDateWithLocale(dateStr, dateformatInit, localeInit), dateformatOut);
	}

	public static String simpleParseDate(String dateStr, String dateformatInit, String dateformatOut) throws Exception {
		String dateResult = "";
		if (dateStr == null) {
			dateResult = "";
		} else if (dateStr.trim().length() == 0) {
			dateResult = "";
		} else {
			dateResult = getDateString(parseDate(dateStr, dateformatInit), dateformatOut);
		}
		return (dateResult);
	}

	
	/**
	 * Vérifie une date (String) en la comparant au format voulu
	 * 
	 * @param dateToTest la date a tester
	 * @param format le format voulu pour cette date
	 * @return true si la date est valide, sinon false
	 */
	public static boolean checkDate(String dateToTest, String format) {
		if (dateToTest == null || dateToTest.trim().equals("")) {
			return false;
		}
		boolean dateOK = false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setLenient(false);
			Date d = sdf.parse(dateToTest.trim());
			if (d != null) {
				dateOK = true;
			}
		} catch (Exception e) {
			dateOK = false;
		}

		return dateOK;
	}

	public static Date calculate(Date dateFrom, int delai) throws Exception {
		if (dateFrom == null) {
			throw new Exception("invalid from date");
		}
		if (delai < -1000 || delai > 1000) {
			throw new Exception("invalid delai in days - must be between -1000 and 1000");
		}
		Calendar cursor = Calendar.getInstance(currentLocale);
		cursor.setTime(dateFrom);
		cursor.add(Calendar.DAY_OF_MONTH, delai);
		return cursor.getTime();
	}

	
	

}
