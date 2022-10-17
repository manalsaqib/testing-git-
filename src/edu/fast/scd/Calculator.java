package edu.fast.scd;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Calculator extends Frame implements ActionListener {
	protected Label lbl1 = null; //label
	protected TextField txt1;  //text field
	protected Label lbl2 = null;
	protected TextField txt2;
	protected TextField txt3;
	protected Button btn1; //button
	protected Button btn2;
	protected Button btn3;
	protected Button btn4;
	protected Button btn5;
	protected Button btn6;
	protected Label lbl3 = null;
	
	public Calculator()  //constructor
	{
		lbl1 = new Label("First value");  //setting labels names
		

		txt1 = new TextField(9); //setting width of text field
		
		lbl2 = new Label("Second value");
		lbl2.setAlignment(WIDTH);
		txt2 = new TextField(9);
		lbl3 = new Label("Result");
		txt3 = new TextField(13);

		btn1 = new Button("Add");
		btn1.addActionListener(this);
		btn2 = new Button("Subtract");
		btn2.addActionListener(this);
		btn3 = new Button("Multiply");
		btn3.addActionListener(this);
		btn4 = new Button("Division");
		btn4.addActionListener(this);
		btn5 = new Button("Power");
		btn5.addActionListener(this);
		btn6 = new Button("Sqaureroot");
		btn6.addActionListener(this);
		
		this.add(lbl1);    //adding buttons
		this.add(txt1);
		
		this.add(lbl2);
		this.add(txt2);
		
		
		this.add(lbl3); //adding labels into frame
		this.add(txt3);
		this.add(btn1);
		btn1.setBackground(Color.CYAN);  //setting color of button background
		
		this.add(btn2);
		btn2.setBackground(Color.CYAN);
		
		this.add(btn3);
		btn3.setBackground(Color.CYAN);
		this.add(btn4);
		btn4.setBackground(Color.CYAN);
		this.add(btn5);
		btn5.setBackground(Color.CYAN);
		this.add(btn6);
		btn6.setBackground(Color.CYAN);
		//this.add(btn2);
		
		
		this.setSize(570,200);  //setting size of frame
		this.setTitle("My Calculator");  //setting name of frame
		this.addWindowListener(new WindowAdapter()  //window closing function
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

		this.setLayout(new FlowLayout());
		
			}

	@Override
	public void actionPerformed(ActionEvent arg0) {
				
		try {  //for adding 
		double d1 = Double.parseDouble(txt1.getText());
		double d2 = Double.parseDouble(txt2.getText());
		double d3 = d1 + d2;
		
		this.txt3.setText(String.valueOf(d3));
		
		}
		catch(NumberFormatException e)
		{
			txt3.setText(String.valueOf("Wrong input"));
		}
		if(arg0.getSource()==btn2)  //for subtracting
		{
			//setBackground(Color.BLUE);
			try {
			double s1 = Double.parseDouble(txt1.getText());
			double s2 = Double.parseDouble(txt2.getText());
			double sub = s1 - s2;
			this.txt3.setText(String.valueOf(sub));
			}
			catch(NumberFormatException e)
			{
				txt3.setText(String.valueOf("Wrong input"));  //handling exception to not enter String in text field 
			
			}
		}
		
	if(arg0.getSource()==btn3)    //for multiplying
	{
		try {
		double m1 = Double.parseDouble(txt1.getText());
		double m2 = Double.parseDouble(txt2.getText());
		double mult =m1*m2;
		
		
		this.txt3.setText(String.valueOf(mult));
		}
		catch(NumberFormatException e)
		{
			txt3.setText(String.valueOf("Wrong input"));
		
		}
		}

	
	if(arg0.getSource()==btn4)  //for dividing
	{
		try {
		double d1 = Double.parseDouble(txt1.getText());
		double d2 = Double.parseDouble(txt2.getText());
		double div =d1/d2;
		
		
		this.txt3.setText(String.valueOf(div));
		}
		catch(NumberFormatException e)
		{
			txt3.setText(String.valueOf("Wrong input"));
			
	
		}
		

		}
	if(arg0.getSource()==btn5)  //for taking power
	{
		try {
		double p1 = Double.parseDouble(txt1.getText());
		double p2 = Double.parseDouble(txt2.getText());
		double power;
		power=Math.pow(p1, p2);
		
		
		this.txt3.setText(String.valueOf(power));
		}
		catch(NumberFormatException e)
		{
			txt3.setText(String.valueOf("Wrong input"));
			
	
		}
		

		}
	if(arg0.getSource()==btn6)  //for taking square root
	{
		try {
		double sq1 = Double.parseDouble(txt1.getText());
		//double sq2 = Double.parseDouble(txt2.getText());
		double sqr=0;
		
		sqr=Math.sqrt(sq1);
				
		
		this.txt3.setText(String.valueOf(sqr));
		//this.txt3.setText(String.valueOf(sqr1));

		}
		catch(NumberFormatException e)
		{
			txt3.setText(String.valueOf("Wrong input"));
			
	
		}
		finally  //runs every time
		{
			txt2.setText(String.valueOf(" Not Required"));
		}
		
	


		}


	}

		// TODO Auto-generated method stub
			

	public static void main(String[] args) {
		Calculator calc = new Calculator();
		calc.setVisible(true);
		calc.setLocation(300,300);
		// TODO Auto-generated method stub

	}

}
