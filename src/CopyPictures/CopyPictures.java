package CopyPictures;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CopyPictures {

    public static void main(String[] args) throws IOException {
        File file = new File("X:\\Smartwares - Product Content\\PRODUCTS\\");
        File dst = new File("X:\\Smartwares - Product Content\\Product_pictures\\HR_pictures_2\\");
        FileWriter fw = new FileWriter("H:/Logs/CopyHRPictures.log", true);
        BufferedWriter bw = new BufferedWriter(fw);

        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        for (int i = 0; i < directories.length; i++) {
            File src = new File("X:\\Smartwares - Product Content\\PRODUCTS\\" + directories[i] + "\\HR_" + directories[i] + "_2.jpg");
            File dstFile = new File(dst + "\\HR_" + directories[i] + "_2.jpg");
            if ((src.exists() && !dstFile.exists()) || ((new Date(src.lastModified())).after(new Date(new Date().getTime() - (1 * 1000 * 60 * 60 * 24))))) {
                System.out.println(src + " - " + dateFormat.format(src.lastModified()));
                bw.newLine();
                bw.write(dateFormat.format(new Date()));
                bw.newLine();
                bw.write(src.getName());
                InputStream in = new FileInputStream(src);
                OutputStream out = new FileOutputStream(dstFile);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            }
        }
        bw.newLine();
        bw.write("----------------------------------------------");
        bw.flush();
        bw.close();
    }
}
