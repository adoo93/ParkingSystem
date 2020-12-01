package MiniProject1;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;




public class Admin_Login extends JFrame {
	JLabel id, pw;
	JTextField tId;
	JPasswordField tPassword;
	JButton loginbtn;

	Admin_Login() throws IOException{
		setTitle("관리자창");
		id = new JLabel("ID");
		pw = new JLabel("PW");


		tId = new JTextField();//id입력창
		tPassword = new JPasswordField();//pw입력창
		loginbtn = new JButton("로그인");

		id.setBounds(40,30,100,30);
		tId.setBounds(110,30,100,30);
		pw.setBounds(40,80,100,30);
		tPassword.setBounds(110,80,100,30);
		loginbtn.setBounds(100,180,100,30);


		add(id);
		add(tId);
		add(pw);
		add(tPassword);
		add(loginbtn);
		setLayout(null);

		tId.setText("admin");
		tPassword.setText("1234");

		setSize(300,280);//350으로 수정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JLabel background;
	    ImageIcon img = new ImageIcon("C:\\Users\\user\\eclipse-workspace\\HelloWorld\\src\\관리자.png");
	    background = new JLabel("",img,JLabel.CENTER);
	    background.setBounds(0, 0, 300, 280);
	    add(background);

		loginbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tId.getText();
				String pw = String.valueOf(tPassword.getPassword());
				try {
					Client.oos.writeObject(new Admin(id,pw));//스윙텍스트에 입력한 id,pw 전송
					//               String result = Client.in.readLine();
					String result = (String)(Client.ois.readObject());//로그인성공 문자 읽어들임
					System.out.println("서버로부터:"+result);
					if(result.equals("로그인 실패")) {
						JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 일치하지 않습니다!");
						//                 Client.ois.readObject();
						Client.oos.writeObject("관리자 로그인"); // 
					}else /*(result.equals(" 로그인 성공"))*/ {
						Client.memberCheck.setVisible(true);
						//                  JOptionPane.showMessageDialog(null, "관리자님 로그인 되셨습니다!");
						//Client.out.println("회원정보확인");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {

					e1.printStackTrace();
				}
			}

		});

	}
}
class Admin_MemberCheck extends JFrame {
	JButton memberCheck, visitCarCheck;

	Admin_MemberCheck(){
		setTitle("관리자 창");
		memberCheck = new JButton("회원정보확인");
		visitCarCheck = new JButton("방문차량확인");

		memberCheck.setBounds(70,80,180,30);
		visitCarCheck.setBounds(300,80,180,30);

		add(memberCheck);
		add(visitCarCheck);

		setLayout(null);
		setSize(600,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JLabel background;
	      ImageIcon img = new ImageIcon("C:\\Users\\user\\eclipse-workspace\\HelloWorld\\src\\info.jpg");
	      background = new JLabel("",img,JLabel.CENTER);
	      background.setBounds(0, 0, 600, 300);
	      add(background);
		
		memberCheck.addActionListener(new ActionListener() {



			@Override
			public void actionPerformed(ActionEvent e) {

				Client.memberList.setMemberList();
				Client.memberList.setVisible(true);
			}
		});
		visitCarCheck.addActionListener(new ActionListener() {



			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Client.carList.setCarList();
					Client.carList.setVisible(true);

				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});
	}

}
class MemberList extends JFrame {
	static JLabel memberList;
	static DefaultTableModel model;
	ArrayList<Member> listMember = null;

	MemberList(){
		setTitle("회원정보확인");
//		memberList = new JLabel("안녕");
//
//		memberList.setBounds(100,100,1000,600);
//
//		add(memberList);
		

		JTable table = new JTable();
		model = new DefaultTableModel(new String[] { "이름","아이디","비번","동호수","차량번호","1시간권","2시간권","3시간권","종일권" },0);
		table.setModel(model);

//		String contents[][] = {
//				{"박영수","90","87","98","11111"},
//				{"김영희","100","99","100","11111"},
//		};
		JScrollPane scrollpane = new JScrollPane(table);
		//scrollpane.setBounds(100, 100, 300, 100);
		add(scrollpane);
		
//		setLayout(null);
		setSize(1200,800);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent evt) {
		          Frame frame = (Frame)evt.getSource();
		          frame.setVisible(false);
		          // Further If the frame is no longer needed, call dispose
//		          frame.dispose();
		      }
		  });
		
		
		
	}
	void setMemberList() {
		String str = "";
		try {
			Client.oos.writeObject("회원정보확인");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			str+="<html><body>";
			listMember = (ArrayList<Member>)(Client.ois.readObject());
			for(Member m : listMember) {
				str+=(m);
				str+="<br/>";
			} 
			str+="</html></body>";
			System.out.println(str);
//			memberList.setText(str);
			
			for(Member m : listMember) { // "이름","아이디","비번","동호수","차량번호","1시간권","2시간권","3시간권","종일권" },0);
				model.addRow(new Object[] { m.name, m.id, m.pw, m.address, m.carNum, m.oneHour, m.twoHour, m.threeHour, m.allDay });
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
class CarList extends JFrame {
	static DefaultTableModel model;
	static JLabel carList;

	CarList(){
//		Dimension dim = new Dimension(400,150);
//		
//		JFrame frame = new JFrame("회원정보");
//		frame.setLocation(200,400);
//		frame.setPreferredSize(dim);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		JTable table = new JTable(4,4);
//		
//		frame.setSize(300,200);
//		frame.setVisible(true);
//		String header[] = {"이름","아이디","비번","동호수","차량번호"};
//		String contents[][] = {
//				{"박영수","90","87","98"},
//				{"김영희","100","99","100"},
//		};
//		

		
//		setTitle("방문차량확인");
//		carList = new JLabel();
//
//		carList.setBounds(10,10,8,6);
//		//setVisible(true);
//		add(carList);
//		setLayout(null);
		JTable table = new JTable();
		model = new DefaultTableModel(new String[] { "차량번호","입차시간" },0);
		table.setModel(model);
		
		JScrollPane scrollpane = new JScrollPane(table);
		//scrollpane.setBounds(100, 100, 300, 100);
		add(scrollpane);
		
//		setLayout(null);
		
		setSize(550,400);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLocationRelativeTo(null);
		JLabel background;
	    ImageIcon img = new ImageIcon("");
	    background = new JLabel("",img,JLabel.CENTER);
	    background.setBounds(0, 0, 300, 280);
	    add(background);
	}
	@SuppressWarnings("deprecation")
	void setCarList() {
		ArrayList<Parking> list = null;
		String str = "";
		try {
			Client.oos.writeObject("방문차량정보확인");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			str+="<html><body>";
			list = (ArrayList<Parking>)(Client.ois.readObject());
			for(Parking m : list) {
				str+=(m);
				str+="<br/>";
			} 
			str+="</html></body>";
			System.out.println(str);
//			carList.setText(str);
			for(Parking p : list) { // "이름","아이디","비번","동호수","차량번호","1시간권","2시간권","3시간권","종일권" },0);
				int mm,dd,hour,minute;
				Date date = new Date(p.parkingStartTime);
				mm = date.getMonth()+1;
				dd = date.getDate();
				hour = date.getHours();
				minute = date.getMinutes();
				model.addRow(new Object[] {p.carNum, mm+"/"+dd+"/"+hour+":"+minute});
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}