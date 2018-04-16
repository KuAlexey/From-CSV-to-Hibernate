package DataTableTest.MainPac;

import org.hibernate.SessionFactory;

public class MainApp {

	public static void main(String[] args) {
		// Making session factory
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		// Editable
		ClassMembers itemClass = new ClassMembers("DataTableTest.MainPac.Item");
		ClassMembers testItemClass = new ClassMembers("DataTableTest.MainPac.ItemTest");
		DataReader readAllData = new DataReader("src/DataTableTest/fileCSV.csv", itemClass);

		// Write data to the Item table
		new ManageObject().addObjects(readAllData, sessionFactory);
		// Write result of testing to the TestItem table
		new Test().test(itemClass, testItemClass, readAllData, sessionFactory);

		// Cleaning up
		HibernateUtil.shutdown();
	}

}
