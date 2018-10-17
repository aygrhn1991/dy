package com.dy.service;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class DbBackupHandler {
//    public void backup() {
//        try {
//            Runtime rt =Runtime.getRuntime(); //返回与当前的Java应用程序的运行时对象
//            // 调用 调用mysql的安装目录的命令
//            Process child = rt.exec(sqlurl);
//            // 设置导出编码为utf-8。这里必须是utf-8
//            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
//            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
//            InputStreamReader xx = new InputStreamReader(in, "utf-8");
//            // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
//            String inStr;
//            StringBuffer sb = new StringBuffer("");
//            String outStr;
//            // 组合控制台输出信息字符串
//            BufferedReader br = new BufferedReader(xx);
//            while ((inStr = br.readLine()) != null) {
//                sb.append(inStr + "\r\n");
//            }
//            outStr = sb.toString();
//            // 要用来做导入用的sql目标文件：
//            FileOutputStream fout = new FileOutputStream(path);
//            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
//            writer.write(outStr);
//            writer.flush();
//            in.close();
//            xx.close();
//            br.close();
//            writer.close();
//            fout.close();
//            System.out.println("");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
public  boolean exportDatabaseTool(String hostIP, String userName, String password, String savePath, String fileName, String databaseName) throws InterruptedException {
    File saveFile = new File(savePath);
    if (!saveFile.exists()) {// 如果目录不存在
        saveFile.mkdirs();// 创建文件夹
    }
    if(!savePath.endsWith(File.separator)){
        savePath = savePath + File.separator;
    }

    PrintWriter printWriter = null;
    BufferedReader bufferedReader = null;
    try {
        printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));
        Process process = Runtime.getRuntime().exec(" C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump -h" + hostIP + " -u" + userName + " -p" + password + " --set-charset=UTF8 " + databaseName);
        InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
        bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        while((line = bufferedReader.readLine())!= null){
            printWriter.println(line);
        }
        printWriter.flush();
        if(process.waitFor() == 0){//0 表示线程正常终止。
            return true;
        }
    }catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (printWriter != null) {
                printWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return false;
}
}
