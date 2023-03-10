import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {
	public static void main(String[] args) {
		
		try {
			// 1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "jspid";
			String pw = "jsppw";
			// 2. 계정 접속
			Connection conn = DriverManager.getConnection(url, id, pw);
			System.out.println("계정 접속 성공");
			
			// 3. SQL문 분석
			int inputNum = 3;
			String inputName = "choi";
			String inputAddr = "제주";
			
			//String sql = "insert into test values(4,'park','경기')";
			//String sql = "insert into test values(" + inputNum + ",'" + inputName + "','" + inputAddr + "')";
			String sql = "insert into test(num,name,addr) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//3.1 ?(위치홀더)에 값 채우기 4번이랑 순서바꾸면안됨
			ps.setInt(1, inputNum); // 1번 물음표 자리에 숫자를 셋팅
			ps.setString(2, inputName); // 2번 물음표 자리에 문자열을 셋팅
			ps.setString(3, inputAddr); // 3번 물음표 자리에 문자열을 셋팅
			
			// 4. SQL문 실행
			int cnt = ps.executeUpdate(); 
			// 만약 여기서 문제가 생겼다면 , sql 을 실행하는 문장이니 위에 29번째줄에서 에러가 생겼을 가능성이 높다
			
			// 성공적으로 insert 한 개수를 리턴한다.
			System.out.println("cnt:" + cnt);
			
			if(cnt>0)
				System.out.println("insert 성공");
			else
				System.out.println("insert 실패");
			
			// 5. 접속 해제
			conn.close();
			System.out.println("연결 해제");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 계정 접속/종료 실패");
		}
		
		
		
		
	}
}
