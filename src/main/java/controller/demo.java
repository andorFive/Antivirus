package controller;

import Util.MybatisUtil;
import Util.timeUtil;
import entity.antivirus;
import org.apache.ibatis.session.SqlSession;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


public class demo extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static ArrayList<File> saveFile =new ArrayList<>();
    private static JTextArea Message = null;

    JButton btn = null;
    JButton btn1 = null;

    JTextField textField = null;
    File filePath;

    public demo() {
        this.setTitle("杀毒小程序");
        // 设置控件
        FlowLayout layout = new FlowLayout();// 布局
        JLabel label = new JLabel("请选择文件夹：");// 标签
        textField = new JTextField(30);// 文本域
        btn = new JButton("浏览");// 按钮
        JLabel label2 = new JLabel("请选择功能：");
        JPanel panel = new JPanel();// 下拉菜单
        JComboBox how = new JComboBox<String>();

        how.addItem("-查找-");
        how.addItem("-杀毒-");
        how.addItem("-保存-");

        btn1 = new JButton("确认");// 按钮

        Message = new JTextArea(20,55);// 显示文本域

        JScrollPane scrollpane = new JScrollPane(Message);
        //滚动条
        scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        // -------------------------------------------------------设置布局------------------------------------------------------------
        this.setBounds(700,300, 500,500);
        layout.setAlignment(FlowLayout.LEFT);
        this.setLayout(layout);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btn.addActionListener(this);//文件路径选择
        this.add(label);
        this.add(textField);
        this.add(btn);
        this.add(label2);
        this.add(panel);
        this.add(how);
        this.add(btn1);
        this.add(scrollpane);
        // ---------------------------------------------------------监听器---------------------------------------------------------------
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//只能选择文件夹
                chooser.showDialog(new JLabel(), "选择");

                File file = chooser.getSelectedFile();
                textField.setText(file.getAbsoluteFile().toString());
                filePath= file.getAbsoluteFile();
            }
        });

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s=(String)how.getSelectedItem();
                if(s=="-查找-") {
                    saveFile.clear();
                    Message.setText("");
                    Message.append("开始查找"+"\r\n");
                    func(filePath);

                }else if(s=="-杀毒-") {

                    delete(saveFile);

                }else if(s=="-保存-"){

                    save();

                }
            }
        });


        Message.append("程序启动"+"\r\n");
    }

    //递归查找文件
    private static void func(File file) {//选择的文件路径
        Thread thread =new Thread(){
            @Override
            public void run() {
                super.run();
                File[] fs = file.listFiles();
                for (File f : fs) {
                    if (f.isDirectory()){//若是目录，则递归打印该目录下的文件
                        func(f);
                    }
                    if (f.isFile()//文件
                            &&f.length()==0//大小为0kb
                            &&f.getName().endsWith(".exe")//后缀为exe
                            &&file.getName().equals(f.getName().substring(0,file.getName().length()))){//上级文件夹名称与文件名称相同
//                        Message.append(Thread.currentThread().getName()+" "+f+"\r\n");
                        Message.append(f+"\r\n");
                        saveFile.add(f);
                    }

                }
            }
        };
        thread.start();
    }

    //删除文件
    private static void delete(ArrayList<File> saveFile) {
        Thread thread =new Thread(){
            @Override
            public void run() {
                super.run();
                int temp=0;
                Message.setText("");
                Message.append("开始杀毒"+"\r\n");
                for(File f:saveFile){
                    f.delete();
                    temp++;
                }
                Message.append("杀毒完成"+"\r\n");
                Message.append("共删除"+temp+"条病毒文件"+"\r\n");
            }
        };
        thread.start();
    }

    //将文件保存到数据库
    private static void save(){
        int temp=0;
        List<antivirus> ant=new ArrayList<>();//批量保存数据库
        Message.setText("");
        Message.append("正在保存到数据库..."+"\r\n");
        SqlSession session = null;
        session = MybatisUtil.getSession();

        for(File f:saveFile){
            antivirus antivirus =new antivirus();
            antivirus.setFileAbsolute(f.getAbsolutePath());
            antivirus.setLen((int)f.length());
            antivirus.setTime(timeUtil.getTime());
            session.insert("dao.antivirusDao.insertFile",antivirus);
            temp++;
        }
        session.commit();
        session.close();
        Message.append("保存成功"+"\r\n");
        Message.append("共保存"+temp+"条数据"+"\r\n");
    }

    public void actionPerformed(ActionEvent e) {

    }


}