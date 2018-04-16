package DataTableTest.MainPac;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

public class Test {
	Test() {

	}
	
	public void test(ClassMembers someClassName, ClassMembers someTestClassName, DataReader dr,
			SessionFactory factory) {
		List<String[]> dataTest = dr.getListOfDataTest();
		Method[] methodsObject = someClassName.getSomeMethods();
		List<Boolean> resultForEachColumn = new ArrayList<>();
		
		//The List<String> is for printing where exactly the false result occurred.
		List<String> namesOfVariables = new ArrayList<>();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String className = someClassName.getSomeClass().getName();
			List<Object> listOfObjects = session.createQuery("FROM " + className).list();
			for (int nObject = 0; nObject < listOfObjects.size(); nObject++) {
				Object oneObject = listOfObjects.get(nObject);
				String[] testDataArray = dataTest.get(nObject);
				for (int i = 0; i < testDataArray.length; i++) {
					try {
						try {
							namesOfVariables.add(methodsObject[i].getName().substring(3));
							String someMethodValue = String.valueOf(methodsObject[i].invoke(oneObject));
							resultForEachColumn.add(someMethodValue.equals(testDataArray[i]));
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				boolean match = resultForEachColumn.stream().allMatch(n -> true);
				String resultText = writeResultText(namesOfVariables, resultForEachColumn);
				// Add data to the test table
				addTest(someTestClassName, match, resultText, factory);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	private String writeResultText(List<String> stringList, List<Boolean> conditionList) {
		String text = "OK";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < conditionList.size(); i++) {
			if (!conditionList.get(i)) {
				text = sb.append(stringList.get(i)).append(" is not match.\n").toString();
			}
		}
		return text;
	}

	private void addTest(ClassMembers someTestClassName, boolean result, String resultText, SessionFactory factory)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {
		Object objectTest = null;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			objectTest = someTestClassName.getNotDefConstructor().newInstance(result, resultText);
			session.save(objectTest);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
}
