package test;

import com.Thorn.model.user;
import org.apache.log4j.Logger;

public class fileload {
    public static void main(String[] args) {
         String []strings=new String[]{"0","1"};
        System.out.println(iscollection(strings,1));
    }
    public static  int iscollection(String []collections,int id){
        int index=0;
        for(index=0;index<collections.length;index++){
            System.out.println(collections[index]);
            if(collections[index].equals(String.valueOf(id)))
            {
                System.out.println("check");
                return index;
            }
        }
        return 0;
    }
}
