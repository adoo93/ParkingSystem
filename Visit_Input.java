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
import javax.swing.JTextField;

class Result extends JFrame{
	static JLabel result;
	JButton ok;
	
	Result(){
		setTitle("방문차량");
		
		result = new JLabel("차량이 등록되었습니다.");
		ok = new JButton("확인");
		
		result.setBounds(70,80,150,30);
		ok.setBounds(80, 150, 100,30);
		
		add(result);
		add(ok);
		setLayout(null);

	    setSize(300, 300);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
    	JLabel background;
    	ImageIcon img = new ImageIcon("C:\\Users\\user\\eclipse-workspace\\HelloWorld\\src\\폭죽2.png");
    	background = new JLabel("",img,JLabel.CENTER);
    	background.setBounds(-30, -30, 300, 300);
    	add(background);

	ok.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent arg0) { 
		Client.result.setVisible(false);
		Client.visit.setVisible(false);
		}

	

	});
}
}
public class Visit_Input  extends JFrame{

   JLabel car_Num, result;
   JTextField tcar_Num;
   JButton joinbtn;
   Visit_Input(){
      setTitle("방문객 입차 차량 창 입니다 ");
      
      car_Num = new JLabel("차량번호");
      

      
      tcar_Num = new JTextField();
      
      joinbtn = new JButton("등록");

     
      car_Num.setBounds(40,80,100,30);
      tcar_Num.setBounds(110,80,100,30);
      
      joinbtn.setBounds(100, 250, 100,30);

      
      add(car_Num);
      add(tcar_Num);
      
      add(joinbtn);
      setLayout(null);
      
      Font f1;
      f1 = new Font("Comic Sans MS 굵게", Font.BOLD, 15);
      car_Num.setFont(f1);
      tcar_Num.setFont(f1);
      joinbtn.setFont(f1);

      setSize(400, 600);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      
      JLabel background;
      ImageIcon img = new ImageIcon("C:\\Users\\user\\eclipse-workspace\\HelloWorld\\src\\wc2.jpg");
      background = new JLabel("",img,JLabel.CENTER);
      background.setBounds(85, -15, 400, 600);
      add(background);
      
      joinbtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            //System.out.println("등록 성공");
        	 String carNum = tcar_Num.getText();
        	 try {
				Client.oos.writeObject(new Parking(carNum));
//				String result = Client.in.readLine();
				String result = (String)(Client.ois.readObject());
				System.out.println(result);
				//Result.result = new JLabel(result);
				Client.result.setVisible(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

         }
      });
   }

   public static void main(String[] args) {


//      while(true) {
//         HashMap<String, String> hMap  = new HashMap<String, String>();
//         hMap.put("101호",  "서울1111");
//
//         Scanner sc = new Scanner(System.in);
//         String ho = sc.nextLine();
//         System.out.println("차량번호 :");
//         String car_num = sc.nextLine();
//
//         if(car_num.equals(hMap.get(ho))){//map으로 해놓으면 id를 오브젝트 파일로 불러올수 잇다
//            System.out.println("차량 입차 성공!");
//         }
//
//         if(hMap.get(ho).equals(car_num)) {
//            System.out.println("차량입차 성공!");
//         }
//         else {
//            System.out.println("차량입차 실패.");
//         }
//         sc.close();
//      }
   }
}

