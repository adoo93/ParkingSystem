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
import javax.swing.JTextField;


public class MemberCouponList extends JFrame {
   JLabel tName, tAddress,name, address, carNum, totalTime, text, couponList,oneHour, twoHour, threeHour, allDay, tOne, tTwo, tThree, tDay;
   JTextField  tCarNum, tTotalTime;
   JButton listbtn, onebtn, twobtn, threebtn, allDaybtn, buyCouponbtn, outbtn;
   MemberCouponList frame = null;
   int v3,v4,v5,v6;

   MemberCouponList() {
	   frame = this;
      setTitle("입주민 차량조회 및 쿠폰리스트 창");
      
      //---이름, 동호수 추가
       name = new JLabel("성명");
        address = new JLabel("동호수");
        
        tName = new JLabel();
        tAddress = new JLabel();
        
       //----------------------- 
       
      outbtn = new JButton("출차");
      carNum = new JLabel("방문차량번호");
      tCarNum = new JTextField();//차량번호조회입력창
      listbtn = new JButton();
      totalTime = new JLabel("총주차시간");
//      tTotalTime = new JTextField();
//      text = new JLabel("초 입니다");

      couponList = new JLabel("쿠폰조회");

      listbtn = new JButton("조회");
      onebtn = new JButton("1시간");
      tOne = new JLabel("33");
      twobtn = new JButton("2시간");
      tTwo = new JLabel("33");
      threebtn = new JButton("3시간");
      tThree = new JLabel("33");
      allDaybtn = new JButton("종일권");
      tDay = new JLabel("33");
      buyCouponbtn = new JButton("쿠폰구매하기");

      outbtn.setBounds(130,420, 120,30);
      name.setBounds(30,40,70,30);
      tName.setBounds(100,40,100,30);
      address.setBounds(230,40,100,30);
      tAddress.setBounds(280,40,100,30);
      
      carNum.setBounds(40,130,100,30);
      tCarNum.setBounds(130,130,100,30);
      listbtn.setBounds(240, 130, 80, 30);
      totalTime.setBounds(40,180,2000,30);
//      tTotalTime.setBounds(130,180,100,30);
//      text.setBounds(240,180,60,30);

      couponList.setBounds(150,240,100,30);

      onebtn.setBounds(40,290,120,30);
      tOne.setBounds(160,290,50,30);

      twobtn.setBounds(200,290,120,30);
      tTwo.setBounds(320,290,50,30);

      threebtn.setBounds(40,340,120,30);
      tThree.setBounds(160,340,50,30);

      allDaybtn.setBounds(200,340,120,30);
      tDay.setBounds(320,340,50,30);
      buyCouponbtn.setBounds(200,520, 160,30);

      onebtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
//			Client.out.println("쿠폰사용");
			try {
				Client.oos.writeObject("쿠폰사용");
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {
				Client.oos.writeObject(tCarNum.getText());  // 차량번호.
				Client.oos.writeObject(new Integer(1));	// 1시간권.
				//String result = Client.in.readLine();
				String result = (String)(Client.ois.readObject());
				if("차감성공".equals(result)) {
					int count = Integer.parseInt(tOne.getText());
					tOne.setText("" + (--count));
					JOptionPane.showMessageDialog(frame, "쿠폰 사용되었습니다.");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
      });
      twobtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
//			Client.out.println("쿠폰사용");
			try {
				Client.oos.writeObject("쿠폰사용");
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {
				Client.oos.writeObject(tCarNum.getText());  // 차량번호.
				Client.oos.writeObject(new Integer(2));	// 2시간권.
				//String result = Client.in.readLine();
				String result = (String)(Client.ois.readObject());
				if("차감성공".equals(result)) {
					int count = Integer.parseInt(tTwo.getText());
					tTwo.setText("" + (--count));
					JOptionPane.showMessageDialog(frame, "쿠폰 사용되었습니다.");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
      });
      threebtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
//			Client.out.println("쿠폰사용");
			try {
				Client.oos.writeObject("쿠폰사용");
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {
				Client.oos.writeObject(tCarNum.getText());  // 차량번호.
				Client.oos.writeObject(new Integer(3));   // 3시간권.
				//String result = Client.in.readLine();
				String result = (String)(Client.ois.readObject());
				if("차감성공".equals(result)) {
					int count = Integer.parseInt(tThree.getText());
					tThree.setText("" + (--count));
					JOptionPane.showMessageDialog(frame, "쿠폰 사용되었습니다.");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
      });
      allDaybtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Client.oos.writeObject("쿠폰사용");
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {
				Client.oos.writeObject(tCarNum.getText());  // 차량번호.
				Client.oos.writeObject(new Integer(24));   // 3시간권.
				//String result = Client.in.readLine();
				String result = (String)(Client.ois.readObject());
				if("차감성공".equals(result)) {
					int count = Integer.parseInt(tDay.getText());
					tDay.setText("" + (--count));
					JOptionPane.showMessageDialog(frame, "쿠폰 사용되었습니다.");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}

		}
      });
       
      add(outbtn);
      add(name);
      add(tName);
      add(address);
      add(tAddress);

      add(listbtn);
      
      add(carNum);
      add(tCarNum);
      add(totalTime);
//      add(tTotalTime);
//      add(text);
      add(couponList);
      add(onebtn);
      add(tOne);
      add(twobtn);
      add(tTwo);
      add(threebtn);
      add(tThree);
      add(allDaybtn);
      add(tDay);
      add(buyCouponbtn);
      setLayout(null);

      setSize(430, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      
      Font f1;
      f1 = new Font("Comic Sans MS 굵게", Font.BOLD, 15);
      outbtn.setFont(f1); name.setFont(f1); tName.setFont(f1); address.setFont(f1);
      tAddress.setFont(f1); listbtn.setFont(f1); carNum.setFont(f1); tCarNum.setFont(f1);
      totalTime.setFont(f1); couponList.setFont(f1); onebtn.setFont(f1);
      tOne.setFont(f1); twobtn.setFont(f1); threebtn.setFont(f1); tThree.setFont(f1);
      allDaybtn.setFont(f1); tDay.setFont(f1); buyCouponbtn.setFont(f1);
      
      JLabel background;
      ImageIcon img = new ImageIcon("C:\\Users\\user\\eclipse-workspace\\HelloWorld\\src\\coupon3.jpg");
      background = new JLabel("",img,JLabel.CENTER);
      background.setBounds(0, -30, 430, 600);
      add(background);
      
      
      listbtn.addActionListener(new ActionListener() {	//조회버튼.
          
          @Override
          public void actionPerformed(ActionEvent e) {
//        	 Client.out.println(loginId);
//        	 Client.out.println("주차시간확인");
        	 try {
				Client.oos.writeObject("주차시간확인");
				String carNum = tCarNum.getText();
				Client.oos.writeObject(carNum);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
             try {
//				String parkingResult = Client.in.readLine();
//				System.out.println(parkingResult);
//				totalTime.setText(parkingResult);
            	 
            	int result = (int)(Client.ois.readObject());
            	
            	String parkingResult = null;
            	if(result>0) {
            		parkingResult = "총 주차시간은 "+result+"초 입니다.";
            	} else {
            		parkingResult = "현재까지 무료입니다.";
            	}
            	System.out.println(parkingResult);
            	totalTime.setText(parkingResult);
            	
//				Client.out.println("쿠폰확인");   // 추가한 부분. 수정 필요. 
//				int oneHour = Client.in.read();
//				int twoHour = Client.in.read();
//				int threeHour = Client.in.read();
//				int allDay = Client.in.read();
//			    tOne.setText("X"+oneHour);
//			    tTwo.setText("X"+twoHour);
//			    tThree.setText("X"+threeHour);
//			    tDay.setText("X"+allDay);
			    
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
          }
            
       });
      

      buyCouponbtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println("쿠폰구매하기");
            Client.buycoupon.setVisible(true);

         }

      });
      
      outbtn.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
        	 String carNum = tCarNum.getText();
        	 try {
        		 Client.oos.writeObject("출차");
				Client.oos.writeObject(carNum);
				String result = (String)(Client.ois.readObject());
				if(("출차성공").equals(result)) {
					JOptionPane.showMessageDialog(null, "굿바이");
					Client.cList.setVisible(false);
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
          }

       });

   }
   
   void displayInfo(String s1, String s2, int v3, int v4, int v5, int v6) {
	   tName.setText(s1);
	   tAddress.setText(s2);
	   this.v3 = v3;
	   this.v4 = v4;
	   this.v5 = v5;
	   this.v6 = v6;
	   updateInfo();
//	   tOne.setText(""+v3);
//	   tTwo.setText(""+v4);
//	   tThree.setText(""+v5);
//	   tDay.setText(""+v6);
	   Font f1;
	   f1 = new Font("Comic Sans MS 굵게", Font.BOLD, 15);
	   tName.setFont(f1); tAddress.setFont(f1);
	   
   }

   void updateInfo() {
	   tOne.setText(""+v3);
	   tTwo.setText(""+v4);
	   tThree.setText(""+v5);
	   tDay.setText(""+v6);
	   Font f1;
	   f1 = new Font("Comic Sans MS 굵게", Font.BOLD, 15);
	   tOne.setFont(f1);
	   tTwo.setFont(f1);
	   tThree.setFont(f1);
	   tDay.setFont(f1);
	   
   }
}
