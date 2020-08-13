package es.mariasoria.cardatabase.domain;

// CrudRepository interface for CRUD operations. We can use its methods for the DB
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CarRepository extends CrudRepository <Car, Long> {

    // Using Crud Repository you can use its methods to fetch from DB
    // Fetch cars by ...
    List<Car> findByBrand(@Param("brand") String brand);
    List<Car> findByColor(@Param("color") String color);
    List<Car> findByYear(@Param("year") String year);
    List<Car> findByModel(@Param("model") String model);
    List<Car> findByPrice(@Param("price") String price);

    // You can also fetch by a couple of attributes, concatenated by "and" or "or"
    // Fetch cars by brand and model
    List<Car> findByBrandAndModel(@Param("brand") String brand,@Param("model") String model);
    // Fetch cars by brand or color
    List<Car> findByBrandOrColor(@Param("brand") String brand, @Param("color") String color);

    // You can also fetch them and get the sorted by "OrderBy"
    // Fetch cars by brand and sort by year
    List<Car> findByBrandOrderByYearAsc(@Param("brand") String brand);

    // You can also create queries using directly SQL with the annotation @Query
    // Fetch cars by brand using SQL
    //@Query("select c from Car c where c.brand = ?1")
    //List<Car> findByBrand(@Param("brand") String brand);

    // you can also use PagingAndSortingRepository to paginate and sort
    // check them in pages 44 to 46
}
