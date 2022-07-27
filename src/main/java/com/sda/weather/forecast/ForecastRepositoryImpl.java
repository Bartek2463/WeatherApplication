package com.sda.weather.forecast;

import com.sda.weather.forecast.Forecast;
import com.sda.weather.forecast.ForecastRepository;
import com.sda.weather.location.Location;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
public class ForecastRepositoryImpl implements ForecastRepository {
    private final SessionFactory sessionFactory;

    @Override
    public Forecast save(Forecast forecast) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(forecast);
        transaction.commit();
        return forecast;
    }

    @Override
    public Optional<Forecast> findByLocation(Location location, LocalDate now, LocalDate forecastDate) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.createQuery()
    }
}
