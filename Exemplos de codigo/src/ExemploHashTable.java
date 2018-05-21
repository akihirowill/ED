import java.util.Enumeration;
import java.util.Hashtable;

public class ExemploHashTable {
    public static void main(String[] args) {

        Enumeration nomes;
        String key;

        //criando a hash table
        Hashtable<String,String> hashtable = new Hashtable<String,String>();

        hashtable.put("Chave 1","Maria");
        hashtable.put("Chave 2","Paulo");
        hashtable.put("Chave 3","Joao");
        hashtable.put("Chave 4","Pedro");

        nomes = hashtable.keys();

        while (nomes.hasMoreElements()){
            key = (String) nomes.nextElement();
            System.out.println("Chave: "+key+ " e Valor: "+hashtable.get(key));
        }
    }
}
