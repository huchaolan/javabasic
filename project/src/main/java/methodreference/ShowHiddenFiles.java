package methodreference;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

public class ShowHiddenFiles {

    public static void main(String[] args) {
        List<File> hiddenList = getHiddenList1();
        System.out.println(hiddenList);
    }
    
    /**
     * 使用常规方法
     * @return
     */
    public static List<File> getHiddenList(){
        File[] hiddenFiles = new File("D:\\javaproject\\apache-tomcat-8.5.34\\webapps").listFiles(new FileFilter(){
            public boolean accept(File file) {
                return file.isHidden();
            }
        });
        return Arrays.asList(hiddenFiles);
    }
    
    public static List<File> getHiddenList1(){
        return Arrays.asList( new File("D:\\javaproject\\apache-tomcat-8.5.34\\webapps")
                    .listFiles(File::isHidden));
    }
}
