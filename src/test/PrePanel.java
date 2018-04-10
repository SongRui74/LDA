/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author dell-pc
 */
public class PrePanel extends JFrame{
    
    public static final int WIDTH = 500;
    public static final int HEIGHT = 300;
          
    public PrePanel(){
        // 设置窗口的内容面板
        Subpanel pre_result = new Subpanel(); 
        setContentPane(pre_result);
        // 设置窗口标题、大小、退出键
        setTitle("分类结果");
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - WIDTH)/2,
            (Toolkit.getDefaultToolkit().getScreenSize().height - HEIGHT)/2 , WIDTH, HEIGHT);
        setResizable(false);
        setVisible(true);
        
}

class Subpanel extends JPanel{  
    private JButton bO,bI,bD,bS;
    
    protected void paintComponent(Graphics g){  
        
        setBackground(Color.white);
        Classifiertest cls = new Classifiertest();
        int[] distr = cls.StatisticsResult();
        int CenterX,CenterY;  
        int r;  
        CenterX = this.getWidth();  
        CenterY = this.getHeight();  
        r = this.getHeight()/3;  
        super.paintComponent(g);  
        
        int a = 0;
        int b = distr[0]*360/distr[4];
        g.setColor(Color.blue);   
        g.fillArc(CenterX/6, CenterY/6, 2*r, 2*r, a, b);
        
        a = distr[0]*360/distr[4];
        b = distr[1]*360/distr[4];
        g.setColor(Color.red);   
        g.fillArc(CenterX/6, CenterY/6, 2*r, 2*r, a, b); 
        
        a = (distr[0]+distr[1])*360/distr[4];
        b = distr[2]*360/distr[4];
        g.setColor(Color.yellow);   
        g.fillArc(CenterX/6, CenterY/6, 2*r, 2*r, a, b);
        
        a = (distr[0]+distr[1]+distr[2])*360/distr[4];
        b = distr[3]*360/distr[4];
        g.setColor(Color.green);   
        g.fillArc(CenterX/6, CenterY/6, 2*r, 2*r, a, b); 
        
        //图例        
        g.setColor(Color.black);
        g.drawString("综合评价", CenterX/2+110, CenterY/8+110); 
        g.drawString("无效评价", CenterX/2+110, CenterY/8+130); 
        g.drawString("需求评价", CenterX/2+110, CenterY/8+150); 
        g.drawString("具体评价", CenterX/2+110, CenterY/8+170); 
        
        bO = new JButton();
        add(bO);
        bO.setBackground(Color.blue);
        bO.setBounds(CenterX/2+80, CenterY/8+100, 20, 10);
        
        bI = new JButton();
        add(bI);
        bI.setBackground(Color.red);
        bI.setBounds(CenterX/2+80, CenterY/8+120, 20, 10);
        
        bD = new JButton();
        add(bD);
        bD.setBackground(Color.yellow);
        bD.setBounds(CenterX/2+80, CenterY/8+140, 20, 10);
        
        bS = new JButton();
        add(bS);
        bS.setBackground(Color.green);
        bS.setBounds(CenterX/2+80, CenterY/8+160, 20, 10);
        
        bO.addActionListener((ActionEvent e) -> {
            if(e.getSource() == bO){
                ResultPanel resultPanel = new ResultPanel("Overview");
            }
        });
        
        bI.addActionListener((ActionEvent e) -> {
            if(e.getSource() == bI){
                ResultPanel resultPanel = new ResultPanel("Invalid");
            }
        });
        
        bD.addActionListener((ActionEvent e) -> {
            if(e.getSource() == bD){
                ResultPanel resultPanel = new ResultPanel("Demand");
            }
        });
        
        bS.addActionListener((ActionEvent e) -> {
            if(e.getSource() == bS){
                ResultPanel resultPanel = new ResultPanel("Specific");
            }
        });
        
        }
    }  
}