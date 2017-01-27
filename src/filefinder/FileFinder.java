package filefinder;

import java.io.*;
import java.util.*;

class FileFinder {

    static String ConvertFilesToString(File St) {
        String strObj = St.getPath();
        return strObj;
    }

    public static void main(String[] args) {
        int i;
        int Flag = 1;
        File strObj = new File(".");
        String FileName = "user.dat";
        File[] Arry = strObj.listRoots();

        Stack stack1 = new Stack();

        System.out.println("Please Wait...");

        for (int a = 0; a < Arry.length && Flag == 1; a++) {

            if (Arry[a].exists()) {

                String Str1 = Arry[a].getPath();
                File O = new File(Str1);
                File[] Arr = O.listFiles();

                for (i = 0; i < Arr.length; i++) {
                    if (Arr[i].isDirectory() && Arr[i].canRead()) {
                        stack1.push(Arr[i]);
                    } else {
                        if (Arr[i].isFile()) {
                            String StringObj = Arr[i].getName();
                            if (FileName.equalsIgnoreCase(StringObj) && Arr[i].canRead()) {
                                Flag = 0;
                                System.out.println("File Found");
                                System.out.println(Arr[i].getPath());
                            }
                        }

                    }
                }

                while (!stack1.empty() && Flag == 1) {
                    try {
                        O = new File(ConvertFilesToString((File) stack1.pop()));
                        Arr = O.listFiles();

                        for (i = 0; i < Arr.length; i++) {
                            if (Arr[i].isDirectory() && Arr[i].canRead()) {
                                stack1.push(Arr[i]);
                            }
                            if (Arr[i].isFile() && Arr[i].canRead()) {
                                String StringObj = Arr[i].getName();
                                if (FileName.equalsIgnoreCase(StringObj)) {
                                    Flag = 0;
                                    System.out.println("File Found");
                                    System.out.println(Arr[i].getPath());
                                }
                            }

                        }
                    }
                    catch (Exception e) {
                    }

                }
            }
        }

        if (Flag == 1) {
            System.out.println("File " + FileName + " Does not Exists");
        }
    }
}
