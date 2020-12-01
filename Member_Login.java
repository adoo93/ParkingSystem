package MiniProject1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class Member_Login extends JFrame {

   JLabel id, pw;
   JTextField tId;
   JPasswordField tPassword;
   JButton loginbtn;
   JButton joinbtn;
   
   Member_Login(){
      setTitle("입주민 로그인 창");
      id = new JLabel("ID");
      pw = new JLabel("PW");
      
      tId = new JTextField();
      tPassword = new JPasswordField();
      loginbtn = new JButton("로그인");
      joinbtn = new JButton("회원가입");
      
      id.setBounds(120,130,100,30);
      tId.setBounds(190,130,100,30);
      pw.setBounds(120,180,100,30);
      tPassword.setBounds(190,180,100,30);
      loginbtn.setBounds(150,300,100,30);
      joinbtn.setBounds(260, 300, 100,30);
      
      add(id);
      add(tId);
      add(pw);
      add(tPassword);
      add(loginbtn);
      add(joinbtn);
      setLayout(null);
      
      Font f1;
      f1 = new Font("Comic Sans MS 굵게", Font.BOLD, 15);
      id.setFont(f1); tId.setFont(f1); pw.setFont(f1); tPassword.setFont(f1);
      loginbtn.setFont(f1); joinbtn.setFont(f1);
      
      
      setSize(500,500);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      
      JLabel background;
      ImageIcon img = new ImageIcon("C:\\Users\\user\\eclipse-workspace\\HelloWorld\\src\\pa.jpg");
      background = new JLabel("",img,JLabel.CENTER);
      background.setBounds(0, 0, 500, 470);
      add(background);
      
      joinbtn.addActionListener(new ActionListener() {
    	  
    	  @Override
    	  public void actionPerformed(ActionEvent e) {
//    		  Client.out.println("회원가입");
    		  try {
				Client.oos.writeObject("회원가입");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		  Client.mjoin.setVisible(true);
    		  
    	  }
      });
      
      loginbtn.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
//        	 Client.out.println("로그인");
        	 try {
				Client.oos.writeObject("로그인");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
            //System.out.println("로그인 성공");
        	 String id = tId.getText();
        	 String pw = String.valueOf(tPassword.getPassword());
        	 //System.out.println(id+"/"+pw);
        	 try {
				Client.oos.writeObject(new Login(id,pw));
				//String result = Client.in.readLine();
				
				String result = (String)(Client.ois.readObject()); //로그인 성공, 실패 여부 받는 문장.
				
				System.out.println("서버로부터:"+result);
//				Client.out.println(".");
				if(result.equals("로그인 실패")) {
					//Client.out.println("로그인");
					Client.oos.writeObject("로그인");
				}else {
					//Client.loginId=id;
					//Client.out.println(id);
					Client.oos.writeObject(id);
					
					Object[] arrReceived = (Object[])(Client.ois.readObject());
					String v1,v2;
					int v3,v4,v5,v6;
					v1 = (String)arrReceived[0];
					v2 = (String)arrReceived[1];
					v3 = (int)arrReceived[2];
					v4 = (int)arrReceived[3];
					v5 = (int)arrReceived[4];
					v6 = (int)arrReceived[5];
					//System.out.println(v1 + v2 + v3 + v4 + v5 + v6);
					Client.cList.displayInfo(v1, v2, v3, v4, v5, v6);
					
					Client.cList.setVisible(true);
					Client.member.setVisible(false);
//					Client.cList.setVisible(true);
//					MemberCouponList.setVisible(true);
//					System.out.println("출차로 넘어가야:"+id);
					
					// 출차로 넘어감.
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
        	 
        	 
         }
         
      });
   }
   public static void main(String[] args) {
      while(true) {

   }
   }
      
}

   
      
   
