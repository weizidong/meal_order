package com.hbin.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * String操作工具类
 */
public class StringUtil {

	private static final Logger log = LoggerFactory.getLogger(StringUtil.class);

	// 保留1位小数
	private static DecimalFormat df1  = new DecimalFormat("##.0");
	
	// 保留2位小数
	private static DecimalFormat df2  = new DecimalFormat("##.00");
	
	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 *            待判断对象
	 * @return Boolean true-空(null/空字符串/纯空白字符),false-非空
	 */
	public static final Boolean isBlank(Object obj) {
		return obj == null || obj.toString().trim().length() == 0;
	}

	/**
	 * 判断字符串是否包含内容(与isBlank方法相反)
	 * 
	 * @param obj
	 *            待判断对象
	 * @return Boolean true-有内容,false-空(null/空字符串/纯空白字符)
	 */
	public static final Boolean isNotBlank(Object obj) {
		return !isBlank(obj);
	}

	/**
	 * 判断字符串是否包含内容(与isBlank方法相反)
	 * 
	 * @param obj
	 *            待判断对象
	 * @return Boolean true-有内容,false-空(null/空字符串/纯空白字符)
	 */
	public static final Boolean hasText(Object obj) {
		return !isBlank(obj);
	}

	/**
	 * 转换为字符串
	 * 
	 * @param obj
	 *            待转换对象
	 * @return String obj为空时返回空字符串
	 */
	public static final String toString(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	/**
	 * 返回一个对象的字符串,多数是处理字符串的.
	 * 
	 * @param obj
	 *            待处理对象
	 * @return 字符串
	 */
	public static final String trim(Object obj) {
		return obj == null ? "" : obj.toString().trim();
	}

	/**
	 * 对比两个对象是否相同
	 * 
	 * <pre>
	 * 当obj1==null或obj2==null时,返回false;
	 * 否则返回 obj1.equals(obj2)
	 * </pre>
	 * 
	 * @param obj1
	 *            对象1
	 * @param obj2
	 *            对象2
	 * @return Boolean
	 */
	public static final Boolean equals(Object obj1, Object obj2) {
		Boolean equals = false;

		if (obj1 != null && obj2 != null) {
			equals = obj1.equals(obj2);
		}

		return equals;
	}

	/**
	 * 对比两个字符串是否相同(忽略大小写)
	 * 
	 * <pre>
	 * 当obj1==null或obj2==null时,返回false;
	 * 否则返回 obj1.toString().equalsIgnoreCase(obj2.toString())
	 * </pre>
	 * 
	 * @param obj1
	 *            对象1
	 * @param obj2
	 *            对象2
	 * @return Boolean
	 */
	public static final Boolean equalsIgnoreCase(Object obj1, Object obj2) {
		Boolean equals = false;

		if (obj1 != null && obj2 != null) {
			equals = obj1.toString().equalsIgnoreCase(obj2.toString());
		}

		return equals;
	}

	/**
	 * 转换为整型(Integer)
	 * 
	 * @param obj
	 *            待转换对象
	 * @param defaultVal
	 *            默认值(无法转换时返回该值)
	 * @return Integer
	 */
	public static final Integer toInteger(Object obj, Integer defaultVal) {
		try {
			if(obj==null){
				return defaultVal;
			}
			String value = new java.text.DecimalFormat("0").format(obj);
			return Integer.parseInt(value);
		} catch (Exception e) {
			try {
				return Integer.parseInt(obj.toString());
			} catch (Exception ex) {
				log.error("Parse Integer Error : {}", ex.toString());
				return defaultVal;
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(StringUtil.toInteger(12.77));
		System.out.println(formatDecimal(13.655111111113, 2));
	}

	/**
	 * 转换为整型(Integer)
	 * 
	 * @param obj
	 *            待转换对象
	 * @return Integer
	 */
	public static final Integer toInteger(Object obj) {
		try {
			if(obj==null){
				return 0;
			}
			String value = new java.text.DecimalFormat("0").format(obj);
			return Integer.parseInt(value);
		} catch (Exception e) {
			try {
				return Integer.parseInt(obj.toString());
			} catch (Exception ex) {
				log.error("Parse Integer Error : {}", ex.toString());
				return null;
			}
		}
	}

	/**
	 * 转换为长整型(Long)
	 * 
	 * @param obj
	 *            待转换对象
	 * @param defaultVal
	 *            默认值(无法转换时返回该值)
	 * @return Long
	 */
	public static final Long toLong(Object obj, Long defaultVal) {
		try {
			return Long.parseLong(obj.toString());
		} catch (Exception e) {
			log.info("Parse Long Error : {}", e.toString());
			return defaultVal;
		}
	}

	/**
	 * 转换为长整型(Long)
	 * 
	 * @param obj
	 *            待转换对象
	 * @return Long
	 */
	public static final Long toLong(Object obj) {
		try {
			return Long.parseLong(obj.toString());
		} catch (Exception e) {
			log.error("Parse Long Error : {}", e.toString());
			return null;
		}
	}

	/**
	 * 转换为浮点数(Float)
	 * 
	 * @param obj
	 *            待转换对象
	 * @param defaultVal
	 *            默认值(无法转换时返回该值)
	 * @return Float
	 */
	public static final Float toFloat(Object obj, Float defaultVal) {
		try {
			return Float.parseFloat(obj.toString());
		} catch (Exception e) {
			log.info("Parse Float Error : {}", e.toString());
			return defaultVal;
		}
	}

	/**
	 * 转换为浮点数(Float)
	 * 
	 * @param obj
	 *            待转换对象
	 * @param defaultVal
	 *            默认值(无法转换时返回该值)
	 * @return Float
	 */
	public static final Float toFloat(Object obj) {
		try {
			return Float.parseFloat(obj.toString());
		} catch (Exception e) {
			log.error("Parse Float Error : {}", e.toString());
			return null;
		}
	}

	/**
	 * 转换为双精度浮点数(Double)
	 * 
	 * @param obj
	 *            待转换对象
	 * @param defaultVal
	 *            默认值(无法转换时返回该值)
	 * @return Double
	 */
	public static final Double toDouble(Object obj, Double defaultVal) {
		try {
			return Double.parseDouble(obj.toString());
		} catch (Exception e) {
			log.info("Parse Double Error : {}", e.toString());
			return defaultVal;
		}
	}

	/**
	 * 转换为双精度浮点数(Double)
	 * 
	 * @param obj
	 *            待转换对象
	 * @return Double
	 */
	public static final Double toDouble(Object obj) {
		try {
			return Double.parseDouble(obj.toString());
		} catch (Exception e) {
			log.error("Parse Double Error : {}", e.toString());
			return null;
		}
	}

	public static final List<String> toList(Object obj, String separtor) {
		List<String> list = new ArrayList<String>();
		String[] temp = trim(obj).split(separtor);
		for (String str : temp) {
			if (hasText(str)) {
				list.add(str);
			}
		}
		return list;
	}

	public static final List<String> toList(Object obj) {
		return toList(obj, ",");
	}

	public static final String[] toArray(Object obj, String separtor) {
		return toList(obj, separtor).toArray(new String[0]);
	}

	public static final String[] toArray(Object obj) {
		return toList(obj, ",").toArray(new String[0]);
	}

	/**
	 * 将字符串转换为Integer集合
	 * 
	 * @param obj
	 *            待转换字符串
	 * @param separtor
	 *            分隔符
	 * @return List<Integer>
	 */
	public static final List<Integer> toIntList(Object obj, String separtor) {
		List<Integer> list = new ArrayList<Integer>();
		String[] temp = trim(obj).split(separtor);
		for (String str : temp) {
			Integer i = toInteger(str);
			if (i != null) {
				list.add(i);
			}
		}
		return list;
	}

	/**
	 * 将字符串转换为Integer集合(分隔符:",")
	 * 
	 * @param obj
	 *            待转换字符串
	 * @return List<Integer>
	 */
	public static final List<Integer> toIntList(Object obj) {
		return toIntList(obj, ",");
	}

	/**
	 * 将字符串转换为Integer数组
	 * 
	 * @param obj
	 *            待转换字符串
	 * @param separtor
	 *            分隔符
	 * @return Integer[]
	 */
	public static final Integer[] toIntArray(Object obj, String separtor) {
		return toIntList(obj, separtor).toArray(new Integer[0]);
	}

	/**
	 * 将字符串转换为Integer数组(分隔符:",")
	 * 
	 * @param obj
	 *            待转换字符串
	 * @return Integer[]
	 */
	public static final Integer[] toIntArray(Object obj) {
		return toIntList(obj, ",").toArray(new Integer[0]);
	}

	/**
	 * 将字符串转换为Long集合
	 * 
	 * @param obj
	 *            待转换字符串
	 * @param separtor
	 *            分隔符
	 * @return List<Long>
	 */
	public static final List<Long> toLongList(Object obj, String separtor) {
		List<Long> list = new ArrayList<Long>();
		String[] temp = trim(obj).split(separtor);
		for (String str : temp) {
			Long l = toLong(str);
			if (l != null) {
				list.add(l);
			}
		}
		return list;
	}

	/**
	 * 将字符串转换为Long集合(分隔符:",")
	 * 
	 * @param obj
	 *            待转换字符串
	 * @return List<Long>
	 */
	public static final List<Long> toLongList(Object obj) {
		return toLongList(obj, ",");
	}

	/**
	 * 将字符串转换为Long数组
	 * 
	 * @param obj
	 *            待转换字符串
	 * @param separtor
	 *            分隔符
	 * @return Long[]
	 */
	public static final Long[] toLongArray(Object obj, String separtor) {
		return toLongList(obj, separtor).toArray(new Long[0]);
	}

	/**
	 * 将字符串转换为Long数组(分隔符:",")
	 * 
	 * @param obj
	 *            待转换字符串
	 * @return Long[]
	 */
	public static final Long[] toLongArray(Object obj) {
		return toLongList(obj, ",").toArray(new Long[0]);
	}

	/**
	 * 使Double类型保留两位小数;
	 */
	public static final String getStringFordouble(Double d) {
		DecimalFormat df = new DecimalFormat(".##");
		String st = df.format(d);
		return st;
	}

	/**
	 * 统计字符串中某个字符出现的次数
	 * 
	 * @param val
	 *            字符串
	 * @param ch
	 *            被统计的字符
	 * @return
	 */
	public static final Integer countChar(String val, String ch) {
		if (isBlank(val)) {
			return 0;
		}
		Integer total = val.length();
		String temp = val.replaceAll(ch, "");
		return total - temp.length();
	}

	// /////////正则表达式验证///////////

	/**
	 * 邮箱格式验证
	 * 
	 * @param obj
	 *            邮箱
	 * @return Boolean
	 */
	public static final Boolean isEmail(Object obj) {
		String regex = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
		return obj == null ? false : Pattern.matches(regex, obj.toString());
	}

	/**
	 * 血压格式验证，如110/99/68
	 * 
	 * @param obj
	 *            血压记录字符串
	 * @return Boolean
	 */
	public static final Boolean isBPRecord(Object obj) {
		String regex = "^(\\d{2,3}/)+(\\d{2,3}/)+(\\d{2,3})$";
		return obj == null ? false : Pattern.matches(regex, obj.toString());
	}

	/**
	 * 验证是否为纯数字
	 * 
	 * @param obj
	 * @return
	 */
	public static final Boolean isNumber(Object obj) {
		return obj == null ? false : Pattern.matches("^*\\d*$", obj.toString());
	}
	
	/**
	 * 四舍五入，保留N位小数
	 * 
	 * @param obj
	 * @return
	 */
	public static final Double formatDecimal(Double val, Integer num) {
		if (val == null)
			return toDouble(0);
		
		if (1 == num)
			return toDouble(df1.format(val));
			
		if (2 == num) 
			return toDouble(df2.format(val));

		return toDouble(df1.format(val));
	}
	
	/**
	 * 四舍五入，保留N位小数
	 * 
	 * @param obj
	 * @return
	 */
	public static final Float formatDecimal(Float val, Integer num) {
		if (val == null)
			return toFloat(0);
		
		if (1 == num)
			return toFloat(df1.format(val));
			
		if (2 == num) 
			return toFloat(df2.format(val));

		return toFloat(df1.format(val));
	}

	/**
	 * //截取数字
	 * 
	 * @param String
	 * @return Integer
	 */
	public static Integer getNumbers(String content) {
		if (content == null || content.equals(""))
			return 0;
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {

			return Integer.parseInt(matcher.group(0));

		}
		return 0;
	}

	/**
	 * 将inputStream转换成字符串
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static String inputStream2String(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (Integer n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n, "utf-8"));
		}
		return out.toString();
	}
	
	/**
	 * 
	 * @param buf
	 * @return
	 */
	public static final InputStream byte2Input(byte[] buf) {  
        return new ByteArrayInputStream(buf);  
    }  
  
	/**
	 * 
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
    public static final byte[] input2byte(InputStream inStream)  
            throws IOException {  
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
        byte[] buff = new byte[100];  
        int rc = 0;  
        while ((rc = inStream.read(buff, 0, 100)) > 0) {  
            swapStream.write(buff, 0, rc);  
        }  
        byte[] in2b = swapStream.toByteArray();  
        return in2b;  
    }
	

}
