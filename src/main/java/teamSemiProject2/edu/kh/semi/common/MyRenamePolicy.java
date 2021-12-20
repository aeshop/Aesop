package teamSemiProject2.edu.kh.semi.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * @author nyr 파일을 서버 저장공간에 업로드하기 위해서 파일 이름을 바꿀때 이 방침을 따르라고 라이브러리에 interface가
 *         존재한다. 우리만의 방법은 있으나 니 맘에 안들테니, 니가 메소드를 커스텀해라 이거임
 * @return
 */
public class MyRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originalFile) {

		long currentTime = System.currentTimeMillis();

		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
		int ranNum = (int) (Math.random() * 100000); // 5자리 랜덤 숫자 생성

		String str = "_" + String.format("%05d", ranNum);
		// String.format : 문자열을 지정된 패턴의 형식으로 변경하는 메소드
		// %05d : 오른쪽 정렬된 십진 정수(d) 5자리(5)형태로 변경. 빈자리는 0으로 채움(0)

		// 파일명을 변경해도 확장자를 유지하기 위하여
		// 별도로 확장자 명 가져오기
		String name = originalFile.getName();
		String ext = null;

		int dot = name.lastIndexOf(".");//마지막 . 문자의 index

		if (dot != -1) {//dot이 존재하지 않아서 -1이 반환됬을때
			// dot 포함해서 확장자 추출 (ext)
			ext = name.substring(dot);//시작인덱스 dot 위치부터 끝 까지 : 확장자이다
		} else {
			// 확장자가 없는 경우
			ext = "";
		}

		String fileName = ft.format(new Date(currentTime)) + str + ext;
		File newFile = new File(originalFile.getParent(), fileName);//원래 파일의 부모 폴더에 fileName으로 새 파일을 만들고 이를 리턴한다는 의미의 구문이다

		return newFile;

	}

}
