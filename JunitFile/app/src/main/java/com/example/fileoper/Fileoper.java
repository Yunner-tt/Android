package com.example.fileoper;

import android.content.Context;

import com.example.changIO.MyObjectOutputStream;
import com.example.vo.Employ;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Fileoper {
    private static String filename = "employee.dat";


    public static boolean WriteSysFile(Context context,Object obj) {
        File file = new File(context.getFilesDir()+"/"+filename);
        try {
            FileOutputStream fos = context.openFileOutput(filename,Context.MODE_PRIVATE+Context.MODE_APPEND);//openFileOutput函数会自动创建文件
            ObjectOutputStream objOut = MyObjectOutputStream.newInstance(file,fos);
            objOut.writeObject(obj);
            objOut.flush();
            fos.flush();  //输出缓冲区中所有的内容
            objOut.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static ArrayList<Employ> ReadSysFile(Context context) {
        ArrayList<Employ> temp = new ArrayList<Employ>();
        try {
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream isr = new ObjectInputStream(fis);
            while(true){
                try{
                    temp.add((Employ)isr.readObject());
                }catch(EOFException e){
                    break;
                }catch(NullPointerException ee){
                    continue;
                }
            }
            isr.close();
            fis.close();//读取完成后关闭
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
    public static void clear(Context context) throws IOException {
        FileOutputStream fos = context.openFileOutput("employee.dat",Context.MODE_PRIVATE);
        fos.write("".getBytes());
        fos.flush();
        fos.close();
    }
}
