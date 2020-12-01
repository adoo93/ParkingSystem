package MiniProject1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


class Member implements Serializable {
	public static final Member showMember = null;
	String name;
	String id;
	String pw;
	String carNum;
	String address;
	int oneHour;
    int twoHour;
    int threeHour;
    int allDay;

	
	Member(String name,String id,String pw,String carNum,String address){
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.carNum = carNum;
		this.address = address;
	}
	 public Member(String name,String id,String pw,String carNum,String address,int oneHour, int twoHour,
	         int threeHour, int allDay) {
	      this.name = name;
	      this.id = id;
	      this.pw = pw;
	      this.carNum = carNum;
	      this.address = address;
	      this.oneHour = oneHour;
	      this.twoHour = twoHour;
	      this.threeHour = threeHour;
	      this.allDay = allDay;
	   }

	 public String toString() {
	      return "--회원정보-- / 이름:"+name+"/ 아이디:"+id+
	            "/ 비밀번호:"+pw+"/ 차량번호"+carNum+"/ 동호수:"+address+"/ 1시간 권:"+oneHour+"개/ 2시간 권:"+twoHour+"개/ 3시간권:"+threeHour+
	                                       "개/ 종일권:"+allDay+"개";
	   }

}
class Login implements Serializable {
	String id;
	String pw;
	
	Login(String id, String pw){
		this.id = id;
		this.pw = pw;
	}
}
class Parking implements Serializable {
	String carNum;
	long parkingStartTime;
	
	Parking(String carNum){
		this.carNum = carNum;
		parkingStartTime = System.currentTimeMillis();
	}
	public String toString() {
		return carNum + " : " + parkingStartTime;
	}
}
class Admin implements Serializable {
	String id;
	String pw;
	
	Admin(String id, String pw){
		this.id = id;
		this.pw = pw;
	}
}
public class Server {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String loginId = null;
		
//		HashMap<String, String> hMap  = new HashMap<String, String>();
//	    hMap.put("admin",  "1234");
		ArrayList<Member> memberList = null;
		ArrayList<Parking> parkingList = new ArrayList<Parking>();
		ObjectOutputStream oosFile = null;
//		 더미(dummy)데이터 저장.
		oosFile=new ObjectOutputStream(new FileOutputStream("memberObject.dat"));
		memberList = new ArrayList<Member>();
		memberList.add(new Member("이름1","1","12","01가1234","서울"));
		memberList.add(new Member("이름2","아이디2","ㅂㅂ","01가1234","서울"));
		memberList.add(new Member("이름3","아이디3","ㅂㅂ","01가1234","서울"));
		oosFile.writeObject(memberList);
		System.out.println("저장완료!!!");
		oosFile.close();
		
		ObjectInputStream oisFile = new ObjectInputStream(new FileInputStream("memberObject.dat"));
		memberList = (ArrayList<Member>)(oisFile.readObject());
		oisFile.close();
		try {
			oisFile = new ObjectInputStream(new FileInputStream("carList.dat"));
			parkingList = (ArrayList<Parking>)(oisFile.readObject());
			oisFile.close();
		} catch(FileNotFoundException e) {
			parkingList = new ArrayList<Parking>();
		}
		
		System.out.println("서버시작시 parkingLIst조회:");
		for(Parking p : parkingList) {
			System.out.println(p.carNum + " / " + p.parkingStartTime);
		}
		
		int port = 9500;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			//System.out.println("다음의 포트 번호에 연결할 수 없습니다 : 9500");
			e.printStackTrace();
			System.exit(-1);
		}

		Socket clientSocket = null;
		try {
			System.out.println("클라이언트 접속을 기다림.");
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
//			System.err.println("accept() 실패 ");
			e.printStackTrace();
			System.exit(-1);
		}
		
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(),"utf-8"), true);
//		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "utf-8"));
		ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
		
		
		String message;
//		outputLine = "Welcome";
//		out.println(outputLine);
//out.println("접속되었습니다. (포트번호 : " + port + ")");
		System.out.println("서버가 시작되었습니다.");
//		while ((message = in.readLine()) != null) {
		while ((message = (String)(ois.readObject())) != null) {
			System.out.println("클라이언트로부터 전송된 메시지 : " + message);
			
			String adminId = "admin";
			String adminPw = "1234";
			
			if(message.equals("회원가입")) {

				Member newMember = (Member)(ois.readObject());
				
				memberList.add(newMember);
				
				oosFile = new ObjectOutputStream(new FileOutputStream("memberObject.dat"));
				oosFile.writeObject(memberList);
				System.out.println("저장완료!!!");
				oosFile.close();
				oos.writeObject("가입완료");
				System.out.println("회원가입정보");
				for(Member m : memberList) {
					System.out.println(m);
				}
			} 
			else if(message.equals("회원정보확인")) {
//				out.println("가입된 회원 정보");			
//				oos.reset();
				oos.writeObject(memberList);
				
				//out.println("회원정보 전송완료.");
//				oos.writeObject("회원정보 전송완료.");
				System.out.println("가입된 회원 정보 전송완료.");
			}
			else if(message.equals("방문차량정보확인")) {
				oos.writeObject(parkingList);
				System.out.println("방문차량 정보 전송 완료.");
			}
			else if(message.equals("로그인")) {
				Login login = (Login)(ois.readObject());
				boolean flag = false;  // 로그인 실패라고 가정. 
				for(Member m : memberList) {
					if(m.id.equals(login.id) && m.pw.equals(login.pw)) {
						flag = true;
					}
				}
				if(flag == true) {
//					out.println("로그인 성공");
					oos.writeObject("로그인 성공");
					//loginId = in.readLine();
					loginId = (String)(ois.readObject());
					
					String s1 = null, s2 = null;  // s1 = 이름, s2 = 동호수.
					int v3=0, v4=0, v5=0, v6 = 0;   // 쿠폰 수량. 
					s1 = loginId;
					for(int i=0; i<=memberList.size()-1; i++) {
						if(memberList.get(i).id.equals(loginId)) {
							Member m = memberList.get(i);
							s2 = m.address;
							v3 = m.oneHour;
							v4 = m.twoHour;
							v5 = m.threeHour;
							v6 = m.allDay;
							break;
						}
					}
					if(s2 != null)
						oos.writeObject(new Object[] {s1, s2, v3, v4, v5, v6});
				}
				else {
					//out.println("로그인 실패");
					oos.writeObject("로그인 실패");
				}
//				if(in.readLine().equals(".")) {
//					out.println("로그인 되었습니다.");
//				}
			}
			
			else if(message.equals("관리자 로그인")) {
				Admin admin = (Admin)(ois.readObject());
				boolean flag = false;
				if(admin.id.equals(adminId)&&admin.pw.equals(adminPw)){
			        flag = true;
			      } 
				if(flag==true) {
					//out.println("로그인 성공");
					oos.writeObject("로그인 성공");
				}
				else {
					//out.println("로그인 실패");
					oos.writeObject("로그인 실패");
					
				}
				
			}
			else if(message.equals("입차")) {
				Parking parking = (Parking)(ois.readObject());
				parkingList.add(parking);
				oosFile = new ObjectOutputStream(new FileOutputStream("carList.dat"));
				oosFile.writeObject(parkingList);
				System.out.println("방문차량 저장 완료.");
				oosFile.close();
				System.out.println("방문차량정보");
				for(Parking p : parkingList) {
					System.out.println(p);
				}
//				long start = System.currentTimeMillis();
//				parking.parkingStartTime = start;
				Calendar cal = Calendar.getInstance();
				int hour = cal.get(Calendar.HOUR_OF_DAY);
				int min = cal.get(Calendar.MINUTE);
				int sec = cal.get(Calendar.SECOND);
				//out.println("방문객 차량("+parking.carNum+") 입차완료. 입차시간 : "+hour+"시"+min+"분"+sec+"초.");
				oos.writeObject("방문객 차량("+parking.carNum+") 입차완료. 입차시간 : "+hour+"시"+min+"분"+sec+"초.");
			}
			else if(message.equals("주차시간확인")) {
				System.out.println("주차시간확인중::: 로그인아이디="+loginId);
				//String carNum = in.readLine(); 
				String carNum = (String)(ois.readObject()); 
				
				long end = System.currentTimeMillis();
				long totalTime = 0;
				for(Parking p : parkingList) {
					if(p.carNum.equals(carNum)) {
						totalTime = (long)(end-p.parkingStartTime);
					}
				}
//				out.println("총 주차시간은 "+(totalTime/1000)+"초 입니다.");
				oos.writeObject(new Integer((int)(totalTime/1000)));
			}
			// ---------------------------------------------------------
			else if(message.equals("출차")) {
				String carNum = (String)(ois.readObject());

				for(int i=0;i<parkingList.size();i++) {

					if(parkingList.get(i).carNum.equals(carNum)) {
						parkingList.remove(i);
						//파일업로드.
						oosFile = new ObjectOutputStream(new FileOutputStream("carList.dat"));
						oosFile.writeObject(parkingList);
						System.out.println("방문차량 저장(업데이트) 완료.");
						oosFile.close();
						break;
					}

				}
				System.out.println(parkingList);
				oos.writeObject("출차성공");
			}
			else if(message.equals("쿠폰사용")) {
				String carNum = (String)(ois.readObject());
				Integer integer = (Integer)(ois.readObject());
				int coupon = integer;
//				if(coupon>=1 && coupon<=3) {
					long parkingStartedMillis = 0;
					for(int i=0; i<=parkingList.size()-1; i++) {
						System.out.println("조회중:"+parkingList.get(i).carNum + "/" + parkingList.get(i).parkingStartTime/1000+"초.");
						if( parkingList.get(i).carNum.equals(carNum) ) {
							Parking obj = parkingList.get(i);
							if(System.currentTimeMillis() < obj.parkingStartTime) {
								//out.println("차감실패");
								oos.writeObject("차감실패");
								System.out.println("차감이 실패함:현재까지 무료임.");
								break;
							}
							obj.parkingStartTime += coupon*60*60*1000;
							parkingList.set(i, obj);
							// 파일 업데이트
							oosFile = new ObjectOutputStream(new FileOutputStream("carList.dat"));
							oosFile.writeObject(parkingList);
							System.out.println("방문차량 저장(업데이트) 완료.");
							oosFile.close();

							// 쿠폰차감.
							for(int j=0; j<=memberList.size()-1; j++) {
								if( memberList.get(j).id.equals(loginId)) {
									System.out.println("테스트(1): " + memberList.get(j).oneHour + " , " + memberList.get(j).twoHour + " , " + memberList.get(j).threeHour);
									if(coupon==1) memberList.get(j).oneHour--;
									if(coupon==2) memberList.get(j).twoHour--;
									if(coupon==3) memberList.get(j).threeHour--;
									if(coupon==24) memberList.get(j).allDay--;
									System.out.println("테스트(2): " + memberList.get(j).oneHour + " , " + memberList.get(j).twoHour + " , " + memberList.get(j).threeHour+","+memberList.get(j));
									//out.println("차감성공");
									oos.writeObject("차감성공");
									System.out.println("차감이 성공함");

									// 파일 업데이트 
									oosFile = new ObjectOutputStream(new FileOutputStream("memberObject.dat"));
									oosFile.writeObject(memberList);
									System.out.println("저장(업데이트)완료!!!");
									oosFile.close();
									
									break;
								}
								if(j==memberList.size()-1) {
									//out.println("차감실패");
									oos.writeObject("차감실패");
									System.out.println("차감이 실패함");
								}
							}
							break;
						}
//					}
				}
				//out.println("쿠폰사용처리완료.");
			}
			
			else if(message.equals("쿠폰구매")) {
				int[] arr = (int[])(ois.readObject());
				int num1 = arr[0];
				int num2 = arr[1];
				int num3 = arr[2];
				int numAllDay = arr[3];
				
				for(int i=0; i<=memberList.size()-1; i++) {
					if(memberList.get(i).id.equals(loginId)) {
						Member m = memberList.get(i);
						m.oneHour += num1;
						m.twoHour += num2;
						m.threeHour += num3;
						m.allDay += numAllDay;
						memberList.set(i, m);
						// 파일 업데이트 
						oosFile = new ObjectOutputStream(new FileOutputStream("memberObject.dat"));
						oosFile.writeObject(memberList);
						System.out.println("저장(쿠폰업데이트)완료!!!");
						oosFile.close();
						
						//out.println("쿠폰구매성공");
						oos.writeObject("쿠폰구매성공");
						oos.writeObject(new int[] {m.oneHour, m.twoHour, m.threeHour, m.allDay});
						//break;
					}
				}
			}
//			else if(message.equals("쿠폰확인")) {  // 로그인 된 아이디가 가지고 있는 쿠폰 갯수를 
//				int oneHour = 0;				// 클라이언트에게 보내기 위한 코드.
//				int twoHour = 0;
//				int threeHour = 0;
//				int allDay = 0;
//				for(Member m : list) {				// 추가한 부분. 수정 필요. 
//					if(m.id.equals(LoginId)) {
//						oneHour = m.oneHour;
//						twoHour = m.twoHour;
//						threeHour = m.threeHour;
//						allDay = m.allDay;
//					}
//				}
//				out.println(oneHour);
//				out.println(twoHour);
//				out.println(threeHour);
//				out.println(allDay);
//				
//			}
			
			else {
				System.out.println("서버에서 느낌표(!)를 붙여 메시지를 보내려는 중..."+message);
				//out.println(message+"!");
				oos.writeObject(message+"!");
				
			}
			if (message.equals("끝"))
				break;
		}
		System.out.println("클라이언트가 나갔습니다. 프로그램 종료.");
		
//		out.close();
//		in.close();
		oos.close();
		ois.close();
//		oosFile.close();
		clientSocket.close();
		serverSocket.close();
	}
}