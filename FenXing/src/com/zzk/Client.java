package com.zzk;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JComboBox;

import com.zzk.grammar.RandomKVGrammar;
import com.zzk.grammar.SingleKVGrammar;
import com.zzk.interpreter.GrammarInterpreterAB;
import com.zzk.interpreter.GrammarInterpreterF;
import com.zzk.util.DrawUtil;

/**
 * 客户端，绘制分形树
 * @author zzk
 */
public class Client extends Frame {
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
	private double rotateAngle = 30;// 旋转角
	private int length = 5;// 线段长度
	private int n = 5;// 迭代次数
	private AbstractGrammarInterpreter interpreter = null;// 解释器
	{
		interpreter = new GrammarInterpreterF(length, grammarString, startPoint, startAngle, rotateAngle);
	}
	/**
	 * 分形树AB
	 */
	public void initTreeAB() {
		char start = 'B';
		char[] notEnd = { 'A', 'B' };
		char[] end = { '(', ')', '[', ']' };
		String[] productA = { "AA" };
		String[] productB = { "A[B]AA(B)" };
		HashMap<String, String[]> map = new HashMap<>();
		map.put("A", productA);
		map.put("B", productB);
		grammar = new SingleKVGrammar(start, end, notEnd, map);//使用SingleKV语法分析
		grammarString = grammar.product(n);
	}

	/**
	 * 随机分形树
	 */
	public void initRandomTree() {
		char start = 'F';
		char[] notEnd = { 'F' };
		char[] end = { '+', '-', '[', ']' };
		String[] product = { "F-F++F-F", "F[+F]F[-F[+F]]", "FF+[+F+F]-[+F]", "F[+F][-F]", "F" };// 树

		HashMap<String, String[]> map = new HashMap<>();
		map.put("F", product);
		grammar = new RandomKVGrammar(start, end, notEnd, map);
		grammarString = grammar.product(n);
	}

	/**
	 * 倒松树形状
	 */
	public void initPine() {
		char start = 'F';
		char[] notEnd = { 'F' };
		char[] end = { '+', '-', '[', ']' };
		String[] product = { "F[+F]F[-F[+F]]" };// 类倒松树
		HashMap<String, String[]> map = new HashMap<>();
		map.put("F", product);
		grammar = new RandomKVGrammar(start, end, notEnd, map);
		grammarString = grammar.product(n);
	}

	/**
	 * 雪花形状
	 */
	public void initSnowflake() {
		char start = 'F';
		char[] notEnd = { 'F' };
		char[] end = { '+', '-', '[', ']' };
		String[] product = { "F-F++F-F" };// 雪花
		HashMap<String, String[]> map = new HashMap<>();
		map.put("F", product);
		grammar = new RandomKVGrammar(start, end, notEnd, map);
		grammarString = grammar.product(n);
	}

	/**
	 * 绘制对称分形树，使用时设置length为100，自动开启length自减开关
	 */
	public void initSymmetricalTree() {
		char start = 'F';
		char[] notEnd = { 'F' };
		char[] end = { '+', '-', '[', ']' };
		String[] product = { "F[+F][-F]" };// 对称分形树，使用时设置length为100
		HashMap<String, String[]> map = new HashMap<>();
		map.put("F", product);
		grammar = new RandomKVGrammar(start, end, notEnd, map);
		grammarString = grammar.product(n);
	}

	public void loadFrame() {
		initRandomTree();
		this.setTitle("分形");
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
		Panel pNorth = new Panel(new FlowLayout());// 按钮区域面板
		pNorth.setBounds(100, 50, 800, 50);
		Button btnRandomTree = new Button("随机分形树");
		btnRandomTree.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initRandomTree();
				length = 5;
				startAngle = 90;
				rotateAngle = 25;
				startPoint.x = 500;
				startPoint.y = 900;
				interpreter = new GrammarInterpreterF(length, grammarString, startPoint, startAngle, rotateAngle);
				repaint();
			}
		});
		Button btnSymmetricalTree = new Button("对称分形树");
		btnSymmetricalTree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initSymmetricalTree();
				length = 100;
				startAngle = 90;
				rotateAngle = 25;
				startPoint.x = 500;
				startPoint.y = 900;
				interpreter = new GrammarInterpreterF(length, grammarString, startPoint, startAngle, rotateAngle);
				repaint();
			}
		});
		Button btnSnowflake = new Button("雪花");
		btnSnowflake.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initSnowflake();
				length = 100 / n;
				startAngle = 30;
				rotateAngle = 60;
				startPoint.x = 1000;
				startPoint.y = 500;
				interpreter = new GrammarInterpreterF(length, grammarString, startPoint, startAngle, rotateAngle);
				repaint();
			}
		});
		Button btnSpecialTree = new Button("AB树");
		btnSpecialTree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initTreeAB();
				length = 100/(n*n);
				startAngle = 90;
				rotateAngle = 45;
				startPoint.x = 500;
				startPoint.y = 900;
				interpreter = new GrammarInterpreterAB(length, grammarString, startPoint, startAngle, rotateAngle);
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
		pNorth.add(label);
		pNorth.add(box);
		pNorth.add(btnRandomTree);
		pNorth.add(btnSnowflake);
		pNorth.add(btnSymmetricalTree);
		pNorth.add(btnSpecialTree);
		this.add(pNorth);
		//颜色块
		Panel pMiddle = new Panel();
		Label labelColor = new Label("线条颜色");
		pMiddle.setBounds(100, 100, 800, 50);
		Button btnRed = new Button("  ");
		Button btnGreen = new Button("  ");
		Button btnBlue = new Button("  ");
		Button btnBlack = new Button("  ");
		
		btnRed.setBackground(Color.RED);
		btnGreen.setBackground(Color.GREEN);
		btnBlue.setBackground(Color.BLUE);
		btnBlack.setBackground(Color.BLACK);
		
		MyListener listener = new MyListener();
		
		btnRed.addActionListener(listener);
		btnGreen.addActionListener(listener);
		btnBlue.addActionListener(listener);
		btnBlack.addActionListener(listener);

		pMiddle.add(labelColor);
		pMiddle.add(btnRed);
		pMiddle.add(btnGreen);
		pMiddle.add(btnBlue);
		pMiddle.add(btnBlack);
		this.add(pMiddle);
	}

	@Override
	public void paint(Graphics g) {
		interpreter.interpret(g);
	}
	public static void main(String[] args) {
		new Client().loadFrame();
	}
}
class MyListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		Button btn =null;
		if(e.getSource() instanceof Button){
			btn=(Button) e.getSource();
		}
		DrawUtil.color=btn.getBackground();
	}
	
}
