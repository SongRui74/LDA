/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import weka.classifiers.Classifier; 
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.*;
import weka.core.converters.*;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;
/**
 *
 * @author dell-pc
 */
public class Classifiertest {
    
    public JTextArea txtJ48 = new JTextArea();
    public JTextArea txtNB = new JTextArea();
    
    public void TestJ48() throws Exception{
        
        //从数据库读取数据文件
        InstanceQuery query = new InstanceQuery();
        query.setUsername("song");
        query.setPassword("123456");
        query.setQuery("select * from test50");
        txtJ48.append("数据库连接成功！\n");
        Instances data = query.retrieveInstances();
        
        //数据预处理
        //remove无用的文本特征
        String[] re_option = new String[2];
        re_option[0] = "-R";
        re_option[1] = "1-5";
        Remove remove = new Remove();
        remove.setOptions(re_option);
        remove.setInputFormat(data);
        Instances newdata = Filter.useFilter(data, remove);        
        //转化类型
        NumericToNominal transtype = new NumericToNominal();
        String[] ty_option = new String[2];
        ty_option[0] = "-R";
        ty_option[1] = "first-last";
        transtype.setOptions(ty_option);        
        transtype.setInputFormat(newdata);
        newdata = Filter.useFilter(newdata, transtype);  
        
        Classifier J48 = new J48();    
        Instances d_Train = newdata;
        Instances d_Test = newdata;           
        
        d_Train.setClassIndex(0); 
        d_Test.setClassIndex(0); //设置分类属性所在行号（第一行为0号）
        
        J48.buildClassifier(d_Train);//训练
        Evaluation eval = new Evaluation(d_Train);
        eval.evaluateModel(J48, d_Test);//测试
        System.out.println(eval.toSummaryString("\n=== Summary ===\n",false));
        System.out.println(eval.toClassDetailsString());
        System.out.println(eval.toMatrixString());
        
        txtJ48.append("Classifier model:\tJ48\n");
        txtJ48.append(eval.toSummaryString("\n=== Summary ===\n",false)+"\n");
        txtJ48.append(eval.toClassDetailsString()+"\n");
        txtJ48.append(eval.toMatrixString()+"\n");
    
    }
    
    public void TestCluster() throws Exception{
        //从数据库读取数据文件
        InstanceQuery query = new InstanceQuery();
        query.setUsername("song");
        query.setPassword("123456");
        query.setQuery("select * from test50");
        Instances data = query.retrieveInstances();
        //实现聚类
        String[] option=new String[6];  //设置相应的参数  
        option[0]="-N"; //聚类数  
        option[1]="4";  
        option[2]="-A"; //距离算法
        option[3]="weka.core.EuclideanDistance";
        option[4]="-I"; //最大迭代次数  
        option[5]="500"; 
        SimpleKMeans kmeans = new SimpleKMeans(); // new instance of clusterer  
        kmeans.setOptions(option); // set the options  
        kmeans.buildClusterer(data); // build the clusterer 
        
        //评价聚类，使用ClusterEvaluation 
        ClusterEvaluation eval = new ClusterEvaluation();  
        eval.setClusterer(kmeans);  
        eval.evaluateClusterer(new Instances(data));  
        System.out.println(eval.clusterResultsToString()); 
    }
    
    //C4.5算法
    public void J48test() throws IOException, Exception{
        Classifier J48 = new J48();  
        File inputFile = new File("D:\\Program Files\\Weka-3-8\\data\\weather.numeric.arff");//训练语料文件  
        ArffLoader atf = new ArffLoader();   
        atf.setFile(inputFile);  
        Instances d_Train = atf.getDataSet(); // 读入训练文件        
        inputFile = new File("D:\\Program Files\\Weka-3-8\\data\\weather.numeric.arff");//测试语料文件  
        atf.setFile(inputFile);            
        Instances d_Test = atf.getDataSet(); // 读入测试文件
        
        d_Train.setClassIndex(0); 
        d_Test.setClassIndex(0); //设置分类属性所在行号（第一行为0号）
        
        J48.buildClassifier(d_Train);//训练
        Evaluation eval = new Evaluation(d_Train);
        eval.evaluateModel(J48, d_Test);//测试
        System.out.println(eval.toSummaryString("\n=== Summary ===\n",false));
        System.out.println(eval.toClassDetailsString());
        System.out.println(eval.toMatrixString());
        
        txtJ48.append("Classifier model:\tJ48\n");
        txtJ48.append(eval.toSummaryString("\n=== Summary ===\n",false)+"\n");
        txtJ48.append(eval.toClassDetailsString()+"\n");
        txtJ48.append(eval.toMatrixString()+"\n");
    }
    //读取C4.5算法结果
    public String getJ48Result() throws Exception{
    //    J48test();
        this.TestJ48();
        String str = txtJ48.getText();
        return str;
    }
    //朴素贝叶斯算法    
    public void NaiveBayestest() throws IOException, Exception{
        Classifier NB = new NaiveBayes();  
        File inputFile = new File("D:\\Program Files\\Weka-3-8\\data\\weather.numeric.arff");//训练语料文件  
        ArffLoader atf = new ArffLoader();   
        atf.setFile(inputFile);  
        Instances d_Train = atf.getDataSet(); // 读入训练文件  
        d_Train.setClassIndex(0);   //设置分类属性所在行号（第一行为0号）      
        Evaluation eval = new Evaluation(d_Train);
        eval.crossValidateModel(NB, d_Train, 10, new Random(1));//交叉验证，fold为10 
        
        System.out.println(eval.toSummaryString("\n=== Summary ===\n",false));
        System.out.println(eval.toClassDetailsString());
        System.out.println(eval.toMatrixString());
        
        txtNB.append("Classifier model:\tNavieBayes\n");
        txtNB.append(eval.toSummaryString("\n=== Summary ===\n",false)+"\n");
        txtNB.append(eval.toClassDetailsString()+"\n");
        txtNB.append(eval.toMatrixString()+"\n");
    }
    //读取朴素贝叶斯算法结果
    public String getNavieBayesResult() throws Exception{
        NaiveBayestest();
        String str = txtNB.getText();
        return str;
    }
}
