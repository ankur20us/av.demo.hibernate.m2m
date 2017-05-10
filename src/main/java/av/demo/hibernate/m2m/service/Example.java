package av.demo.hibernate.m2m.service;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import av.demo.hibernate.m2m.modal.Course;
import av.demo.hibernate.m2m.modal.Student;

public class Example {

	public static void main(String[] args) {
		SessionFactory factory = null;
		Session session = null;
		
		try {
		
			Configuration 	cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			System.out.println("hello");	

			factory = cfg.buildSessionFactory();
			session = factory.openSession();
			Transaction tx = null;
			
			Student s1=new Student();
				s1.setStudentId(100);
				s1.setStudentName("James");
				s1.setMarks(98);
	 
			Student s2=new Student();
				s2.setStudentId(101);
				s2.setStudentName("Lee");
				s2.setMarks(99);
				
			Course c1=new Course();
				c1.setCourseId(500);
				c1.setCourseName("Hibernate");
				c1.setDuration(7);
		 
			Course c2=new Course();
				c2.setCourseId(501);
				c2.setCourseName("Java");
				c2.setDuration(30);
				
			Set<Course> courses = new HashSet<Course>();
				courses.add(c1);
				courses.add(c2);
				
			s1.setCourses(courses);
			s2.setCourses(courses);
			
			/***
			 * AV:
			 * 		Inserting the data
			 */
			tx = session.beginTransaction();
				session.save(s1);
				session.save(s2);
			tx.commit();
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null){
				session.close();
			}
			if(factory != null){
				factory.close();				
			}
		}
	}
}
