package com.sqe.staticanalysis;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;


/**
 * -- ---------- --------------------------------------------------------- -- PGM 이름 : Entity -- PGM 내용 : VO를 담당할 객체 --
 * ---------- ---------------------------------------------------------
 */
public class RecursiveLoop extends HashMap {

	private static final long serialVersionUID = 1L;
	private boolean isMultPart = false;

	public RecursiveLoop() {
	}

	public RecursiveLoop(Map map) throws Exception {
		this.parseMap(map);
	}

	/**
	 * ResultSet 에서 컬럼 이름을 key로 해서 그 값을 Entity 에 저장하는 method
	 *
	 * @param ResultSet
	 * @exception SQLException
	 */
	public void parseResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int size = md.getColumnCount();

		for (int i = 1; i <= size; i++) {
			this.setValue(md.getColumnName(i), rs.getString(i));
		}
	}

	/**
	 * ResultSet 에서 컬럼 이름과 인덱스를 key로 해서 그 값을 Entity 에 저장하는 method
	 *
	 * @param int
	 * @param ResultSet
	 * @exception SQLException
	 */
	public void parseResultSet(int iIndex, ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int size = md.getColumnCount();

		String sColumnName = null;
		String sColumnValue = null;

		for (int i = 1; i <= size; i++) {
			this.setValue(new StringBuffer(md.getColumnName(i)).append(iIndex).toString(), rs.getString(i));
		}
	}


	/**
	 * DWR 이용시 파라메터로 받는 Map을 Entity에 바인딩 시킨다. - 명준민
	 */
	public void parseMap(Map map) throws Exception {

		Iterator it = map.keySet().iterator();

		String key = "";
		String val = "";

		while (it.hasNext()) {

			key = it.next().toString();

			System.out.println(key + " : " + val);

			this.setValue(key, val);
		}
	}

	public void setValue(String sKey, String sValue) {
		if (sValue != null) {
			this.put(sKey, sValue);
		}
	}

	public void setValue(String sKey, String[] sValues) {
		this.put(sKey, sValues);
	}

	public void setValue(String sKey, byte[] yValues) {
		String sValue = null;

		if (yValues != null) {
			sValue = new String(yValues);
		}
		this.put(sKey, sValue);
	}

	public void setValue(String sKey, byte yValue) {
		this.put(sKey, Byte.toString(yValue));
	}

	public void setValue(String sKey, char[] cValues) {
		String sValue = null;
		if (cValues != null) {
			sValue = new String(cValues);
		}
		this.put(sKey, sValue);
	}

	public void setValue(String sKey, char cValue) {
		this.put(sKey, String.valueOf(cValue));
	}

	public void setValue(String sKey, float fValue) {
		this.put(sKey, String.valueOf(fValue));
	}

	public void setValue(String sKey, boolean bValue) {
		this.put(sKey, String.valueOf(bValue));
	}

	public void setValue(String sKey, short tValue) {
		this.put(sKey, String.valueOf(tValue));
	}

	public void setValue(String sKey, int iValue) {
		this.put(sKey, String.valueOf(iValue));
	}

	public void setValue(String sKey, long lValue) {
		this.put(sKey, String.valueOf(lValue));
	}

	public void setValue(String sKey, double dValue) {
		this.put(sKey, String.valueOf(dValue));
	}

	public void setValue(String sKey, java.util.Date value) {
		String sValue = null;
		if (value != null) {
			;
		}
		sValue = value.toString();
		this.put(sKey, sValue);
	}

	public void setValue(String sKey, Vector value) {
		this.put(sKey, value);
	}

	public void setValue(String sKey, List value) {
		this.put(sKey, value);
	}

	public void setValue(String sKey, Hashtable value) {
		this.put(sKey, value);
	}

	public void setValue(String sKey, RecursiveLoop value) {
		this.put(sKey, value);
	}

	public void setValue(String sKey, HashMap value) {
		this.put(sKey, value);
	}

	public void setValue(String sKey, Map value) {
		this.put(sKey, value);
	}

	public String getString(String sKey) {
		String sValue = null;
		Object obj = null;

		try {
			obj = this.get(sKey);

			if (obj instanceof String) {
				sValue = (String) obj;
			} else if (obj instanceof String[]) {
				sValue = ((String[]) obj)[0];
			} else {
				sValue = obj.toString();
			}
		} catch (Exception e) {
			sValue = "";
		}
		return sValue;
	}

	/**
	 *
	 * Entity 에 저장된 String[] 를 return 하는 method String 이 저장되어 있을 경우에는 length 가 1인 String[] 를 return 한다.
	 *
	 * @param String
	 *            sKey
	 * @return String[]
	 * @exception Exception
	 */
	public String[] getStrings(String sKey) {
		String[] sValues = null;
		Object obj = null;

		try {
			obj = this.get(sKey);
			if (obj instanceof String) {
				sValues = new String[1];
				sValues[0] = (String) obj;
			} else {
				sValues = (String[]) obj;
			}
		} catch (Exception e) {
		}
		return sValues;
	}

	public byte getByte(String sKey) {
		byte yResult = (byte) 0;

		try {
			yResult = Byte.parseByte((String) this.get(sKey));
		} catch (Exception e) {
		}

		return yResult;
	}

	public byte[] getBytes(String sKey) {
		byte[] yResults = null;

		try {
			yResults = ((String) this.get(sKey)).getBytes();
		} catch (Exception e) {
		}

		return yResults;
	}

	public char getChar(String sKey) {
		char cResult = (char) 0;

		try {
			cResult = ((String) this.get(sKey)).charAt(0);
		} catch (Exception e) {
		}

		return cResult;
	}

	public char[] getChars(String sKey) {
		char[] cResults = null;

		try {
			cResults = ((String) this.get(sKey)).toCharArray();
		} catch (Exception e) {
		}

		return cResults;
	}

	public float getFloat(String sKey) {
		float fResult = 0;

		try {
			fResult = Float.parseFloat((String) this.get(sKey));
		} catch (Exception e) {
		}

		return fResult;
	}

	public boolean getBoolean(String sKey) {
		boolean bResult = false;

		try {
			bResult = Boolean.getBoolean((String) this.get(sKey));
		} catch (Exception e) {
		}

		return bResult;
	}

	public short getShort(String sKey) {
		short tResult = 0;
		Object o = this.get(sKey);
		try {
			o = ((String) o).replaceAll(",", "");
			tResult = Short.parseShort((String) o);
		} catch (Exception e) {
		}

		return tResult;
	}

	public int getInt(String sKey) {
		int iResult = 0;

		Object o = this.get(sKey);

		try {
			o = ((String) o).replaceAll(",", "");
			iResult = Integer.parseInt((String) o);
		} catch (Exception e) {
		}

		try {
			Class classType = o.getClass();

			if (classType == BigDecimal.class) {
				iResult = Integer.parseInt(o.toString());
			}
		} catch (Exception e) {
		}

		return iResult;
	}

	public long getLong(String sKey) {
		long lResult = 0;
		Object o = this.get(sKey);

		try {
			o = ((String) o).replaceAll(",", "");
			lResult = Long.parseLong((String) o);
		} catch (Exception e) {
		}

		try {
			Class classType = o.getClass();

			if (classType == BigDecimal.class) {
				lResult = Long.parseLong(o.toString());
			}
		} catch (Exception e) {
		}

		return lResult;
	}

	public double getDouble(String sKey) {
		double dResult = 0;
		Object o = this.get(sKey);

		try {
			o = ((String) o).replaceAll(",", "");
			dResult = Double.parseDouble((String) o);
		} catch (Exception e) {
		}

		try {
			Class classType = o.getClass();

			if (classType == BigDecimal.class) {
				dResult = Double.parseDouble(o.toString());
			}
		} catch (Exception e) {
		}

		return dResult;
	}

	public java.util.Date getDate(String sKey) {
		java.util.Date result = null;

		try {
			String sDate = (String) this.get(sKey);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			result = formatter.parse(sDate, pos);
		} catch (Exception e) {
		}

		return result;
	}

	public Vector getVector(String sKey) {
		Vector vResult = null;

		try {
			vResult = (Vector) this.get(sKey);
		} catch (Exception e) {
		}

		return vResult;
	}

	public List getList(String sKey) {
		List alResult = null;

		try {
			alResult = (List) this.get(sKey);
		} catch (Exception e) {
			alResult = new ArrayList();
		}

		return alResult;
	}

	public Hashtable getHashtable(String sKey) {
		Hashtable value = null;

		try {
			value = (Hashtable) this.get(sKey);

			if (value == null) {
				value = new Hashtable();
			}
		} catch (Exception e) {
			value = new Hashtable();
		}

		return value;
	}

	public Map getMap(String sKey) {
		Map value = null;

		try {
			value = (Map) this.get(sKey);

			if (value == null) {
				value = new HashMap();
			}
		} catch (Exception e) {
			value = new HashMap();
		}

		return value;
	}

	public RecursiveLoop getEntity(String sKey) {
		RecursiveLoop value = null;

		try {
			value = (RecursiveLoop) this.get(sKey);

			if (value == null) {
				value = new RecursiveLoop();
			}
		} catch (Exception e) {
			value = new RecursiveLoop();
		}

		return value;
	}

	public HashMap getHashMap(String sKey) {
		HashMap value = null;

		try {
			value = (HashMap) this.get(sKey);

			if (value == null) {
				value = new HashMap();
			}
		} catch (Exception e) {
			value = new HashMap();
		}

		return value;
	}

	public void remove(String sKey) {
		this.remove(sKey.toUpperCase());
	}

	public String getKey(String sValue) {
		String sResult = null;

		Set keySet = this.entrySet();
		Object[] lists = keySet.toArray();

		String sKey = null;
		Object value = null;

		for (int i = 0; i < lists.length; i++) {
			sKey = (String) (((Map.Entry) lists[i]).getKey());
			value = this.get(sKey);

			if (value instanceof String && ((String) value).trim().equals(sValue)) {
				sResult = sKey;
				break;
			}
		}
		return sResult;
	}

	public String getKey(String sValue, String sKeyPrefix) {
		sKeyPrefix = sKeyPrefix.toUpperCase();

		String sResult = null;

		Set keySet = this.entrySet();
		Object[] lists = keySet.toArray();

		String sKey = null;
		Object value = null;

		for (int i = 0; i < lists.length; i++) {
			sKey = (String) (((Map.Entry) lists[i]).getKey());
			value = this.get(sKey);

			if (sKey.startsWith(sKeyPrefix) && value instanceof String && ((String) value).trim().equals(sValue)) {
				sResult = sKey;

				break;
			}
		}

		return sResult;
	}
}
