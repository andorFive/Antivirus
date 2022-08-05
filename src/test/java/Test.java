import Util.MybatisUtil;
import Util.timeUtil;
import entity.antivirus;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Test implements Runnable {
    //查询全部
    @org.junit.Test
    public void testselectAll() throws Exception {
        System.out.println(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));

    }

    @org.junit.Test
    public void test() {   //文件夹下的可执行程序的名字跟文件夹同名且大小为0kb。
        String path = "D:\\ensp\\模拟病毒素材";		//要遍历的路径
        File file = new File(path);		//获取其file对象
        func(file);
    }

    @org.junit.Test
    public void time(){
        File f =new File("D:\\ensp\\1.txt");
        SqlSession session = null;
        session = MybatisUtil.getSession();
        antivirus antivirus =new antivirus();
        antivirus.setFileAbsolute(f.getAbsolutePath());
        System.out.println(f.getAbsolutePath());
        antivirus.setLen((int)f.length());
        System.out.println(f.length());
        antivirus.setTime(timeUtil.getTime());
        System.out.println(timeUtil.getTime());
        session.insert("dao.antivirusDao.insertFile",antivirus);
        session.commit();
        session.close();
    }


    private static void func(File file) {
        File[] fs = file.listFiles();
        File temp =file;//保存上级目录
        for (File f : fs) {
            if (f.isDirectory()){//若是目录，则递归打印该目录下的文件
                func(f);
            }
            if (f.isFile()//文件
                    &&f.length()==0//大小为0kb
                    &&f.getName().endsWith(".exe")//后缀为exe
                    &&file.getName().equals(f.getName().substring(0,file.getName().length()))){//上级文件夹名称与执行文件的名称相同

                System.out.println("文件名："+f+"  文件大小:"+f.length()+"kb");
            }

        }
    }
    @org.junit.Test
    public void test3(){
//        Test my =new Test();
//        my.start();//启动线程
        Test myrun =new Test();
        Thread t =new Thread(myrun);//将任务放入线程中
        Thread t2 =new Thread(myrun);//将任务放入线程中
        t.start();//启动线程
        t2.start();//启动线程
    }

    @Override
    public void run() {
        method();

    }



    ReentrantLock lock =new ReentrantLock();

    private synchronized void method() {
        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName()+": "+i);
        }
    }

    @org.junit.Test
    public void test6(){
        int[][] a={{1,2},{3,4,5},{6,7,8,9}};
//        System.out.println(a[0][1]);
//        System.out.println(a[a.length-1][3]);
        System.out.println(a[1][3]);
//        System.out.println(a[2][3]);
    }

    @org.junit.Test
    public void test7(){
        int a=1;
        int b=1;
        a+=++b-a;
        System.out.println(a);
        System.out.println(b);

        String $persons;
        String teoNum;
        String _童话里;
//        String *point;

//        ArrayList m =new Object();
        List m = new ArrayList();
//        ArrayList my =new List();
//        List mylist =new List();
    }

    public static void method1(){}

    int method(int x,int y){
        return 1;
    }

    private void methos(){}

//    public default float meth(float d){
//        return 2.0;
//    }

    @org.junit.Test
    public void test8(){

        int a=1;
        int b=1;
        int c=1;
        if(a--==1&&1<++b||c--==1){
            System.out.println(c);
            System.out.println("####");
        }
        System.out.println("a="+a+"b="+b+"c="+c);
    }

    @org.junit.Test
    public void test9(){

        int a=1;
        int b=1;
        int c=1;
        if(a--==1&&1<++b||c--==1){
            System.out.println(c);
            System.out.println("####");
        }
        System.out.println("a="+a+"b="+b+"c="+c);
        System.out.println(" yea");
    }




}
