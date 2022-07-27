package com.sda.weather.forecast.client;

import com.sda.weather.forecast.Forecast;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
}
