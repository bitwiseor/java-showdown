import java.util.*;
import java.io.*;

public class RandomTeamMaker {
  
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java RandomTeamMaker [filepath]");
      System.exit(0);
    }
    
    Scanner in = null;
    
    try {
      in = new Scanner(new FileInputStream(args[0]));
    } catch (FileNotFoundException e) {
      System.out.println("Unable to access input file: " + args[0]);
      System.exit(1);
    }
    
    ArrayList<String> pokemon = new ArrayList<String>();
    int count = 0;
    
    while(in.hasNextLine()) {
      if(pokemon.size() == count) {
        pokemon.add(in.nextLine());
      } else {
        String mon = in.nextLine();
        if(mon.isEmpty()) {
          count++;
        } else {
          pokemon.set(count, pokemon.get(count) + "\n" + mon);
        }
      }
    }
    
    in.close();
    
    ArrayList<String> team = new ArrayList<String>();
    ArrayList<String> mons = new ArrayList<String>();
    ArrayList<String> items = new ArrayList<String>();
    
    Random rand = new Random();
    
    while(team.size() < 6) {
      int index = rand.nextInt(pokemon.size());
      String checkMon = pokemon.get(index);
      Scanner itemScan = new Scanner(checkMon);
      String itemString = itemScan.nextLine();
      String[] nameAndItem = itemString.split("@");
      nameAndItem[1] = nameAndItem[1].trim();
      nameAndItem[0] = nameAndItem[0].trim();
      if(items.contains(nameAndItem[1]) || mons.contains(nameAndItem[0])) {
        itemScan.close();
        continue;
      } else {
        items.add(nameAndItem[1]);
        mons.add(nameAndItem[0]);
        team.add(checkMon);
        itemScan.close();
      }
    }
    
    for(String member : team) {
      System.out.println(member + "\n");
    }
    
  }
}