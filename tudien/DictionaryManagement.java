import java.util.* ;
import java.io.* ;

public class DictionaryManagement
{
    private static ArrayList<Word> dictionary = new ArrayList<>() ; 

    public void insertFromCommandline(Word word)
    {
        System.out.println("Them thanh cong !") ;
        dictionary.add(word) ;
    }

    public void insertFromFile(Word word)
    {
        dictionary.add(word) ;
    }

    public void editFromCommandline(Word word, int chon, int index)
    {
        Scanner sc = new Scanner(System.in) ;

        if(chon == 1)
        {
            System.out.print("Nhap nghia tieng viet moi: ") ;
            word.setWordExplain(sc.nextLine()) ;
        }
        else
        {     
            System.out.print("Nhap nghia tieng anh moi: ") ;
            word.setWordTarget(sc.nextLine());
        }   

        dictionary.set(index, word) ;
        dictionaryExportToFile(0);
        System.out.println("Sua thanh cong !") ;
    }

    public void removeFromCommandline(Word word)
    {
        dictionary.remove(word) ;
        dictionaryExportToFile(0);
        System.out.println("Xoa thanh cong !") ;
    }
    
    public void dictionaryExportToFile(int yc)
    {
        try
        {
            FileWriter f = new FileWriter("dictionaries.txt") ;  
            
            for(Word i:dictionary)
                f.write(i.toString()+"\n") ; 
            
            f.close() ;

            if(yc == 1)         System.out.println("Ghi du lieu ra file thanh cong") ;     
        }
        catch(Exception e)
        {
            if(yc == 1)         System.out.println("Ghi du lieu ra file khong thanh cong") ; 
        }
    }

    public void printDictionary()
    {
        for(Word i:dictionary)
            System.out.println(i) ;
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        DictionaryManagement dicMana = new DictionaryManagement() ;
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
            System.out.print("Ban chon: ");
            chon = sc.nextInt();

            if (chon == 0)      System.out.println("Thoat chuong trinh thanh cong !");     
            else if (chon == 1)
            {
                System.out.println("Nhap theo format sau: ") ;
                System.out.println("Dong 1: Nhap tu tieng Anh") ;
                System.out.println("Dong 2: Nhap tu giai thich sang tieng viet") ;
                sc.nextLine() ;
                dicMana.insertFromCommandline(new Word(sc.nextLine(),sc.nextLine())) ;
            } 
            else if (chon == 2)
            {
                try
                {
                    File f = new File("dictionaries.txt") ;  
                    Scanner sc2 = new Scanner(f) ;
                    
                    while(sc2.hasNextLine())
                    {
                        String line = sc2.nextLine() ;
                        String[] items = line.split("\t") ;       
                        dicMana.insertFromFile(new Word(items[0],items[1])) ;
                    }
        
                    sc2.close(); 
                    System.out.println("Doc file thanh cong !") ; 
                }
                catch(Exception e)
                {
                    System.out.println("Doc file khong thanh cong !") ; 
                }        
            }
            else if (chon == 3) 
            {
                if (dictionary.size() == 0) 
                {
                    System.out.println("Tu dien dang trong");
                    continue;
                }

                String w1 ;
                int flag = 0 ;

                System.out.println("Ban muon sua theo: ") ;
                System.out.println("1. Tu tieng anh      2. Tu tieng viet") ;
                System.out.print("Ban chon: ") ;
                int chon2 = sc.nextInt() ;
                sc.nextLine() ;
                
                if(chon2 == 1)
                {
                    System.out.print("Nhap tu tieng anh ban muon sua: ") ;
                    w1 = sc.nextLine() ;
                    
                    for(int i=0;i<dictionary.size();i++)
                        if(w1.equals(dictionary.get(i).getWordTarget()))
                        {
                            System.out.println("Tim thay tu: ") ;
                            System.out.println(dictionary.get(i)) ;
                            dicMana.editFromCommandline(dictionary.get(i), chon2, i); 
                            flag = 1 ;  
                            break ;                         
                        }
                    
                    if(flag == 0)   System.out.println("Khong tim thay tu trong tu dien !") ;           
                }
                else if(chon2 == 2)
                {
                    System.out.print("Nhap tu tieng viet ban muon sua: ") ;
                    w1 = sc.nextLine() ;
                    
                    for(int i=0;i<dictionary.size();i++)
                        if(w1.equals(dictionary.get(i).getWordExplain()))
                        {
                            System.out.println("Tim thay tu: ") ;
                            System.out.println(dictionary.get(i)) ;
                            dicMana.editFromCommandline(dictionary.get(i), chon2, i); 
                            flag = 1 ;  
                            break ;                         
                        }
                    
                    if(flag == 0)   System.out.println("Khong tim thay tu trong tu dien !") ;   
                }
                else    System.out.println("Yeu cau khong hop le !") ;     
            } 
            else if (chon == 4) 
            {
                if (dictionary.size() == 0) 
                {
                    System.out.println("Tu dien dang trong");
                    continue;
                }

                String w1 ;
                int flag = 0 ;

                System.out.println("Ban muon xoa theo: ") ;
                System.out.println("1. Tu tieng anh      2. Tu tieng viet") ;
                System.out.print("Ban chon: ") ;
                int chon2 = sc.nextInt() ;
                sc.nextLine() ;
                
                if(chon2 == 1)
                {
                    System.out.print("Nhap tu tieng anh ban muon xoa: ") ;
                    w1 = sc.nextLine() ;
                    
                    for(int i=0;i<dictionary.size();i++)
                        if(w1.equals(dictionary.get(i).getWordTarget()))
                        {
                            System.out.println("Tim thay tu: ") ;
                            System.out.println(dictionary.get(i)) ;
                            dicMana.removeFromCommandline(dictionary.get(i)); 
                            flag = 1 ;  
                            break ;                         
                        }
                    
                    if(flag == 0)   System.out.println("Khong tim thay tu trong tu dien !") ;           
                }
                else if(chon2 == 2)
                {
                    System.out.print("Nhap tu tieng viet ban muon xoa: ") ;
                    w1 = sc.nextLine() ;
                    
                    for(int i=0;i<dictionary.size();i++)
                        if(w1.equals(dictionary.get(i).getWordExplain()))
                        {
                            System.out.println("Tim thay tu: ") ;
                            System.out.println(dictionary.get(i)) ;
                            dicMana.removeFromCommandline(dictionary.get(i)); 
                            flag = 1 ;  
                            break ;                         
                        }
                    
                    if(flag == 0)   System.out.println("Khong tim thay tu trong tu dien !") ;   
                }
                else    System.out.println("Yeu cau khong hop le !") ;    

            }
            else if (chon == 5) 
            {
                if (dictionary.size() == 0) 
                {
                    System.out.println("Tu dien dang trong");
                    continue;
                }

                dicMana.printDictionary(); 
            } 
            else if (chon == 6) dicMana.dictionaryExportToFile(1);   
            else                System.out.println("Yeu cau khong hop le !");

        } while (chon != 0);
    }
}