package sample;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class fileManage1 implements Serializable
{

    public HashMap<String, ArrayList<String[]>> getHashMap(String userName)
    {
        HashMap<String, ArrayList<String[]>> hashMap = new HashMap<>();
        new File("D:/ghanta123/system/data/").mkdirs();
        try
        {

            File file = new File("D:/ghanta123/system/data/" + userName + "allData.dat");
            file.createNewFile(); // if the file exists do nothing
            long len1 = file.length();
            if (len1 == 0)
            {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:/ghanta123/system/data/" + userName + "allData.dat"));
                System.out.println("YES0");
                HashMap<String, ArrayList<String[]>> hashMap1 = new HashMap<>();
                objectOutputStream.writeObject(hashMap1);
                objectOutputStream.close();
            } else
            {
                System.out.println("*File Found*");
            }


            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:/ghanta123/system/data/" + userName + "allData.dat"));

            hashMap = (HashMap<String, ArrayList<String[]>>) objectInputStream.readObject();
            objectInputStream.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return hashMap;
    }

    public void setHashMap(HashMap<String, ArrayList<String[]>> hashMap, String userName) throws IOException
    {

        ObjectOutputStream objectOutputStream1=new ObjectOutputStream(new FileOutputStream("D:/ghanta123/system/data/" + userName + "allData.dat"));

        objectOutputStream1.writeObject(hashMap);
        objectOutputStream1.close();
    }


    // STRING[] WIDTH,HEIGHT,STYLE,STRING FOR A LABEL
    public void setHashMap1(HashMap<String,ArrayList<String[]>> hashSet, String userName) throws IOException
    {

        ObjectOutputStream objectOutputStream1=new ObjectOutputStream(new FileOutputStream("D:/ghanta123/system/data/" + userName + "allData.dat"));
        HashMap<String,ArrayList<String[]>> hashMap=new HashMap<>();

        objectOutputStream1.writeObject(hashMap);
        objectOutputStream1.close();

    }

}