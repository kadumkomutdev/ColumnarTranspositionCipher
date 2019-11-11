import java.util.ArrayList;
import java.util.Collections;

public class TransCipherEncryptoin {
    private String text;
    private String key;
    private StringBuffer encrpytedText;
    private ArrayList<Integer> keyOrder;

    //constructor accepting only the keyword and plaintext
    public TransCipherEncryptoin(String key,String text) {
        this.text = text;
        this.key = key;
        this.keyOrder = new ArrayList<>();
        this.encrpytedText = new StringBuffer();
        setKeyOrder(key);
        encrypt();
    }

    //encrypt the text
    private final boolean encrypt(){
        //set the rows with respected to the plaintext
        int noOfRows=text.length()%key.length();
        if(noOfRows==0)
            noOfRows = key.length();
        else if(noOfRows<key.length())
            noOfRows = (text.length()/key.length())+1;

        char[][] keyPlaced = new char[noOfRows][key.length()];
        //check if keyOrder is not created or plaintext is null
        //then return false;
        if(keyOrder!=null&&text!=null){
            //inserting the keys
            int l=0;
            for(int i=0;i<noOfRows;i++)
            {
                for(int j=0;j<key.length();j++)
                {
                    if(l<this.text.length())
                    {
                        if(text.charAt(l)==' ')
                            keyPlaced[i][j] = '_';
                        else
                            keyPlaced[i][j] = text.charAt(l);
                        l++;
                    }
                    else
                        keyPlaced[i][j] = '_';
                }
            }
            generateEncryption(keyPlaced,noOfRows,key.length());
            return true;
        }
        return false;
    }

    //generate the encryption of key
    private final void generateEncryption(char[][] keyPlaced,int row,int col){
        for(int i=0;i<keyOrder.size();i++)
        {
            int tempCol = small(i);
            for(int j=0;j<row;j++)
                encrpytedText.append(keyPlaced[j][tempCol]);
        }
        //now check for conditions such as if the encrypted text has double dash
        //if present then convert it to one space
        //ignore if there is only only one dash
        int in= encrpytedText.indexOf("__");
        if(encrpytedText.indexOf("__")!=-1)
            encrpytedText.replace(in,in+2,"?");
    }

    public StringBuffer getEncrpytedText() {
        return encrpytedText;
    }

    private int small(int n){
        for(int i=0;i<keyOrder.size();i++){
            if(keyOrder.get(i)==n)
                return i;
        }
        return -1;
    }

    //generate the key order as number according to the key given
    private final void setKeyOrder(String key){
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
    }

}
