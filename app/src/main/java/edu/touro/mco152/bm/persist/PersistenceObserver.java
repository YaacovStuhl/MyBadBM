package edu.touro.mco152.bm.persist;

import edu.touro.mco152.bm.Observers.CustomObserver;
import jakarta.persistence.EntityManager;

public class PersistenceObserver implements CustomObserver {

    /**
     * An implementation of the {@link edu.touro.mco152.bm.Observers.CustomObserver CustomObserver} interface whose update() method will be called by the
     * {@link edu.touro.mco152.bm.Observers.CustomObservable CustomObservable} class and will persist {@link
     * edu.touro.mco152.bm.persist.DiskRun DiskRun}
     *  data to the database
     */

    public  PersistenceObserver() {}

    @Override
    public void update(DiskRun run) {
        EntityManager em = EM.getEntityManager();
        em.getTransaction().begin();
        em.persist(run);
        em.getTransaction().commit();
    }
}
