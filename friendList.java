import java.util.ArrayList;
import java.util.Scanner;

public class friendList {

    private String str;
    private int count=0;
    public Scanner scan = new Scanner(System.in);
    private ArrayList<String> friendList = new ArrayList<>();
    public ArrayList<String> getName(){
        return friendList;
    }
    public void listen(){
        System.out.println("Please tell me what you wanna do?");
        str = scan.nextLine();
        String[] parts = str.split("#");
        while(!parts[0].equals("close")){

            if(parts[0].equals("add")){
                if(!checkList(parts[1],friendList)){
                    friendList.add(parts[1]);
                    System.out.println("Your have added " + friendList.get(count));
                    count++;
                    System.out.println("Your Friendlist");
                    for(String name: friendList){
                        System.out.println(name);
                    }
                }
                else{
                    System.out.println("You have added this friend!");
                }

            }
            else if(parts[0].equals("remove")){
                friendList.remove(parts[1]);
                System.out.println("You removed "+parts[1]+ " out of your friendlist.");
                System.out.println("Your Friendlist");
                for(String name: friendList){
                    System.out.println(name);
                }
            }
            else{
                System.out.println("Your instruction is error, please try again.");
            }
            System.out.println("Please tell me what you wanna do?");
            str = scan.nextLine();
            parts = str.split("#");
        }
        System.out.println("Close the list.");
    }


    public boolean checkList(String name, ArrayList<String> list){
        for (String s : list) {
            if (name.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
