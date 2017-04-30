
package testtradingstrategy;

import java.io.IOException;
import java.util.ArrayList;

public class TestMethods {
    
    public ArrayList<Integer> upFractals() throws IOException
    {        
        ArrayList<Integer> upfractals = new ArrayList<>(); 
        for(int i = 2; i < (ParsingExcel.maxArr.size()-2); i++)
        {
          if((ParsingExcel.maxArr.get(i)>ParsingExcel.maxArr.get(i-1))&(ParsingExcel.maxArr.get(i)>ParsingExcel.maxArr.get(i-2))
             &(ParsingExcel.maxArr.get(i)>ParsingExcel.maxArr.get(i+1))&ParsingExcel.maxArr.get(i)>ParsingExcel.maxArr.get(i+2))
              upfractals.add(i);
        }        
        return upfractals;
    }
     public ArrayList<Integer> downFractals() throws IOException
     {
         ArrayList<Integer> downfractals = new ArrayList<>(); 
        for(int i = 2; i < (ParsingExcel.minArr.size()-2); i++)
        {
          if((ParsingExcel.minArr.get(i)<ParsingExcel.minArr.get(i-1))&(ParsingExcel.minArr.get(i)<ParsingExcel.minArr.get(i-2))
             &(ParsingExcel.minArr.get(i)<ParsingExcel.minArr.get(i+1))&ParsingExcel.minArr.get(i)<ParsingExcel.minArr.get(i+2))
              downfractals.add(i);
        }        
        return downfractals; 
     }
     //Методы для проверки наличия двух вверхнаправленных свечей подряд
     //В качестве аргумента индекс в исходном массиве
     public boolean uptwoSameCandle(int i) throws IOException
     {
//         ParsingExcel.readFromExcel();
        return (ParsingExcel.openArr.get(i)-ParsingExcel.closeArr.get(i))<0&((ParsingExcel.openArr.get(i-1)-ParsingExcel.closeArr.get(i-1))<0);
     }
     public boolean downTwoSameCandle(int i) throws IOException
     {
//         ParsingExcel.readFromExcel();
        return (ParsingExcel.openArr.get(i)-ParsingExcel.closeArr.get(i))>0&((ParsingExcel.openArr.get(i-1)-ParsingExcel.closeArr.get(i-1))>0);
     }
      //Метод для проверки наличия трех внизнаправленных свечей подряд
     //В качестве аргумента индекс в исходном массиве
     public boolean downthreeSameCandle(int i) throws IOException
     {
//         ParsingExcel.readFromExcel();
        return (ParsingExcel.openArr.get(i)-ParsingExcel.closeArr.get(i))>0&((ParsingExcel.openArr.get(i-1)-ParsingExcel.closeArr.get(i-1))>0)
                &((ParsingExcel.openArr.get(i-2)-ParsingExcel.openArr.get(i-2))>0);
     }
     //три вверхнаправленные свечи подряд
     public boolean upThreeSameCandle(int i) throws IOException
     {
//         ParsingExcel.readFromExcel();
        return (ParsingExcel.openArr.get(i)-ParsingExcel.closeArr.get(i))<0&((ParsingExcel.openArr.get(i-1)-ParsingExcel.closeArr.get(i-1))<0)
                &((ParsingExcel.openArr.get(i-2)-ParsingExcel.openArr.get(i-2))<0);
     }
     //Методы для проверки наличия трёх подряд растущих внизнаправленных фракталов
     //В качестве аргумента - массив с фракталами
     public boolean upThreeSameFractals(int i) throws IOException
     {    
         int i1 = downFractals().get(i);
         int i2 = downFractals().get(i-1);
         int i3 = downFractals().get(i-2);
return (ParsingExcel.minArr.get(i1)>ParsingExcel.minArr.get(i2))&(ParsingExcel.minArr.get(i2)>ParsingExcel.minArr.get(i3));
     }
     //Метод для проверки наличия трех подряд падающих вверхнаправленных фракталов
     public boolean downThreeSameFractals(int i) throws IOException
     { 
          int i1 = upFractals().get(i);
         int i2 = upFractals().get(i-1);
         int i3 = upFractals().get(i-2);
        return (ParsingExcel.maxArr.get(i1)<ParsingExcel.maxArr.get(i2))&(ParsingExcel.maxArr.get(i2)<ParsingExcel.maxArr.get(i3)); 
     }
     //Метод для проверки наличия трех подряд растущих вверхнаправленных фракталов
     public boolean upUpThreeSameFractals(int i) throws IOException
     {  
         int i1 = upFractals().get(i);
         int i2 = upFractals().get(i-1);
         int i3 = upFractals().get(i-2);
        return (ParsingExcel.maxArr.get(i1)>ParsingExcel.maxArr.get(i2))&(ParsingExcel.maxArr.get(i2)>ParsingExcel.maxArr.get(i3)); 
     } 
     //метод для проверки наличия трех подряд падающих внизнаправленых фракталов
     public boolean downDownThreeSameFractals(int i) throws IOException
     {      
         int i1 = downFractals().get(i);
         int i2 = downFractals().get(i-1);
         int i3 = downFractals().get(i-2);
        return (ParsingExcel.minArr.get(i1)<ParsingExcel.minArr.get(i2))&(ParsingExcel.minArr.get(i2)<ParsingExcel.minArr.get(i3));  
     }
     //метод для тестирования входа по трем фракталам вверх(верхним и нижним). 
     //вход по цене закрытия свечи, на которой третий нижний фрактал образовался
     //выход после получения прибыли в размере множителя double m
     public void testUpThreeFractals(double m) throws IOException
     {
         double profit = 0;
         double openPrice = 0;
         double stopLoss;
         
       for(int i=2; i<(downFractals().size()-3); i++){ 
           for(int j=2; j<upFractals().size(); j++){
          if(upThreeSameFractals(i)==true)
              if(upUpThreeSameFractals(j)==true)
                if(upFractals().get(j)<=downFractals().get(i)
                        &upFractals().get(j)>=downFractals().get(i-1)){ 
                    for(int y=i+3; y<=ParsingExcel.closeArr.size()-1; y++){                        
                        stopLoss=ParsingExcel.minArr.get(i);
                        openPrice=ParsingExcel.closeArr.get(i+2);
                        if(ParsingExcel.minArr.get(y)<=stopLoss)
                         profit=profit+stopLoss-openPrice;
                        if(ParsingExcel.maxArr.get(y)>=m*stopLoss)
                            profit=profit+m*stopLoss-openPrice;
                        System.out.println(profit); 
                    }
                    }          
        }
     }
       System.out.println(profit); 
    }
}
 
