package MiniProject1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;




public class Client extends JFrame{
//	   static Admin_MemberCheck admin_memberCheck;
	   static CarList carList;
	   static MemberList memberList;
	   static Admin_MemberCheck memberCheck;
	   static Buy_Coupon buycoupon;
	   static Welcome welcome;
	   static Member_Join mjoin;
	   static FindCar findcar;
	   static MemberCouponList cList;
	   static First first;
	   static Admin_Login admin;
	   static Member_Login member;
	   static Visit_Input visit;
	   static Result result;
	   
	   static ObjectInputStream ois = null;
	   static ObjectOutputStream oos = null;
//	   static PrintWriter out = null;
//	   static BufferedReader in = null;
	   
	   static String loginId = null;
	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("Main");
	      
//		  admin_memberCheck = new Admin_MemberCheck();
		  carList = new CarList();
		  memberList = new MemberList();
		  memberCheck = new Admin_MemberCheck();
		  buycoupon = new Buy_Coupon();
		  welcome = new Welcome();
		  mjoin = new Member_Join();
		  findcar = new FindCar();
		  cList = new MemberCouponList();
		  result = new Result();
	      first = new First();
	      admin = new Admin_Login();
	      member = new Member_Login();
	      visit = new Visit_Input();
	      
	      
//	      admin_memberCheck.setVisible(false);
	      carList.setVisible(false);
	      memberList.setVisible(false);
	      memberCheck.setVisible(false);
          first.setVisible(true);
	      buycoupon.setVisible(false);
	      welcome.setVisible(false);
	      mjoin.setVisible(false);
	      findcar.setVisible(false);
	      cList.setVisible(false);
	      result.setVisible(false);
	      admin.setVisible(false);
	      member.setVisible(false);
	      visit.setVisible(false);
	   //------여기까지 메인스윙창-------
		
		
		Socket socket = null;
		try{
			socket = new Socket("127.0.0.1", 9500);   // 또는 "localhost"
//			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"), true);
//			in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
		}catch(UnknownHostException e){
//			System.err.println("localhost에 접근할 수 없습니다.");
			e.printStackTrace();
			System.exit(-1);
		}catch(IOException e){
			e.printStackTrace();
//			System.err.println("입출력 오류11");
			System.exit(-1);
		}

		Scanner sc = new Scanner(System.in);
		Parking parking;
		Member member;
		Login login;
		String message;  // 서버로부터의 문자열.
		ArrayList<Member> list = null;
		while(true){
//			if((message=in.readLine())==null) break;
//			message=(String)(ois.readObject());
//			
//			System.out.println("서버 : "+message);

			System.out.print("입력 : ");
			message = sc.nextLine();
			if(message.equals("끝"))
				break;

			//out.println(message);
			oos.writeObject(message);
			if(message.equals("회원정보확인")) {
				String msg = (String)(ois.readObject());
				System.out.println("서버로부터:"+msg);
				
//				member = (Member)(ois.readObject());
//				System.out.println("서버로부터 받은 회원정보");
//				System.out.println("회원:"+member);
				list = (ArrayList<Member>)(ois.readObject());
				String result = (String)(ois.readObject());
				System.out.println("서버로부터 받은 회원정보 테스트 출력 : ");
				for(Member m : list) {
					System.out.println(m);
				}
			}
//			} else if(message.equals("회원가입")) {
//				System.out.print("이름:");
//				String name=sc.nextLine();
//				System.out.print("아이디:");
//				String id = sc.nextLine();
//				System.out.print("패스워드:");
//				String pw = sc.nextLine();
//				System.out.print("차량번호:");
//				String carNum = sc.nextLine();
//				System.out.print("동호수:");
//				String address = sc.nextLine();
////				list.add(new Member(name,id,pw,carNum,address));
//				//oos.writeObject(new Member(name,id,pw,carNum,address));
//				//out.println("가입완료.");	
//				
//				 int oneHour=20,twoHour=15,threeHour=10,allDay=1;
//		            System.out.println("할인권 - 1시간:"+oneHour+"개"+"/2시간:"+twoHour+"개"+"/3시간:"+threeHour+"개"+"/종일권:"+allDay+"개");
////		            list.add(new Member(name,id,pw,carNum,address));
//		            //oos.reset();
//		            oos.writeObject(new Member(name,id,pw,carNum,address,oneHour,twoHour,threeHour,allDay));
//		            out.println("가입완료.");   
			
//			else if(message.equals("로그인")) {
//				do {		
//					System.out.println("아이디:");
//					String id = sc.nextLine();
//					System.out.println("패스워드:");
//					String pw = sc.nextLine();
//					oos.writeObject(new Login(id,pw));
//				}while(in.readLine().equals("로그인 실패"));
//				out.println(".");
//				
//	           
//	         }

			
//			else if(message.equals("입차")) {
//				System.out.println("차량번호:");
//				String car = sc.nextLine();
//				oos.writeObject(new Parking(car));
//			}
//			else if(message.equals("출차")) {
//				System.out.println("차량번호:");
//				String carNum = sc.nextLine();
//				out.println(carNum);
//			}
		}
		System.out.println("프로그램 종료.");
		
//		out.close();
//		in.close();
		sc.close();
		socket.close();
	}

}