package kakaopay.investing.common.code;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeMap {

	public static <T extends Enum<T>> Map<String, String> getCodeMap(Class<T> codes) {
		
		Map<String, String> codeMap = new HashMap<String, String>();
		
        Method m;
		try {
			
			m = codes.getMethod("values", null);
			Object obj = m.invoke(null, null);
			
			for (Enum<T> enumval : (Enum<T>[]) obj) {
				codeMap.put(enumval.name(), enumval.toString());
			}
			
		} catch (Exception e) {
			log.error("코드맵생성오류:{}", e.fillInStackTrace());
		}
		
		return codeMap;
	}
}
