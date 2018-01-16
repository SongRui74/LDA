/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author dell-pc
 */
public class MyPanel extends JFrame implements ActionListener
{
       /**
     * @param args the command line arguments
     */
    private JButton button_j48;
    private JButton button_NB;
    private JButton button_clear;
    private JTextArea txtArea;
    private JLabel label;
    private JScrollPane js;
    
    public MyPanel(){
        //设置按钮
        button_j48 = new JButton("J48分析");
        button_NB = new JButton("NaiveBayes分析");
        button_clear = new JButton("清空");
        txtArea = new JTextArea(10,40);
        label = new JLabel("分析结果");
        js = new JScrollPane(txtArea);
        
        //布局
        setTitle("Classifier");
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 500)/2,
            (Toolkit.getDefaultToolkit().getScreenSize().height - 300)/2 , 500, 300);
        js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        setLayout(new FlowLayout());
        add(label);
        add(js);
        add(button_j48);
        add(button_NB);
        add(button_clear);
        setResizable(false);
        setVisible(true);
        
        //监听
        this.button_j48.addActionListener(this);
        this.button_NB.addActionListener(this);
        this.button_clear.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.button_j48)
        {
            Classifiertest test = new Classifiertest();
            try {
                this.txtArea.append(test.getJ48Result());
                this.txtArea.append("================================================\n");
            } catch (Exception ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == this.button_NB)
        {
            Classifiertest test = new Classifiertest();
            try {
                this.txtArea.append(test.getNavieBayesResult());
                this.txtArea.append("================================================\n");
            } catch (Exception ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(e.getSource() == this.button_clear)
        {
            this.txtArea.setText(null);
        }
    }
}