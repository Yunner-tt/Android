package com.example.junitfile;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws IOException, ClassNotFoundException {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.junitfile", appContext.getPackageName());
        www(appContext);
        ArrayList<String> res = rrr(appContext);
        for(Object a : res){
            System.out.println(a.toString());
        }
    }
//    @Test  //测试单元测试功能
//    public void testmothed(){
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        Service ss = new Service();
//        System.out.println(ss.methon());
//    }
    public void www(Context context) throws IOException {
        FileOutputStream fos = context.openFileOutput("ttt.txt",Context.MODE_PRIVATE+Context.MODE_APPEND);
        ObjectOutputStream sss = new ObjectOutputStream(fos);
        sss.writeObject("woaini");
        sss.writeObject("sdsdsd");
        sss.flush();
        fos.flush();
        sss.close();
        fos.close();
    }
    public ArrayList<String> rrr(Context context) throws IOException, ClassNotFoundException {
        ArrayList<String> temp = new ArrayList<String>();
        FileInputStream fos = context.openFileInput("ttt.txt");
        ObjectInputStream isr = new ObjectInputStream(fos);
        temp.add((String)isr.readObject());
        System.out.println((int)isr.readObject());
//        while(true){
//            try{
//                temp.add((String)isr.readObject());
//            }catch(EOFException e){
//                break;
//            }catch(NullPointerException ee){
//                continue;
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
        isr.close();
        fos.close();//读取完成后关闭
        return temp;
    }
public void WriteSysFile(Context context, String filename) {
    try {
        FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);//openFileOutput函数会自动创建文件
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write("随便插入点啥");
        osw.flush();
        fos.flush();  //输出缓冲区中所有的内容
        osw.close();
        fos.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public void ReadSysFile(Context context, String filename) {
        try {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            char[] input = new char[fis.available()];  //available()用于获取filename内容的长度,但是对中文有问题，建议使用BufferReader
            isr.read(input);  //读取并存储到input中
            isr.close();
            fis.close();//读取完成后关闭
            String str = new String(input);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}