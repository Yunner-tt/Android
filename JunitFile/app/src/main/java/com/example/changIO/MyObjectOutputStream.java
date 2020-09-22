package com.example.changIO;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * 此类继承ObjectOutputStream，重写writeStreamHeader()方法,以实现追加写入时去掉头部信息
 */
public class MyObjectOutputStream extends ObjectOutputStream {
    private static File f;

    // writeStreamHeader()方法是在ObjectOutputStream的构造方法里调用的
    // 由于覆盖后的writeStreamHeader()方法用到了f。如果直接用此构造方法创建
    // 一个MyObjectOutputStream对象，那么writeStreamHeader()中的f是空指针
    // 因为f还没有初始化。所以这里采用单态模式
    public  MyObjectOutputStream(OutputStream out, File f) throws IOException, SecurityException {
        super(out);
    }

    // 返回一个MyObjectOutputStream对象，这里保证了new MyObjectOutputStream(out, f)
    // 之前f已经指向一个File对象
    public static MyObjectOutputStream newInstance(File file, OutputStream out) throws IOException {
        f = file;// 本方法最重要的地方：构建文件对象，两个引用指向同一个文件对象
        return new MyObjectOutputStream(out, f);
    }

    @Override
    public void writeStreamHeader() throws IOException {
        // 文件不存在或文件为空,此时是第一次写入文件，所以要把头部信息写入。
        if (!f.exists() || (f.exists() && f.length() == 0)) {
            super.writeStreamHeader();
        } else {
            // 不需要做任何事情
        }
    }
}