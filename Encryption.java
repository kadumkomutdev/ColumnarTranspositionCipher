import java.util.ArrayList;

public class Encryption extends TranspositionCipher {

    //constructor accepting only the keyword and plaintext
    public Encryption(String key, String text) {
        this.text = text;
        this.key = key.toUpperCase();
        this.keyOrder = new ArrayList<>();
        this.encryptedText = new StringBuffer();
        startEncryption();
    }

    private void startEncryption(){
        keyOrder = setKeyOrder(key);
        encrypt();
    }

    //encrypt the text
    private boolean encrypt(){
        //set the rows with respected to the plaintext
        int noOfRows=text.length()%key.length();
        if(noOfRows==0) noOfRows = text.length()/key.length();
        else if(noOfRows<key.length()) noOfRows = (text.length()/key.length())+1;

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
            generateEncryption(keyPlaced,noOfRows);
            return true;
        }
        return false;
    }

    //generate the encryption of key
    private void generateEncryption(char[][] keyPlaced,int row){
        for(int i=0;i<keyOrder.size();i++)
        {
            int tempCol = small(i,keyOrder);
            for(int j=0;j<row;j++)
                encryptedText.append(keyPlaced[j][tempCol]);
        }
        //now check for conditions such as if the encrypted text has double dash
        //if present then convert it to one space
        //ignore if there is only only one dash
        int in= encryptedText.indexOf("__");
        if(encryptedText.indexOf("__")!=-1)
            encryptedText.replace(in,in+2,"?");
    }

    public StringBuffer getEncryptedText() {
        return encryptedText;
    }

}
