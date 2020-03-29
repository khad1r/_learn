package array;

import java.util.ArrayList;
import java.util.Scanner;

public class Array {
    static ArrayList<String[]> array;
    static boolean isSearching = false;
    static String Search;
    static Scanner input;
    
    public static void main(String[] args) {
        array = new ArrayList<String[]>();
        input = new Scanner(System.in);
        System.out.println("190602057 Abdul Kadir Jaelani")

        while (true) {
            mainMenu();
        }
    }

    static void mainMenu() {
        System.out.println("Menu:");
        System.out.println("[1] Tambah Data\t[2] Edit Data\t[3] Hapus Data");
        if(isSearching){
            System.out.print("[4] Kembali\t");
        }else{
            System.out.print("[4] Cari Data\t");
        }
        System.out.print("[0] Keluar\n");
        System.out.println("----------------------------------------------");
        printArray();
        System.out.println("----------------------------------------------");
        System.out.print("Pilih menu> ");
        String selectedMenu = input.nextLine();
        if (selectedMenu.equals("1")) {
            addToArray();
        } else if (selectedMenu.equals("2")) {
            editDataInArray();
        } else if (selectedMenu.equals("3")) {
            deleteDataInArray();
        } else if (selectedMenu.equals("4")) {
            if(isSearching){
                endSearch();
            }else{
                searchDataInArray();
            }
        } else if (selectedMenu.equals("0")) {
            System.exit(0);
        } else {
            System.out.println("Kamu salah pilih menu!");
            backToMenu();
        }
    }

    static void backToMenu() {
        System.out.println("");
        System.out.print("Tekan [Enter] untuk kembali..");
        input.nextLine();
    }

    static void printArray() {
        System.out.print("No\t\tNama\t\tJurusan\n");
        if(isSearching){
            for(int i=0;i<array.size();i++){
                if(array.get(i)[0].contains(Search)||array.get(i)[1].contains(Search)){
                    System.out.print((i+1)+".\t\t"+array.get(i)[0]+"\t\t"+array.get(i)[1]+"\n");
                }
            }
        }else{
            if(array.size()<1){
                System.out.println("\t   <<<<<Data Kosong>>>>>");
            }
            for(int i=0;i<array.size();i++){
                System.out.print((i+1)+".\t\t"+array.get(i)[0]+"\t\t"+array.get(i)[1]+"\n"); 
            }
        }
    }

    static void addToArray() {
        String[] read = new String[2];
        System.out.print("Nama: ");
        read[0] = input.nextLine().toUpperCase();
        System.out.print("Jurusan: ");
        read[1] = input.nextLine().toUpperCase();
        if(!read[0].isEmpty() && !read[1].isEmpty()){
            array.add(read);
            System.out.println("Berhasil ditambah!");
        }else{
            System.out.println("Data Tidak Boleh Kosong");
        }
        backToMenu();
    }

    static void editDataInArray() {
        System.out.print("Pilih No> ");
        int index = Integer.parseInt(input.nextLine());
        index = index-1;
        if (index > array.size()) {
            System.out.println("Kamu memasukan data yang salah!");
        } else {
            String[] read = new String[2];
            System.out.print("Nama: ");
            read[0] = input.nextLine().toUpperCase();
            System.out.print("Jurusan: ");
            read[1] = input.nextLine().toUpperCase();
            if(!read[0].isEmpty() && !read[1].isEmpty()){
                array.set(index, read);
                System.out.println("Berhasil diubah!");
            }else{
                System.out.println("Data Tidak Boleh Kosong");
            }
        }
        backToMenu();
    }

    static void deleteDataInArray() {
        System.out.print("Pilih No> ");
        int index = Integer.parseInt(input.nextLine());
        index = index-1;
        if (index > array.size()) {
            System.out.println("Kamu memasukan data yang salah!");
        } else {
            System.out.println("Kamu akan menghapus:");
            System.out.println(array.get(index)[0]+" dari "+array.get(index)[1]);
            System.out.println("Apa kamu yakin?");
            System.out.print("Jawab (y/t): ");
            String jawab = input.nextLine();
            if (jawab.equalsIgnoreCase("y")) {
                array.remove(index);
            }
        }
        backToMenu();
    }
   
    static void searchDataInArray() {
        isSearching = true;
        System.out.print("Cari> ");
        Search = input.nextLine().toUpperCase();
    }

    static void endSearch() {
         isSearching = false;
         Search = null;
         backToMenu();
    }
}
