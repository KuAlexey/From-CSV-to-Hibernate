package DataTableTest.MainPac;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class DataReader {

    private List<Object> listOfObjects = new ArrayList<>();
    private List<String[]> listOfDataTest = new ArrayList<>();
    private String pathFile;

    DataReader(String pathFile, ClassMembers someClass) {
        this.pathFile = pathFile;
        readAllProduct(someClass);
    }

    public void setPathName(String anotherPath) {
        pathFile = anotherPath;
    }

    private void addObject(ClassMembers someClass, String[] args) {
        Object object;
        List<Object> listOfValues = new ArrayList<>();
        Constructor constructor = someClass.getNotDefConstructor();
        Class[] classTypeArray = constructor.getParameterTypes();
        //Type correcting
        for (int i = 0; i < args.length; i++) {
            String typeName = classTypeArray[i].getSimpleName();
            switch (typeName) {
                case "short":
                    listOfValues.add(Short.parseShort(args[i]));
                    break;
                case "int":
                    listOfValues.add(Integer.parseInt(args[i]));
                    break;
                case "long":
                    listOfValues.add(Long.parseLong(args[i]));
                    break;
                case "float":
                    listOfValues.add(Float.parseFloat(args[i]));
                    break;
                case "double":
                    listOfValues.add(Double.parseDouble(args[i]));
                    break;
                case "BigDecimal":
                    listOfValues.add(new BigDecimal(args[i]));
                    break;
                case "boolean":
                    listOfValues.add(Boolean.parseBoolean(args[i]));
                    break;
                default:
                    listOfValues.add(args[i]);
                    break;
            }
        }
        Object[] values = listOfValues.toArray();
        try {
            object = constructor.newInstance(values);
            listOfObjects.add(object);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void addDataTest(String[] data) {
        listOfDataTest.add(data);
    }

    public List<Object> getListOfObjects() {
        return listOfObjects;
    }

    public List<String[]> getListOfDataTest() {
        return listOfDataTest;
    }

    private void readAllProduct(ClassMembers someClass) {
        int numberOfParameters = someClass.getNotDefConstructor().getParameterCount();
        int row = 1;
        try {
            BufferedReader buffreader = Files.newBufferedReader(Paths.get(pathFile));
            String strLine;
            while ((strLine = buffreader.readLine()) != null) {
                String[] arrayOfLine = strLine.split(",");
                //Make sure that data is fully represent.
                if (arrayOfLine.length % numberOfParameters == 0) {
                    addObject(someClass, arrayOfLine);
                    addDataTest(arrayOfLine);
                    row++;
                } else {
                    throw new IllegalArgumentException("In the CSV file required data has not been received on row "+ row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


