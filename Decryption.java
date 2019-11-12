import java.util.ArrayList;

public class Decryption extends TranspositionCipher {
    public Decryption(String key, StringBuffer encryptedText) {
        this.key = key.toUpperCase();
        this.encryptedText = encryptedText;
        this.keyOrder = new ArrayList<>();
        startDecryption();
    }

    private void startDecryption(){
        this.keyOrder = setKeyOrder(key);
        this.text = decrypt();
    }

    public void getText(){
        text = text.replaceAll("_"," ");
        System.out.println("The Decrypted text for '"+this.encryptedText+"' : "+ text);
    }

    public String decrypt(){
        int row = encryptedText.length()%key.length();

        if(row==0) row = encryptedText.length()/key.length();
        else if(row<key.length()) row = (encryptedText.length()/key.length())+1;

        char keyPlaced[][] = new char[row][key.length()];
        //check for the basic condition
        //checking for two dash lines
        if(encryptedText.indexOf("?")!=-1){
            encryptedText.replace(encryptedText.indexOf("?"),encryptedText.indexOf("?")+1,"__");
        }
        int l=0;
        for(int i=0;i<keyOrder.size();i++)
        {
            int tempCol = small(i,keyOrder);
            for(int j=0;j<row;j++){
                if(l>=encryptedText.length()) break;
                keyPlaced[j][tempCol] = encryptedText.charAt(l);
                l++;
            }
            if(l>=encryptedText.length()) break;
        }
        String s= "";
        for(int i=0;i < row;i++){
            for(int j=0; j < key.length();j++){
                s = s + keyPlaced[i][j];
            }
        }
        return s;
    }
}
