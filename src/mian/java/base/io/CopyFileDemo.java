package mian.java.base.io;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;

public class CopyFileDemo {
    public static void main(String[] args) {
        //获取指定resource路径的文件
       File file =  getResourceFileByPath("template/abc.txt");

       if(!file.exists()){
           try {
               file.createNewFile();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }else{
           System.out.println(file.getAbsolutePath());
       }
        //将文件移动另一个文件里面
        transferAnotherPath(file, "template/bcd.txt");
    }

    private static void transferAnotherPath(File file, String s) {
        InputStream fileInputStream = null;
        OutputStream os = null;
        try {
            fileInputStream = new FileInputStream(file);
            File outFile = createFile(s);
            System.out.println(outFile.getAbsolutePath());
            os = new FileOutputStream(outFile);

            byte[] bs = new byte[1024];
            int idx;
            while((idx = fileInputStream.read(bs)) != -1){
                os.write(bs, 0, idx);
                os.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static File createFile(String s) {
        File resourceFileByPath = getResourceFileByPath(s);
        if(!resourceFileByPath.exists()){
            try {
                resourceFileByPath.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resourceFileByPath;
    }

    private static File getResourceFileByPath(String path) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        if(url == null){
            throw new RuntimeException("指定路径的文件不存在");
        }
        String path1 = null;//解决中英文乱码
        try {
            path1 = URLDecoder.decode(url.getPath(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        File file = new File(path1);
        return file;
    }
}
