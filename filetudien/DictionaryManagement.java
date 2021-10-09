import java.util.* ;
import java.io.* ;

public class DictionaryManagement
{
    public ArrayList<Word> insertFromCommandline(ArrayList<Word> dic)
    {
        Scanner sc = new Scanner(System.in) ;
        String line1, line2 ;

        System.out.print("Nhap so luong tu vung muon them vao: ") ;
        int n = sc.nextInt() ;
        sc.nextLine() ;

        System.out.println("Nhap theo format sau: ") ;
        System.out.println("Dong 1: Nhap tu tieng Anh") ;
        System.out.println("Dong 2: Nhap tu giai thich sang tieng viet") ;
        for(int i=0;i<n;i++)
        {
            System.out.println("Nhap tu thu " + (i+1) + ": ") ;
            
            line1 = sc.nextLine() ;
            line2 = sc.nextLine() ;

            dic.add(new Word(line1, line2)) ;
        }

        return dic ;
    }

    public ArrayList<Word> insertFromFile(ArrayList<Word> dic)
    {
        try
        {
            File f = new File("dictionaries.txt") ;  
            Scanner sc = new Scanner(f) ;
            
            while(sc.hasNextLine())
            {
                String line = sc.nextLine() ;
                String[] items = line.split("\t") ;
                dic.add(new Word(items[0], items[1])) ;
            }

            sc.close(); 
            System.out.println("Doc file thanh cong !") ; 
        }
        catch(Exception e)
        {
            System.out.println("Doc file khong thanh cong !") ; 
        }

        return dic ;
    }

    public ArrayList<Word> editFromCommandline(ArrayList<Word> dic)
    {
        Scanner sc = new Scanner(System.in) ;
        String w, w1 ;

        System.out.println("Ban muon sua theo: ") ;
        System.out.println("1. Tu tieng anh      2. Tu tieng viet") ;
        System.out.print("Ban chon: ") ;
        int chon = sc.nextInt() ;
        sc.nextLine() ;

        if(chon == 1)
        {
            System.out.print("Nhap tu tieng anh ban muon sua: ") ;
            w = sc.nextLine() ;

            for(Word i:dic)
                if(i.getWordTarget().equals(w))
                {
                    System.out.println("Tim thay tu: ") ;
                    System.out.println(i) ;
                    System.out.print("Nhap nghia tieng viet moi: ") ;
                    w1 = sc.nextLine() ;
                    i.setWordExplain(w1) ;
                    System.out.println("Sua thanh cong !") ;
                    return dic ;
                }
            
            System.out.println("Khong tim thay tu trong tu dien !") ;
        }
        else if(chon == 2)
        {
            System.out.print("Nhap tu tieng viet ban muon sua: ") ;
            w = sc.nextLine() ;

            for(Word i:dic)
                if(i.getWordExplain().equals(w))
                {
                    System.out.println("Tim thay tu: ") ;
                    System.out.println(i) ;
                    System.out.print("Nhap nghia tieng anh moi: ") ;
                    w1 = sc.nextLine() ;
                    i.setWordTarget(w1);
                    System.out.println("Sua thanh cong !") ;
                    return dic ;
                }
            
            System.out.println("Khong tim thay tu trong tu dien !") ;
        }
        else
            System.out.println("Yeu cau khong hop le !") ;

        return dic ;
    }

    public ArrayList<Word> removeFromCommandline(ArrayList<Word> dic)
    {
        Scanner sc = new Scanner(System.in) ;
        String w ;

        System.out.println("Ban muon xoa theo: ") ;
        System.out.println("1. Tu tieng anh      2. Tu tieng viet") ;
        System.out.print("Ban chon: ") ;
        int chon = sc.nextInt() ;
        sc.nextLine() ;

        if(chon == 1)
        {
            System.out.print("Nhap tu tieng anh ban muon xoa: ") ;
            w = sc.nextLine() ;

            for(Word i:dic)
                if(i.getWordTarget().equals(w))
                {
                    System.out.println("Tim thay tu: ") ;
                    System.out.println(i) ;
                    dic.remove(i) ;
                    System.out.println("Xoa thanh cong !") ;
                    return dic ;
                }
            
            System.out.println("Khong tim thay tu trong tu dien !") ;
        }
        else if(chon == 2)
        {
            System.out.print("Nhap tu tieng viet ban muon xoa: ") ;
            w = sc.nextLine() ;

            for(Word i:dic)
                if(i.getWordExplain().equals(w))
                {
                    System.out.println("Tim thay tu: ") ;
                    System.out.println(i) ;
                    dic.remove(i) ;
                    System.out.println("Sua thanh cong !") ;
                    return dic ;
                }
            
            System.out.println("Khong tim thay tu trong tu dien !") ;
        }
        else
            System.out.println("Yeu cau khong hop le !") ;

        return dic ;
    }
    
    public void dictionaryExportToFile(ArrayList<Word> dic)
    {
        try
        {
            FileWriter f = new FileWriter("dictionaries.txt") ;  
            
            for(Word i:dic)
                f.write(i.toString()+"\n") ; 
            
            f.close() ;
            System.out.println("Ghi du lieu ra file thanh cong") ; 
        }
        catch(Exception e)
        {
            System.out.println("Ghi du lieu ra file khong thanh cong") ; 
        }
    }
}