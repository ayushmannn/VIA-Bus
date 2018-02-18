import java.util.ArrayList;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URISyntaxException;


public class ScheduleList
{
   private ArrayList<Schedule> scheduleList;
   private static final long serialVersionUID = 6529685098267757690L;
   // -----------------------------------------------------------

   public ScheduleList()
   {
      scheduleList = new ArrayList<Schedule>();
   }

   public ScheduleList(ArrayList<Schedule> scheduleList)
   {
      this.scheduleList = scheduleList;
   }

   // -----------------------------------------------------------
   public ArrayList<Schedule> getScheduleList()
   {
      return scheduleList;
   }

   public void setScheduleList(ArrayList<Schedule> scheduleList)
   {
      this.scheduleList = scheduleList;
   }

   // -----------------------------------------------------------
   public void addSchedule(Schedule schedule)
   {
      scheduleList.add(schedule);
   }

   public void removeSchedule(int index)
   {
      scheduleList.remove(index);
   }

   public int getSize()
   {
      return scheduleList.size();
   }

   // -----------------------------------------------------------
   public void writeToFile()
   {
      try
      {
         // write object to file
         Path path = Paths.get(ScheduleList.class.getResource(".").toURI());
         System.out.println();
         FileOutputStream fos = new FileOutputStream(path.getParent()
               + "\\database\\scheduleList.ser");
         ObjectOutputStream oos = new ObjectOutputStream(fos);
         oos.writeObject(scheduleList);
         oos.close();
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      catch (URISyntaxException e)
      {
         e.printStackTrace();
      }
   }

   public static ArrayList<Schedule> readFromFile()
   {

      try
      {

         // read object from file
         Path path = Paths.get(ScheduleList.class.getResource(".").toURI());
         FileInputStream fis = new FileInputStream(path.getParent()
               + "\\database\\scheduleList.ser");
         ObjectInputStream ois = new ObjectInputStream(fis);
         ArrayList<Schedule> result = (ArrayList<Schedule>) ois.readObject();
         ois.close();
         return result;
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      catch (ClassNotFoundException e)
      {
         e.printStackTrace();
      }
      catch (URISyntaxException e)
      {
         e.printStackTrace();
      }
      return null;
   }

}
