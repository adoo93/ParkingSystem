package MiniProject1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Buy_Coupon extends JFrame {
   JLabel name, adress, buyCoupon, oneHour, twoHour, threeHour, allDay;
   JTextField tCarNum;
   JLabel labelOne, labelTwo, labelThree, labelAllDay, labelPrice;
   JButton buyFinishbtn;
   JComboBox tOne,tTwo, tThree, tAllDay;
   int price=0;
   int num1=0, num2=0, num3=0, numAllDay=0;
   Buy_Coupon frame = null;
   
   Buy_Coupon(){
	   frame = this;
      setTitle("입주민 쿠폰구매 창");
      
      
      buyCoupon = new JLabel("구매할 쿠폰을 선택하세요");
      
     
      
      //couponList = new JLabel("쿠폰조회");
      
      
      
      labelOne = new JLabel("1시간");
      tOne = new JComboBox();
      tOne.setModel(new DefaultComboBoxModel(new String[] {"없음","1장", "2장", "3장", "4장", "5장"})) ;
      tOne.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			 JComboBox cb = (JComboBox) e.getSource(); // 콤보박스 알아내기
             num1 = cb.getSelectedIndex();// 선택된 아이템의 인덱스
             price = num1*1000 + num2*2000 + num3*3000 + numAllDay*10000;
             labelPrice.setText(price+"원");
		}
      });
      labelTwo = new JLabel("2시간");
      tTwo = new JComboBox();
      tTwo.setModel(new DefaultComboBoxModel(new String[] {"없음","1장", "2장", "3장", "4장", "5장"})) ;
      tTwo.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			 JComboBox cb = (JComboBox) e.getSource(); // 콤보박스 알아내기
             num2 = cb.getSelectedIndex();// 선택된 아이템의 인덱스
             price = num1*1000 + num2*2000 + num3*3000 + numAllDay*10000;
             labelPrice.setText(price+"원");
		}
      });
      labelThree = new JLabel("3시간");
      tThree = new JComboBox();
      tThree.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			 JComboBox cb = (JComboBox) e.getSource(); // 콤보박스 알아내기
             num3 = cb.getSelectedIndex();// 선택된 아이템의 인덱스
             price = num1*1000 + num2*2000 + num3*3000 + numAllDay*10000;
             labelPrice.setText(price+"원");
		}
      });
      tThree.setModel(new DefaultComboBoxModel(new String[] {"없음","1장", "2장", "3장", "4장", "5장"})) ;
      labelAllDay = new JLabel("종일권");
      tAllDay = new JComboBox();
      tAllDay.setModel(new DefaultComboBoxModel(new String[] {"없음","1장", "2장", "3장", "4장", "5장"})) ;
      tAllDay.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			 JComboBox cb = (JComboBox) e.getSource(); // 콤보박스 알아내기
             numAllDay = cb.getSelectedIndex();// 선택된 아이템의 인덱스
             price = num1*1000 + num2*2000 + num3*3000 + numAllDay*10000;
             labelPrice.setText(price+"원");
		}
      });

      labelPrice = new JLabel(price + "원");
      buyFinishbtn = new JButton("쿠폰구매완료");
      buyFinishbtn.addActionListener(new ActionListener() {		// 구매완료버튼 --> 서버:구매한 쿠폰 개수 반영.
    	  
    	  @Override
    	  public void actionPerformed(ActionEvent e) {
//            System.out.println("쿠폰구매하기");
    		  //Client.out.flush();
//    		  Client.out.println("쿠폰구매");
    		  try {
				Client.oos.writeObject("쿠폰구매");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
    		  try {
    			  Client.oos.reset();
    			  Client.oos.writeObject(new int[] {num1, num2, num3, numAllDay} );
    		  } catch (IOException e1) {
    			  e1.printStackTrace();
    		  }
    		  String result;
    		  result ="";
			try {
				//result = Client.in.readLine();
				try {
					result = (String)(Client.ois.readObject());
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    		if("쿠폰구매성공".equals(result)) {
	    		JOptionPane.showMessageDialog(null, "쿠폰 구매하였습니다~~");
	    		int[] arr = {0,0,0,0};
				try {
					arr = (int[])(Client.ois.readObject());
		    		Client.cList.v3 = arr[0];
		    		Client.cList.v4 = arr[1];
		    		Client.cList.v5 = arr[2];
		    		Client.cList.v6 = arr[3];
					System.out.println(Client.cList.v3);
					System.out.println(Client.cList.v4);
					System.out.println(Client.cList.v5);
					System.out.println(Client.cList.v6);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				// MemberCoupon창 내용 업데이트
	    		Client.cList.updateInfo();
//    			frame.setVisible(false);
	    		Client.buycoupon.setVisible(false);
//  result = "";
    			
	    	} else {
	    		System.out.println("주의! 서버로부터의 메시지가 '쿠폰구매성공' 이 아님.");
	    		System.out.println("--> result = " + result);
	    	}
    		  
    	  }   
      });
      
      
      buyCoupon.setBounds(100,30,300,30);
      
      
      //name.setBounds(40, y, width, height);
      //tname.setBounds(
//      adress.setBounds(r);
//      tAdress.setBoounds()
      labelOne.setBounds(30,150,80,30);
      tOne.setBounds(120,150,60,30);
      
      labelTwo.setBounds(220,150,80,30);
      tTwo.setBounds(300,150,60,30);
      
      labelThree.setBounds(30,220,80,30);
      tThree.setBounds(120,220,60,30);
      
      labelAllDay.setBounds(220,220,80,30);
      tAllDay.setBounds(300,220,60,30);
      
      labelPrice.setBounds(150,300, 120,30);
      buyFinishbtn.setBounds(150,360, 170,30);
      
      add(buyCoupon);
     
      add(labelOne);
      add(tOne);
      add(labelTwo);
      add(tTwo);
      add(labelThree);
      add(tThree);
      add(labelAllDay);
      add(tAllDay);
      add(labelPrice);
      add(buyFinishbtn);
      setLayout(null);
      
      Font f1;
      f1 = new Font("Comic Sans MS 굵게", Font.BOLD, 15);
      labelOne.setFont(f1); tOne.setFont(f1); labelTwo.setFont(f1);
      tTwo.setFont(f1); labelThree.setFont(f1); tThree.setFont(f1);
      labelAllDay.setFont(f1); tAllDay.setFont(f1); labelPrice.setFont(f1);
      buyFinishbtn.setFont(f1);
      
      setSize(420,500);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      JLabel background;
      ImageIcon img = new ImageIcon("C:\\Users\\user\\eclipse-workspace\\HelloWorld\\src\\cart.png");
      background = new JLabel("",img,JLabel.CENTER);
      background.setBounds(-30, 0, 420, 500);
      add(background);
      
      
   }
}

