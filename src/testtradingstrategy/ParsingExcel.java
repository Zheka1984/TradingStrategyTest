package testtradingstrategy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ParsingExcel {

        public static ArrayList<Object> dateArr = new ArrayList<>();
        public static ArrayList<Object> timeArr = new ArrayList<>();
        public static ArrayList<Double> openArr = new ArrayList<>();
        public static ArrayList<Double> maxArr = new ArrayList<>();
        public static ArrayList<Double> minArr = new ArrayList<>();
        public static ArrayList<Double> closeArr = new ArrayList<>();
        public static ArrayList<Object> volumeArr = new ArrayList<>();
        public static String fileName = "F:\\CHFJPY240.xls";
     public static void readFromExcel() throws IOException
    {
        int date = 0;
        int time = 1;
        int open = 2;
        int max = 3;
        int min = 4;
        int close = 5;
        int volume = 6;
        
        HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream(fileName));
        HSSFSheet sheet = workBook.getSheetAt(0);
        
        Iterator<org.apache.poi.ss.usermodel.Row> rows = sheet.rowIterator();
        
        if(rows.hasNext())
        {
            rows.next();
        }
        while(rows.hasNext())
        {
            
            HSSFRow row = (HSSFRow) rows.next();
            //получаем значение ячеек по номерам столбцов
            HSSFCell dateCell = row.getCell(date);
            //получаем строковое значение из ячейки
            Object dates = dateCell.getStringCellValue();
            dateArr.add(dates);
            
            HSSFCell timeCell = row.getCell(time);
            Object times = timeCell.getStringCellValue();
            timeArr.add(times);
            
            HSSFCell openCell = row.getCell(open);
            Double openes = Double.parseDouble(openCell.getStringCellValue());
            openArr.add(openes);
            
            HSSFCell maxCell = row.getCell(max);
            Double maxes = Double.parseDouble(maxCell.getStringCellValue());
            maxArr.add(maxes);
            
            HSSFCell minCell = row.getCell(min);
            Double mines = Double.parseDouble(minCell.getStringCellValue());
            minArr.add(mines);
            
            HSSFCell closeCell = row.getCell(close);
            Double closes = Double.parseDouble(closeCell.getStringCellValue());
            closeArr.add(closes);
            
            HSSFCell volumeCell = row.getCell(volume);
            Object volumes = volumeCell.getNumericCellValue();
            volumeArr.add(volumes);
        } 
    } 
    public void test3candle(){
        int success = 0;
        int fail = 0;
for (int i = 2; i < openArr.size(); i++){
    if((openArr.get(i-2)-closeArr.get(i-2))<0
            &(openArr.get(i-1)-(double)closeArr.get(i-1))<0
            &((double)openArr.get(i)-(double)closeArr.get(i))<0){
        success++;
    }       
    if(((double)openArr.get(i-2)-(double)closeArr.get(i-2))<0
            &((double)openArr.get(i-1)-(double)closeArr.get(i-1))<0
            &((double)openArr.get(i)-(double)closeArr.get(i))>0)
    {
        fail++;
    }
    if(((double)openArr.get(i-2)-(double)closeArr.get(i-2))>0
            &((double)openArr.get(i-1)-(double)closeArr.get(i-1))>0
            &((double)openArr.get(i)-(double)closeArr.get(i))>0)
    {
       success++; 
    }
    if(((double)openArr.get(i-2)-(double)closeArr.get(i-2))>0
            &((double)openArr.get(i-1)-(double)closeArr.get(i-1))>0
            &((double)openArr.get(i)-(double)closeArr.get(i))>0)
    {
       fail++;  
    }
      }
        System.out.println("Успешно: "+success+" Неуспешно:"+fail);
    }
}

