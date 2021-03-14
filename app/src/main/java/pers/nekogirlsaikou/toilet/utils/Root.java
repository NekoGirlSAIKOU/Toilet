package pers.nekogirlsaikou.toilet.utils;

import java.io.DataOutputStream;
import java.io.File;

public class Root {
    public static boolean isRootedPhone(){
        try {
            File file1 = new File ("/ststem/bin/su");
            File file2 = new File ("/system/xbin/su");
            return file1.exists () || file2.exists ();
        } catch (Exception e){
            return false;
        }
    }

    public static boolean runRootCommand (String command){
        System.out.println("excute as root:"+command);
        Process process = null;
        DataOutputStream os = null;

        try {

            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();

        } catch (Exception e) {
            return false;

        } finally {

            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }

        return true;

    }
}
