import java.util.ArrayList;
import java.util.Collections;

//the parent class
public class TranspositionCipher {
    protected String key;
    protected StringBuffer encryptedText;
    protected ArrayList<Integer> keyOrder;
    protected String text;

    //generate the key order as number according to the key given
    public final ArrayList<Integer> setKeyOrder(String key){
        ArrayList<Integer> temp = new ArrayList<>();
        //Convert the key into asci values and insert it int the temp
        for(int i=0;i<key.length();i++)
            temp.add(i,(int)key.charAt(i));

        Collections.sort(temp);
        //compare the value with temp and insert it back to keyorder
        for(int i=0;i<key.length();i++)
        {
            for(int j=0;j<key.length();j++)
            {
                if(temp.get(j)==(int)key.charAt(i))
                    keyOrder.add(j);
            }
        }
        return keyOrder;
    }

    protected int small(int n,ArrayList<Integer> keyOrder){
        for(int i=0;i<this.keyOrder.size();i++){
            if(this.keyOrder.get(i)==n)
                return i;
        }
        return -1;
    }

}
