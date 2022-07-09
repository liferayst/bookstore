package com.tt.bookstore.utility;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DataValidator {

	private static Map<String, String> patterns;

	static {
		patterns = new HashMap<String, String>();
		patterns.put("HTTPParameterName", "^[a-zA-Z0-9_\\[\\]\\.]{1,70}$");
		patterns.put("HTTPParameterName_1", "^[a-zA-Z0-9_]{1,70}$");
		patterns.put("HTTPParameterValue", "^[a-zA-Z0-9.\\-\\/+=_ ]*$");
		patterns.put("HTTPCookieName", "^[a-zA-Z0-9\\-_]{1,32}$");
		patterns.put("HTTPCookieValue", "^[a-zA-Z0-9\\-\\/+=_. ]*$");
		patterns.put("HTTPHeaderName", "^[a-zA-Z0-9\\-_]{1,32}$");
		patterns.put("HTTPHeaderValue", "^[a-zA-Z0-9()\\-=\\*\\.\\?;,+\\/:&_ ]*$");
		patterns.put("unsafeText", "(\\s|\\S)*(--)(\\s|\\S)*");
		patterns.put("SQLInjection",
				"(\\s|\\S)*((%27)|(')|(%2B)|(%3D)|(=)|(%2F)|(\")|((%22)|(-|%2D){2})|(%23)|(%3B))+(\\s|\\S)*");
		patterns.put("execCommand", "(\\s|\\S)*(exec(\\s|\\+)+(s|x)p\\w+)(\\s|\\S)*");
		patterns.put("imageXSS",
				"(\\s|\\S)*((%3C)|<)((%69)|i|I|(%49))((%6D)|m|M|(%4D))((%67)|g|G|(%47))[^\\n]+((%3E)|>)(\\s|\\S)*");
		patterns.put("scriptXSS",
				"(\\s|\\S)*(%3C|<)(\\s)*((%73)|s|S)(\\s)*((%63)|c|C)(\\s)*((%72)|r|R)(\\s)*((%69)|i|I)(\\s)*((%70)|p|P)(\\s)*((%74)|t|T)(\\s|\\S)*");
		patterns.put("objectXSS",
				"(\\s|\\S)*(<|(%3C))(\\s)*((%6F)|o|O)(\\s)*((%62)|b|B)(\\s)*((%6A)|j|J)(\\s)*((%65)|e|E)(\\s)*((%63)|c|C)(\\s)*((%74)|t|T)(\\s|\\S)*");
		patterns.put("embedXSS",
				"(\\s|\\S)*(<|(%3C))(\\s)*((%65)|e|E)(\\s)*((%6D)|m|M)(\\s)*((%62)|b|B)(\\s)*((%65)|e|E)(\\s)*((%64)|d|D)(\\s|\\S)*");
		patterns.put("alertXSS",
				"(\\s|\\S)*(A|a|(%61))(\\s)*((%6C)|l|L)(\\s)*((%65)|e|E)(\\s)*((%72)|r|R)(\\s)*((%74)|t|T)(\\s)*((%28)|\\()(\\s|\\S)*");
		patterns.put("evalXSS",
				"(\\s|\\S)*((%65)|e|E)(\\s)*((%76)|v|V)(\\s)*((%61)|a|A)(\\s)*((%6C)|l|L)(\\s)*((%29)|\\()(\\s|\\S)*");
	}

	private int checkVulnerability(String value) {
		int counter = 0;
		if (counter == 0 && value.matches(patterns.get("unsafeText"))) {
			counter++;
		}
		if (counter == 0 && value.matches(patterns.get("SQLInjection"))) {
			counter++;
		}
		if (counter == 0 && value.matches(patterns.get("execCommand"))) {
			counter++;
		}
		if (counter == 0 && value.matches(patterns.get("imageXSS"))) {
			counter++;
		}
		if (counter == 0 && value.matches(patterns.get("scriptXSS"))) {
			counter++;
		}
		if (counter == 0 && value.matches(patterns.get("evalXSS"))) {
			counter++;
		}
		/* Below is for RTF */
		if (counter == 0 && value.matches(patterns.get("objectXSS"))) {
			counter++;
		}
		if (counter == 0 && value.matches(patterns.get("embedXSS"))) {
			counter++;
		}
		if (counter == 0 && value.matches(patterns.get("alertXSS"))) {
			counter++;
		}
		return counter;
	}

	public boolean validateData(Object data) throws Exception {
		int vcount = 0;
		if (data != null) {
			Class<?> objClass = data.getClass();
			Field[] fields = objClass.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.getType().getPackage() != null && (field.getType().getPackage().getName().equals("com.tt.bookstore.dto"))) {
					if (!validateData(field.get(data))) {
						vcount++;
					}
				}
				Object value = field.get(data);
				if (vcount == 0 && value != null && StringUtils.hasLength(value.toString())) {
					if (value.toString().length() > 1500) {
						vcount = vcount + checkVulnerability(value.toString().substring(0, 1500));
					} else {
						vcount = vcount + checkVulnerability(value.toString());
					}
				}
			}
		}
		return vcount == 0;
	}

	public boolean validateField(Object value) throws Exception {
		if (value != null && StringUtils.hasLength(value.toString())) {
			return checkVulnerability(value.toString()) == 0;
		}
		return true;
	}
}
