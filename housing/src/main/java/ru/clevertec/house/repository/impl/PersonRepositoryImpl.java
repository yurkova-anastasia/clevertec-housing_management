//package ru.clevertec.house.repository.impl;
//
//import lombok.RequiredArgsConstructor;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//import ru.clevertec.house.exception.RepositoryException;
//import ru.clevertec.house.model.House;
//import ru.clevertec.house.model.Person;
//import ru.clevertec.house.repository.PersonRepository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//
//@Repository
//@RequiredArgsConstructor
//public class PersonRepositoryImpl implements PersonRepository {
//
//    private static final String SELECT_OWNED_HOSES = """
//            SELECT h.uuid,
//                   h.area,
//                   h.country,
//                   h.city,
//                   h.street,
//                   h.number,
//                   h.create_date,
//                   h.update_date
//            FROM people p
//                     JOIN houses_owners ho ON p.id = ho.owner_id
//                     JOIN houses h ON ho.house_id = h.id
//            WHERE p.uuid = :uuid;
//            """;
//
//    public static final String SELECT_ALL_QUERY = """
//            SELECT p.uuid,
//                   p.name,
//                   p.surname,
//                   p.sex,
//                   p.passport_series,
//                   p.passport_number,
//                   p.create_date,
//                   p.update_date
//            FROM people p
//            LIMIT :limit OFFSET :offset;
//            """;
//
//    public static final String UPDATE_QUERY = """
//            UPDATE people
//            SET name            = :name,
//                surname         = :surname,
//                sex             = :sex,
//                passport_series = :passportSeries,
//                passport_number = :passportNumber
//            WHERE uuid = :uuid
//            """;
//
//    private final SessionFactory sessionFactory;
//
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    @Override
//    public boolean save(Person person) {
//        try (Session session = sessionFactory.openSession()) {
//            try {
//                session.beginTransaction();
//                session.persist(person);
//                session.getTransaction().commit();
//                return true;
//            } catch (Exception e) {
//                session.getTransaction().rollback();
//                throw new RepositoryException("Failed to save person", e);
//            }
//        }
//    }
//
//    @Override
//    public Person findById(UUID uuid) {
//        try (Session session = sessionFactory.openSession()) {
//            return session.get(Person.class, uuid);
//        } catch (Exception ex) {
//            throw new RepositoryException(String.format("Failed to find person where uuid = %d!", uuid), ex);
//        }
//    }
//
//    @Override
//    public List<House> findOwnedHouses(UUID uuid) {
//        try (Session session = sessionFactory.openSession()) {
//            Query<House> query = session.createNativeQuery(SELECT_OWNED_HOSES, House.class)
//                    .setParameter("uuid", uuid);
//            return query.getResultList();
//        } catch (Exception ex) {
//            throw new RepositoryException(String.format("Failed to find owned house for person with uuid = %d!", uuid), ex);
//        }
//    }
//
//    @Override
//    public List<Person> findAll(int limit, int offset) {
//        try {
//            MapSqlParameterSource params = new MapSqlParameterSource();
//            params.addValue("limit", limit);
//            params.addValue("offset", offset);
//
//            return namedParameterJdbcTemplate.query(SELECT_ALL_QUERY, params,
//                    (resultSet, rowNum) -> construct(resultSet));
//        } catch (Exception ex) {
//            throw new RepositoryException("The entities were not found[" + ex.getMessage() + "]");
//        }
//    }
//
//    @Override
//    public boolean updateById(UUID uuid, Person person) {
//        try (Session session = sessionFactory.openSession()) {
//            try {
//                session.beginTransaction();
//                session.createNativeQuery(UPDATE_QUERY, Person.class)
//                        .setParameter("name", person.getName())
//                        .setParameter("surname", person.getSurname())
//                        .setParameter("sex", person.getSex())
//                        .setParameter("passportSeries", person.getPassportSeries())
//                        .setParameter("passportNumber", person.getPassportNumber())
//                        .setParameter("uuid", uuid)
//                        .executeUpdate();
//                session.getTransaction().commit();
//                return true;
//            } catch (Exception e) {
//                session.getTransaction().rollback();
//                String message = "Failed to update person with uuid = %d.";
//                throw new RepositoryException(String.format(message, uuid), e);
//            }
//        }
//    }
//
//    @Override
//    public boolean deleteById(UUID uuid) {
//        try (Session session = sessionFactory.openSession()) {
//            try {
//                session.beginTransaction();
//                Person person = session.getReference(Person.class, uuid);
//                session.remove(person);
//                session.getTransaction().commit();
//                return true;
//            } catch (Exception e) {
//                session.getTransaction().rollback();
//                String message = "Failed to delete person with uuid = %d. This person is not exist!";
//                throw new RepositoryException(String.format(message, uuid), e);
//            }
//        }
//    }
//
//    private Person construct(ResultSet resultSet) throws SQLException {
//        Person person = new Person();
//        person.setUuid(resultSet.getObject("uuid", UUID.class));
//        person.setName(resultSet.getString("name"));
//        person.setSurname(resultSet.getString("surname"));
//        person.setSex(resultSet.getString("sex"));
//        person.setPassportSeries(resultSet.getString("passport_series"));
//        person.setPassportNumber(resultSet.getString("passport_number"));
//        person.setCreateDate(resultSet.getObject("create_date", LocalDateTime.class));
//        person.setUpdateDate(resultSet.getObject("update_date", LocalDateTime.class));
//        return person;
//    }
//}
