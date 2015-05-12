import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class View extends JFrame implements ActionListener{
	
	private JButton newNode , evaluateGain , newGraph , newEdge ;
	private Canvas canvas;
	private JPanel buttonsPanel , panel1 , panel2, panel3;
	DrawBoard drawPanel ;
	Log log , loops , paths ; 
	private Border raisedbevel , loweredbevel , compound , blackline;
	ArrayList<Node> nodes ; 
	boolean newNodeIsPressed = false ;
	
	public View(){
		
		this.setTitle("Signal Flow Graph");
		this.setSize(1200, 750);
		this.setLayout(null);
		
		makeBorders();
		nodes = new ArrayList<Node>();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel1.setBounds(150, 500, 540, 750-500);
		panel1.setBorder ( new TitledBorder ( new EtchedBorder (), "Log" ) );
		panel2.setBounds(690, 500, 250, 750-500);
		panel2.setBorder ( new TitledBorder ( new EtchedBorder (), "Paths" ) );
		panel3.setBounds(940, 500, 250, 750-500);
		panel3.setBorder ( new TitledBorder ( new EtchedBorder (), "Loops" ) );
		
		buttonsPanel = new ButtonPanel();
		drawPanel = new DrawBoard();
		log = new Log(40,10);
		loops = new Log(18,10);
		paths = new Log(18,10);
		panel1.add(log.jScrollPane1);
		panel2.add(paths.jScrollPane1);
		panel3.add(loops.jScrollPane1);
		
		buttonsPanel.setBorder(  BorderFactory.createCompoundBorder(blackline, compound) );
		drawPanel.setBorder(  BorderFactory.createCompoundBorder(blackline, compound) );
		
		this.add(buttonsPanel);
		this.add(drawPanel);
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		
		newNode = new JButton("Add Node");
		newNode.setContentAreaFilled(false);
		newGraph = new JButton("new Graph");
		newGraph.setContentAreaFilled(false);
		evaluateGain = new JButton("get gain");
		evaluateGain.setContentAreaFilled(false);
		newEdge = new JButton("new edge");
		newEdge.setContentAreaFilled(false);
		
		drawPanel.getView(this);
		
		newNode.addActionListener(this);
		newGraph.addActionListener(this);
		evaluateGain.addActionListener(this);
		newEdge.addActionListener(this);
		
		newNode.setBounds(10, 10, 130, 50);
		newGraph.setBounds(10, 430 , 130, 200);
		evaluateGain.setBounds(10, 220, 130, 200);
		
		buttonsPanel.add(newNode);
		buttonsPanel.add(newEdge);
		buttonsPanel.add(newGraph);
		buttonsPanel.add(evaluateGain);
	
		
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	void makeBorders(){
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		blackline = BorderFactory.createLineBorder(Color.black);
		compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String pressed = e.getActionCommand();
		if(pressed=="Add Node"){
			drawPanel.setCurrentNode(true);
			drawPanel.setNewEdge(false);
			drawPanel.node1 = null;
			drawPanel.node2 = null;
			drawPanel.node1Checked = false ;
		}
		
		else if(pressed=="new Graph"){
			int f = JOptionPane.showConfirmDialog(drawPanel.getComponentPopupMenu(), "do you realy want to get new graph ??\nby clicking yes current graph will be deleted");
			if(f==0){
				drawPanel.reset();
			}
			
		}
		
		else if(pressed=="get gain"){
			System.out.println("new node");
		}
		
		else if(pressed=="new edge"){
			drawPanel.setCurrentNode(false);
			drawPanel.setNewEdge(true);
			drawPanel.node1 = null;
			drawPanel.node2 = null ;
			drawPanel.node1Checked = false ;
		}
		
		
	}
	
	

}
