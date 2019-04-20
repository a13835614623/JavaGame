package com.zzk;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JComboBox;

import com.zzk.grammar.RandomKVGrammar;
import com.zzk.interpreter.GrammarInterpreterF;

/**
 * 客户端，绘制自定义分形树
 * 
 * @author zzk
 */
public class Client2 extends Frame {
	/**
	 * 序列号
	 */
	private static final int FRAME_WIDTH = 1000;// 窗口宽度
	private static final int FRAME_HEIGHT = 1000;// 窗口高度
	private static final long serialVersionUID = -1503870997066468394L;
	private String grammarString;// 文法字符串
	private AbstractGrammar grammar;// 语法分析器
	private Point startPoint = new Point(500, 900);// 起始位置
	private double startAngle = 90;// 起始角度
	private double rotateAngle = 25;// 旋转角
	private int length = 5;// 线段长度
	private int n = 5;// 迭代次数
	private AbstractGrammarInterpreter interpreter = null;// 解释器
	{
		interpreter = new GrammarInterpreterF(length, grammarString, startPoint, startAngle, rotateAngle);
	}

	public void loadFrame() {
		this.setTitle("自定义分形");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setBackground(Color.WHITE);
		this.setLocationRelativeTo(null);// 居中
		this.setResizable(false);
		this.setFont(new Font("幼圆", Font.BOLD, 20));
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setLayout(null);
		this.initComponent();// 初始化组件
		this.setVisible(true);
	}

	// 初始化组件
	public void initComponent() {
		Label tips = new Label("自定义产生式:(左部必须有包含'F',所有字符只能为\n'+', '-', '[', ']','F'中的一种)");
		tips.setBounds(200, 50, 1000, 50);
		this.add(tips);
		Panel pRight = new Panel();// 按钮区域面板
		pRight.setLocation(250, 100);
		pRight.setSize(500, 150);
		Label product1 = new Label("产生式1:");
		TextField left1 = new TextField(5);// 左部1
		Label labelP1 = new Label("->");
		TextField right1 = new TextField(15);// 右部1

		Label product2 = new Label("产生式2:");
		TextField left2 = new TextField(5);// 左部2
		Label labelP2 = new Label("->");
		labelP2.setSize(20, 20);
		TextField right2 = new TextField(15);// 右部2

		Button btnDraw = new Button("绘制");
		char start = 'F';
		char[] notEnd = { 'F' };
		char[] end = { '+', '-', '[', ']' };
		String[] product = {};// 树
		btnDraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String[]> map = new HashMap<>();
				String l1 = left1.getText();
				String r1 = right1.getText();
				String l2 = left2.getText();
				String r2 = right2.getText();
				if(l1!=null&&!"".equals(l1)&&(r1!=null&&!"".equals(r1))){
					map.put(l1, new String[] { r1 });
				}
				
				if(l2!=null&&!"".equals(l2)&&(r2!=null&&!"".equals(r2))){
					if(l2.equals(l1)){
						map.put(l1, new String[]{r1,r2});
					}
					else{
						map.put(l2, new String[] { r2 });
					}
				}
				grammar = new RandomKVGrammar(start, end, notEnd, map);
				grammarString = grammar.product(n);
				interpreter = new GrammarInterpreterF(length, grammarString, startPoint, startAngle, rotateAngle);
				repaint();
			}
		});
		Label label = new Label("迭代次数");
		Integer[] ints = { 1, 2, 3, 4, 5, 6, 7, 8 };
		JComboBox<Integer> box = new JComboBox<>(ints);
		box.setSelectedItem(n);
		box.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					n = (int) e.getItem();
				}
			}
		});
		pRight.add(product1);
		pRight.add(left1);
		pRight.add(labelP1);
		pRight.add(right1);

		pRight.add(product2);
		pRight.add(left2);
		pRight.add(labelP2);
		pRight.add(right2);

		pRight.add(label);
		pRight.add(box);
		pRight.add(btnDraw);

		this.add(pRight);
	}

	@Override
	public void paint(Graphics g) {
		interpreter.interpret(g);
	}

	public static void main(String[] args) {
		new Client2().loadFrame();
	}
}
