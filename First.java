package MiniProject1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class First extends JFrame{
	   
	   JButton b1 = new JButton("관리자");
	   JButton b2 = new JButton("입주민");
	   JButton b3 = new JButton("방문객");
	   
	   
	   static JFrame frame;
	   
	   First(){
		   
		 
		   
	      setTitle("메인창");
	      b1.setBounds(70,100,100,50);
	      b2.setBounds(260,100,100,50);
	      b3.setBounds(460,100,100,50);
	      
	      add(b1);
	      add(b2);
	      add(b3);
	      Font f1;
	      f1 = new Font("Comic Sans MS 굵게", Font.BOLD, 15);
	      b1.setFont(f1);
	      b2.setFont(f1);
	      b3.setFont(f1);
	      
	      setLayout(null);
	      setSize(650,300);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setLocationRelativeTo(null);
	      //setBackground(Color.green);배경화면색깔넣어보려니,에러남
	      JLabel background;
	      ImageIcon img = new ImageIcon("C:\\Users\\user\\eclipse-workspace\\HelloWorld\\src\\pm.jpg");
	      background = new JLabel("",img,JLabel.CENTER);
	      background.setBounds(0, 0, 650, 350);
	      add(background);
	      
	      b1.addActionListener(new ActionListener() {

	         @Override
	         public void actionPerformed(ActionEvent e) {    
	            //ParkingMain.first.setVisible(true);
	        	 Client.admin.setVisible(true);
	        	 //Client.out.println("관리자 로그인");
	        	 try {
					Client.oos.writeObject("관리자 로그인");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	         }
	      });
	      b2.addActionListener(new ActionListener() {
	         
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            //ParkingMain.first.setVisible(false);
	            Client.member.setVisible(true);
	            
	         }
	      });
	      b3.addActionListener(new ActionListener() {
	         
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            //ParkingMain.first.setVisible(false);
	            Client.visit.setVisible(true);
	            //Client.out.println("입차");
	            try {
					Client.oos.writeObject("입차");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	         }
	      });
	      try {
			   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
	      
	}
}