package MiniProject1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Member_Join extends JFrame {

	// ----아래쪽은 입주민 회원가입창

	JLabel name, id, pw, carNum, adress;
	JTextField tName, tId, tCarNum, tAdress;
	JPasswordField tPassword;
	JButton joinbtn;

	Member_Join() {
		setTitle("입주민 회원가입 창");
		name = new JLabel("성명");
		id = new JLabel("ID");
		pw = new JLabel("PW");
		carNum = new JLabel("차량번호");
		adress = new JLabel("동호수");

		tName = new JTextField();
		tId = new JTextField();
		tPassword = new JPasswordField();
		tCarNum = new JTextField();
		tAdress = new JTextField();
		joinbtn = new JButton("가입");

		name.setBounds(40, 30, 100, 30);
		id.setBounds(40, 80, 100, 30);
		pw.setBounds(40, 130, 100, 30);
		carNum.setBounds(40, 180, 100, 30);
		adress.setBounds(40, 230, 100, 30);

		tName.setBounds(130, 30, 100, 30);
		tId.setBounds(130, 80, 100, 30);
		tPassword.setBounds(130, 130, 100, 30);
		tCarNum.setBounds(130, 180, 100, 30);
		tAdress.setBounds(130, 230, 100, 30);

		joinbtn.setBounds(150, 330, 100, 30);

		add(name);
		add(tName);
		add(id);
		add(tId);
		add(pw);
		add(tPassword);
		add(carNum);
		add(tCarNum);
		add(adress);
		add(tAdress);
		add(joinbtn);
		setLayout(null);

		setSize(350, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		Font f1;
	    f1 = new Font("Comic Sans MS 굵게", Font.BOLD, 15);
	    name.setFont(f1); tName.setFont(f1); id.setFont(f1); tId.setFont(f1);
	    pw.setFont(f1);  tPassword.setFont(f1);  carNum.setFont(f1); tCarNum.setFont(f1);
		adress.setFont(f1);  tAdress.setFont(f1);  joinbtn.setFont(f1);
		setVisible(true);
		JLabel background;
		ImageIcon img = new ImageIcon("C:\\Users\\user\\eclipse-workspace\\HelloWorld\\src\\Join.png");
		background = new JLabel("",img,JLabel.CENTER);
		background.setBounds(-100, 150, 350, 450);
		add(background);
		joinbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				Client.out.println("회원가입");
				// Client.memberlogin.setVisible(true);
				// Client.memberjoin.setVisible(true);
				String name = tName.getText();
				String id = tId.getText();
				String pw = String.valueOf(tPassword.getPassword());
				String carNum = tCarNum.getText();
				String adress = tAdress.getText();

//	            System.out.println(tName.getText());
//	            System.out.println(tId.getText());

				try {
//	               Client.oos.writeObject(new Member(name, id,pw,carNum,adress));

					int oneHour = 20, twoHour = 15, threeHour = 10, allDay = 1;
					Client.oos.writeObject(new Member(name, id, pw, carNum, adress, oneHour, twoHour, threeHour, allDay));
//					Client.out.println("가입완료.");
					try {
						String result = (String)(Client.ois.readObject());
						JOptionPane.showMessageDialog(null, "가입완료되었습니다.");
						Client.mjoin.setVisible(false);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
//					try {
//						
//					} catch (ClassNotFoundException e1) {
//						e1.printStackTrace();
//					}
//	               String result = Client.in.readLine();
//	               System.out.println("서버로부터:"+result);
//	               if(result.equals("회원가입 실패")) {
//	                  Client.out.println("회원가입");
//	               }//else {
					// Client.membername=name;
					// 로그인으로 넘어가야함
//					Client.welcome.setVisible(true);
					
					
					System.out.println("가입되었습니다.");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
class Welcome extends JFrame{
	JLabel welcome;
	JButton ok;
	
	Welcome(){
		setTitle("회원가입");
		welcome = new JLabel("가입 완료되었습니다. ");
		ok = new JButton("확인");
		
		ok = new JButton();
		ok.setBounds(100, 150, 100,30);
		welcome = new JLabel();
		welcome.setBounds(40,80,100,30);
		
		add(ok);
		add(welcome);
		setLayout(null);
		setSize(300,300);
		
		Font f1;
	    f1 = new Font("Comic Sans MS 굵게", Font.BOLD, 15);
	    welcome.setFont(f1);
	    ok.setFont(f1);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setLocationRelativeTo(null);
	      
	    setVisible(false);

	    ok.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent arg0) { 
			Client.welcome.setVisible(false);
			Client.mjoin.setVisible(false);
			}
	
});
}
}




