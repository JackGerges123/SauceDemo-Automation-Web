package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FilesUtils {

    private FilesUtils(){super();}

    public static void deleteFiles(File dirPath){
        if(dirPath == null || !dirPath.exists()){
            LogsUtil.warn("Directory doesnot exist" + dirPath);
            return;
        }

        File[] filelist = dirPath.listFiles();
        if(filelist==null){
            LogsUtil.warn("Failed to list files in " + dirPath);
            return;
        }

        for (File file : filelist){
            if (file.isDirectory()){
                deleteFiles(file);
            }else {
                try {
                    Files.delete(file.toPath());
                }catch (IOException e){
                    LogsUtil.warn("Failed to delete file" + file);
                }
            }
        }
    }
}
