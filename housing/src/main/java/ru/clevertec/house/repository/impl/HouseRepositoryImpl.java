//package ru.clevertec.house.repository.impl;
//
//import lombok.RequiredArgsConstructor;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//import ru.clevertec.house.dto.response.PersonResponseDto;
//import ru.clevertec.house.exception.RepositoryException;
//import ru.clevertec.house.model.House;
//import ru.clevertec.house.model.Person;
//import ru.clevertec.house.repository.HouseRepository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//
//@Repository
//@RequiredArgsConstructor
//public class HouseRepositoryImpl implements HouseRepository {
//
//    private static final String SELECT_RESIDENTS = """
//            SELECT p.uuid,
//                   p.name,
//                   p.surname,
//                   p.sex,
//                   p.passport_series,
//                   p.passport_number,
//                   p.create_date,
//                   p.update_date
//            FROM houses h
//                     JOIN people p ON p.residency_id = h.id
//            WHERE h.uuid = :uuid;
//            """;
//
//    public static final String SELECT_ALL_QUERY = """
//            SELECT h.uuid,
//                   h.area,
//                   h.country,
//                   h.city,
//                   h.street,
//                   h.number,
//                   h.create_date,
//                   h.update_date
//            FROM houses h
//            LIMIT :limit OFFSET :offset;
//            """;
//
//    public static final String UPDATE_QUERY = """
//            UPDATE houses
//            SET area        = :area,
//                country     = :country,
//                city        = :city,
//                street      = :street,
//                number      = :number,
//                update_date = NOW()
//            WHERE id = :id
//            """;
//
//    private final SessionFactory sessionFactory;
//
//    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//
//    @Override
//    public boolean save(House house) {
//        try (Session session = sessionFactory.openSession()) {
//            try {
//                session.beginTransaction();
//                session.persist(house);
//                session.getTransaction().commit();
//                return true;
//            } catch (Exception e) {
//                session.getTransaction().rollback();
//                throw new RepositoryException("Failed to save house: this house already exist", e);
//            }
//        }
//    }
//
//    @Override
//    public House findById(UUID uuid) {
//        try (Session session = sessionFactory.openSession()) {
//            return session.get(House.class, uuid);
//        } catch (Exception ex) {
//            throw new RepositoryException(String.format("Failed to find house where uuid = %d!", uuid), ex);
//        }
//    }
//
//    @Override
//    public List<Person> findHouseResidents(UUID uuid) {
//        try (Session session = sessionFactory.openSession()) {
//            Query<Person> query = session.createNativeQuery(SELECT_RESIDENTS, Person.class)
//                    .setParameter("uuid", uuid);
//            return query.getResultList();
//        } catch (Exception ex) {
//            throw new RepositoryException(String.format("Failed to find house residents where house uuid = %d!", uuid), ex);
//        }
//    }
//
//    @Override
//    public List<House> findAll(int limit, int offset) {
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
//    public boolean updateById(UUID uuid, House house) {
//        try (Session session = sessionFactory.openSession()) {
//            try {
//                session.beginTransaction();
//                session.createNativeQuery(UPDATE_QUERY, House.class)
//                        .setParameter("area", house.getArea())
//                        .setParameter("country", house.getCountry())
//                        .setParameter("city", house.getCity())
//                        .setParameter("street", house.getStreet())
//                        .setParameter("number", house.getNumber())
//                        .setParameter("uuid", uuid)
//                        .executeUpdate();
//                session.getTransaction().commit();
//                return true;
//            } catch (Exception e) {
//                session.getTransaction().rollback();
//                String message = "Failed to update house with uuid = %d. This house is not exist!";
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
//                House house = session.getReference(House.class, uuid);
//                session.remove(house);
//                session.getTransaction().commit();
//                return true;
//            } catch (Exception e) {
//                session.getTransaction().rollback();
//                String message = "Failed to delete house  with uuid = %d. This house is not exist!";
//                throw new RepositoryException(String.format(message), e);
//            }
//        }
//    }
//
//    private House construct(ResultSet resultSet) throws SQLException {
//        House house = new House();
//        house.setUuid(resultSet.getObject("uuid", UUID.class));
//        house.setArea(resultSet.getString("area"));
//        house.setCountry(resultSet.getString("country"));
//        house.setCity(resultSet.getString("city"));
//        house.setStreet(resultSet.getString("street"));
//        house.setNumber(resultSet.getString("number"));
//        house.setCreateDate(resultSet.getObject("create_date", LocalDateTime.class));
//        house.setUpdateDate(resultSet.getObject("update_date", LocalDateTime.class));
//        return house;
//    }
//
//}
