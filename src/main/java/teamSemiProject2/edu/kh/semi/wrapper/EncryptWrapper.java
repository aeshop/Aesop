package teamSemiProject2.edu.kh.semi.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author nyr 웹서블릿은 사용하지 않는다: 상속되는 부모 말대로 http req를 포장하는 클래스: 여기서 getParameter의
 *         오버라이딩을 수행한다
 */
public class EncryptWrapper extends HttpServletRequestWrapper {

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String param) {
		String value = null;

		switch (param) {
		 case "pw": case "pwd1": 
			value = getSha512(super.getParameter(param));
			break;
		default:
			value = super.getParameter(param);
			break;

		}

		return value;
	}

	/**
	 * SHA-512 해쉬 함수를 이용하여 문자열 암호화를 진행하는 메소드
	 * 
	 * @param pwd
	 * @return encPwd
	 */
	private String getSha512(String pwd) {

		// 1. 암호화된 비밀번호를 저장할 변수 선언
		String encPwd = null;

		// 2. 해쉬 함수를 수행하는 객체를 저장할 변수 선언
		// 해쉬 함수 : 특정 값을 여러 단계의 연산을 거쳐 일정 길이의 무작위 값을 얻어내는 함수 MessageDigest: 암호화 객체
		MessageDigest md = null;

		try {
			// 3. SHA-512 방식의 해쉬함수를 수행할 수 있는 객체를 얻어옴
			md = MessageDigest.getInstance("SHA-512");// MessageDigest: 암호화 객체는 SHA-512방식으로 할것인데,

			// 4. md를 이용해 문자열 암호를 하기 위해 byte 배열로 변환 //문자열은 byte배열로 변환을 해야 한다: 근데 그 문자열은
			// UTF-8 형식이었다.
			byte[] bytes = pwd.getBytes(Charset.forName("UTF-8"));

			// 5. md 객체에 바이트로 변환된 비밀번호를 전달하여 암호화를 수행 // update = 수정: 암호화를 진행했음
			md.update(bytes);

			// 6. md 객체에서 암호화된 내용을 꺼내옴
			// Base64 : 바이트 코드를 문자열로 바꾸는 라이브러리
			encPwd = Base64.getEncoder().encodeToString(md.digest()); // md.digest() 값은 byte값인데 다시 String으로 바꾼것을 encPwd에
																		// 담는다
			// md.digest() : 암호화된 코드를 꺼내옴

			System.out.println("암호화 전 : " + pwd);
			System.out.println("암호화 후 : " + encPwd);
			// 원래 여기있으면 안됨 ㅋㅋ
		} catch (NoSuchAlgorithmException e) {
			// SHA-512 해쉬함수가 없는 경우 발생
			e.printStackTrace();
		}

		return encPwd;
	}

}
