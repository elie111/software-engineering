package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
public class App
{

    private static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        // Add ALL of your entities here. You can also try adding a whole package.
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(Owner.class);
        configuration.addAnnotatedClass(Garage.class);
        configuration.addAnnotatedClass(Image.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static void generateData() throws Exception {
        Random random = new Random();
        List<Garage> garageslst = new ArrayList<Garage>();
        List<Car> carslst = new ArrayList<Car>();
        List<Owner> ownerslst = new ArrayList<Owner>();
        List<Image> imageslst = new ArrayList<Image>();
        for(int i=0; i<2;i++){
            Garage garage=new Garage("address"+i,"phonenum"+i);
            garageslst.add(garage);
            session.save(garage);
            session.flush();
        }
        for(int i=0;i<8;i++){
            Owner owner=new Owner("ownerName"+i,"ownerLastName"+i,"email"+i,"password"+i);
            Image image=new Image("image"+i);
            ownerslst.add(owner);
            imageslst.add(image);
            session.save(owner);
            session.save(image);


            Car car = new Car("MOO-" + random.nextInt(), 100000, 2000 + random.nextInt(19),ownerslst.get(i),imageslst.get(i));
            session.save(car);
            carslst.add(car);
            imageslst.get(i).setCar(car);

            if(i%2==0){
                garageslst.get(0).addCar(car);
                garageslst.get(0).addOwner(owner);
            }
            else{
                garageslst.get(1).addCar(car);
                garageslst.get(1).addOwner(owner);
            }


 /*
 * The call to session.flush() updates the DB immediately without ending the transaction.
 * Recommended to do after an arbitrary unit of work.
 * MANDATORY to do if you are saving a large amount of data - otherwise you may get
cache errors.
 */
            session.flush();
        }


    }

    private static <T> List<T> getAllData(Class<T> c) throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(c);
        Root<T> rootEntry = criteriaQuery.from(c);
        CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);
        TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
        return allQuery.getResultList();
    }
    private static void printAllGarages()throws Exception{
        List<Garage> garages = getAllData(Garage.class);
        for (Garage garage : garages) {
            System.out.print("Id: ");
            System.out.print(garage.getId());
            System.out.print(" address: ");
            System.out.print(garage.getAddress());
            System.out.print(" cars ");
            for(int i=0;i<garage.getCars().size();i++)
            System.out.print(garage.getCars().get(i).getLicensePlate()+" ");
            System.out.print(" owners ");
            for(int i=0;i<garage.getOwners().size();i++)
                System.out.print(garage.getOwners().get(i).getName()+" ");

            System.out.print('\n');
        }
    }
    private static void printAllCars() throws Exception {
        List<Car> cars = getAllData(Car.class);
        for (Car car : cars) {
            System.out.print("Id: ");
            System.out.print(car.getId());
            System.out.print(", License plate: ");
            System.out.print(car.getLicensePlate());
            System.out.print(", Price: ");
            System.out.print(car.getPrice());
            System.out.print(", Year: ");
            System.out.print(car.getYear());
            System.out.print('\n');
        }
    }

    public static void main( String[] args ) {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();


            generateData();
            System.out.println("garages:");
            printAllGarages();
            System.out.println("cars");
            printAllCars();

            session.getTransaction().commit(); // Save everything.

        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }
}
