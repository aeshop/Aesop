package teamSemiProject2.edu.kh.semi.common;

public class XSS {

	//크로스사이트 스크립트 공격 방지 위한 메소드
	/**
	 * @param parameter: 문자열
	 * @return 문자열을 크로스사이트 스크립트 공격 안당하게 바꿈
	 */
	public static String replaceParameter(String parameter) {
		
		
		if(parameter!=null) {
			parameter=parameter.replaceAll("&", "&amp;");
			parameter=parameter.replaceAll("<", "&lt;");
			parameter=parameter.replaceAll(">", "&gt;");
			parameter=parameter.replaceAll("\"", "&quot;");
		}
		
		return parameter;
	}
	
}
