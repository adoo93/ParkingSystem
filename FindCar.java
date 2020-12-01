package MiniProject1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FindCar extends JFrame{
	JLabel car_Num, result;
	   JTextField tcar_Num;
	   JButton findbtn;
	   
	   FindCar(){
	      setTitle("출차하기 ");
	      
	      car_Num = new JLabel("차량번호");
	      

	      
	      tcar_Num = new JTextField();
	      
	      findbtn = new JButton("조회");

	     
	      car_Num.setBounds(40,80,100,30);
	      tcar_Num.setBounds(110,80,100,30);
	      
	      findbtn.setBounds(100, 250, 100,30);

	      
	      add(car_Num);
	      add(tcar_Num);
	      
	      add(findbtn);
	      setLayout(null);
	      Font f1;
	      Font f2;
	      f1 = new Font("Comic Sans MS 굵게", Font.BOLD, 15);
	      f2 = new Font("Comic Sans MS 굵게", Font.BOLD, 12);
	      car_Num.setFont(f1); tcar_Num.setFont(f1); findbtn.setFont(f2);

	      setSize(400, 600);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setLocationRelativeTo(null);
	      findbtn.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	
}
});
	      }
	   }