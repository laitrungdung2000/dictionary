import java.util.*;

public class TestCode 
{
    static void printDictionary(ArrayList<Word> dic)
    {
        for(Word i:dic)
            System.out.println(i) ;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();
        DictionaryManagement m = new DictionaryManagement();
        int chon;

        do 
        {
            System.out.println();
            System.out.println("1. Them tu moi tu ban phim");
            System.out.println("2. Them tu moi tu file");
            System.out.println("3. Sua tu");
            System.out.println("4. Xoa tu");
            System.out.println("5. Xem tu dien");
            System.out.println("6. In tu dien ra file");
            System.out.println("0. Thoat chuong trinh");
            System.out.println("Ban chon: ");
            chon = sc.nextInt();

            if (chon == 0)      System.out.println("Thoat chuong trinh thanh cong !");     
            else if (chon == 1) dictionary.setDic(m.insertFromCommandline(dictionary.getDic()));        
            else if (chon == 2) dictionary.setDic(m.insertFromFile(dictionary.getDic()));
            else if (chon == 3) 
            {
                if (dictionary.getDic().size() == 0) 
                {
                    System.out.println("Tu dien dang trong");
                    continue;
                }

                dictionary.setDic(m.editFromCommandline(dictionary.getDic()));
            } 
            else if (chon == 4) 
            {
                if (dictionary.getDic().size() == 0) 
                {
                    System.out.println("Tu dien dang trong");
                    continue;
                }

                dictionary.setDic(m.removeFromCommandline(dictionary.getDic()));
            }
            else if (chon == 5) 
            {
                if (dictionary.getDic().size() == 0) 
                {
                    System.out.println("Tu dien dang trong");
                    continue;
                }

                printDictionary(dictionary.getDic());
            } 
            else if (chon == 6) m.dictionaryExportToFile(dictionary.getDic());   
            else                System.out.println("Yeu cau khong hop le !");

        } while (chon != 0);
    }
}