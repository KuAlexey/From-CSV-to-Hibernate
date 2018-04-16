package DataTableTest.MainPac;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

class ManageObject {

    ManageObject(){

    }
    public void addObjects(DataReader readAllData, SessionFactory factory) {
        List<Object> objectList = readAllData.getListOfObjects();
        if (!objectList.isEmpty()) {
            Session session = factory.openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                for (Object object : objectList) {
                    session.save(object);
                    
                }
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
            objectList.clear();
        } else {
            System.out.print("The list of objects is empty.");
            System.exit(1);
        }

    }

}
